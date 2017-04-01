# vertx-telegram-bot-api [![Build Status](https://travis-ci.org/flicus/vertx-telegram-bot-api.png)](https://travis-ci.org/flicus/vertx-telegram-bot-api)
Telegam bot api for vert.x 

*still in development*

### Intro
Add vertx-telegram-bot-api to your project. 

For maven:
 ```xml
 <dependency>
     <groupId>com.github.flicus</groupId>
     <artifactId>vertx-telegram-bot-api</artifactId>
     <version>0.1.12</version>
 </dependency>
 ```
For gradle:
 ```groovy
 dependencies {
       compile 'com.github.flicus:vertx-telegram-bot-api:0.1.12'
  }
 ```
  ### Usage
  Keep in mind non-blocking nature of the vert.x and never block the thread. 
  
  Basic usage is as follows:
  ```java
 public class TestBot extends AbstractVerticle {
  
      private TelegramBot bot;
  
      @Override
      public void start() throws Exception {
  
          TelegramOptions telegramOptions = new TelegramOptions()
                  .setBotName("test_bot")
                  .setBotToken("1234567890:blablabla");
  
          bot = TelegramBot.create(vertx, telegramOptions)
                  .receiver(new LongPollingReceiver()
                          .onUpdate(update -> {
                              bot.sendMessage(new SendMessage()
                                      .setText("Hello, " + update.getMessage().getFrom().getUsername())
                                      .setChatId(update.getMessage().getChatId()));
                          }))
                  .start();
      }
  }
```
  In this example we using long polling method of getting updates, and saying hello back to user.
  
  More advanced way is to use command manager. This way you will need command class for every type of command your bot is supporting. Lets see how to implement above Hello example using command manager. First, implement HelloCommand class:
  ```java
  @BotCommand(regexp = "^/hello")
  public class HelloCommand extends Command {
      @Override
      public void execute(String s, CommandContext commandContext) {
          getBot().sendMessage(
                  new SendMessage()
                          .setReplyToMessageId(commandContext.getUpdate().getMessage().getMessageId())
                          .setChatId(commandContext.getUpdate().getMessage().getChatId())
                          .setText("Hello back, "+commandContext.getUpdate().getMessage().getFrom().getUsername()));
      }
  }
```
Here, with @BotCommand annotation we saying this is bot command, and with regexp parameter we saying what kind of message sent by user will invoke execute() method of this command.
In this example this message starting with "/hello".

Next we need to say to our bot to use command manager:
```java
bot = TelegramBot.create(vertx, telegramOptions)
                .useCommandManager()
                .receiver(new LongPollingReceiver())
                .start();
```
Here we already dont need to write update handler for receiver, commands will be found automatically by annotations and its execute() method will be invoked if the message matches the regexp from commands annotation. But what if message will not match any command? For this case we need to implement default command which will be executed if message doesnt match any other command defenition. For this we just need to use isDefault parameter of the @BotCommand annotation:
```java
@BotCommand(isDefault = true)
public class DefaultCommand extends Command {
    @Override
    public void execute(String s, CommandContext commandContext) {
        getBot().sendMessage(
                new SendMessage()
                        .setReplyToMessageId(commandContext.getUpdate().getMessage().getMessageId())
                        .setChatId(commandContext.getUpdate().getMessage().getChatId())
                        .setText("I do not understand"));
    }
}
```

Next type of command is "check" commands. They will be executed even before matching user message to any other command. For example you need to check if this user allowed to use this bot. Here how to write that type of command:
```java
@BotCheck
public class UserCheck extends Check {
    @Override
    public boolean execute(String s, CommandContext commandContext) {
        String username = commandContext.getUpdate().getMessage().getFrom().getUsername();
        if (!"flicus".equals(username)) {
            return false;
        }
        return true;
    }
}
```
Again, just implement this check command, it will be found automatically and executed before any other normal commands. This type of command should return *true* or *false* to let bot know if it is allowed to continue usual command execution. There may be several "check" commands, they all will be executed and as far as any of them will return *false* bot will stop the command execution. If all of them will return *true* bot will try to find usual command to execute.
 
Another type of commands is *post command*. It will be executed only if *check* commands returned true and after usual commands. There may be several of them or none. This is how to implement that kind of command:
```java
@BotCommand(isPostExecute = true)
public class PostCommand extends Command {
    @Override
    public void execute(String s, CommandContext commandContext) {
        getBot().sendMessage(
                new SendMessage()
                        .setChatId(commandContext.getUpdate().getMessage().getChatId())
                        .setText("post 1"));
    }
}
```
So, just use *isPostExecute* parameter of annotation.
  
  

