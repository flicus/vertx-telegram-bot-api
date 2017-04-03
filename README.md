# vertx-telegram-bot-api [![Build Status](https://travis-ci.org/flicus/vertx-telegram-bot-api.png)](https://travis-ci.org/flicus/vertx-telegram-bot-api)
Telegam bot api for vert.x 

*still in development*

## Intro
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
## Usage
  Keep in mind non-blocking nature of the vert.x and never block the thread. 
  
### Basic usage
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
  
### Command manager
  More advanced way is to use command manager. This way you will need command class for every type of command your bot is supporting. Lets see how to implement above Hello example using command manager. First, implement HelloCommand class:
  ```java
  @BotCommand(regexp = "^/hello")
  public class HelloCommand extends Command {
      @Override
      public void execute(CommandContext context, Handler<Boolean> handler) {
          getBot().sendMessage(
                  new SendMessage()
                          .setReplyToMessageId(context.getUpdate().getMessage().getMessageId())
                          .setChatId(context.getUpdate().getMessage().getChatId())
                          .setText("Hello back, " + context.getUpdate().getMessage().getFrom().getUsername()));
          handler.handle(Boolean.TRUE);
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
Here we already dont need to write update handler for receiver, commands will be found automatically by annotations and its execute() method will be invoked if the message matches the regexp from commands annotation. 

### Default command
But what if message will not match any command? For this case we need to implement default command which will be executed if message doesnt match any other command defenition. You can implement several of them, but only one will be set as default command. Which one - its depends on which class will be loaded last. So there is no sense to implement several default commands. This is how to implement default command: 
```java
@BotCommand(isDefault = true)
public class DefaultCommand extends Command {
    @Override
    public void execute(CommandContext context, Handler<Boolean> handler) {
        getBot().sendMessage(
                new SendMessage()
                        .setReplyToMessageId(context.getUpdate().getMessage().getMessageId())
                        .setChatId(context.getUpdate().getMessage().getChatId())
                        .setText("I do not understand"));
        handler.handle(Boolean.TRUE);
    }
}
```
Just use isDefault parameter of the @BotCommand annotation.

### Pre-execution command
Next type of command is "check" command. It will be executed even before matching user message to any other command. For example you need to check if this user allowed to use this bot, or you need to increment some counter etc. You can implement several of them, but only one check commands will be executed, so, no use in several check commands. Here how to implement *check* command:
```java
@BotCommand(isPreExecute = true)
public class UserCheck extends Command {
    @Override
    public void execute(CommandContext context, Handler<Boolean> handler) {
        String username = context.getUpdate().getMessage().getFrom().getUsername();
        handler.handle("flicus".equals(username));
    }
}
```
Again, just implement this check command using *isPreExecute* parameter of the annotation, it will be found automatically and executed before any other normal commands. This type of command should return *TRUE* or *FALSE* by calling a handler to let bot know if it is allowed to continue usual command execution. This *check* command will be executed and as far as it will return *FALSE*, bot will stop the command execution. If it will return *TRUE* bot will try to find usual command to execute.
 
 
### Post-execution command
Another type of commands is *post command*. It will be executed only if *check* command returned *TRUE* and after usual commands. If there are no *check* command exist, post command will be executed after normal command. There may be several of them or none, but again only one will be executed. This is how to implement that kind of command:
```java
@BotCommand(isPostExecute = true)
public class PostCommand extends Command {
    @Override
    public void execute(CommandContext context, Handler<Boolean> handler) {
        getBot().sendMessage(
                new SendMessage()
                        .setChatId(context.getUpdate().getMessage().getChatId())
                        .setText("All is done"));
        handler.handle(Boolean.TRUE);
    }
}
```
So, just use *isPostExecute* parameter of annotation.
  
### Commands loading
To dynamically find commands, this library traversing the classpath. If you have a lot of other libraries and classes in your classpath it may be time consuming operation. To dramatically reduce this time you may say to bot in wich package all your annotated commands resides. You can do this like this:
```java
bot = TelegramBot.create(vertx, telegramOptions)
                .useCommandManager("org.test.bot")
                .receiver(new LongPollingReceiver())
                .start();
```
where *org.test.bot* - is the package where your commands resides.

### Manual command addition
You can omit this dynamic commands loading and configure command manager yourself from the code. This is how to do this:
```java
CommandManager commandManager = new CommandManager()
                .addCommand(new DefaultCommand())
                .addCommand(new HelloCommand())
                .addCommand(new PostCommand());
        
        bot = TelegramBot.create(vertx, telegramOptions)
                .useCommandManager(commandManager)
                .receiver(new LongPollingReceiver())
                .start();
```

### CommandContext
CommandContext is intended to keep all information regarding current message processed during all the time this message is processed inside the bot. Commands can store some context information there to use it later in another command. All facilities you are using in the bot, will also be available from CommandContext. For example you may put there some object for database access:
```java
db = new Storage(vertx, config().getString("admin"));
        
        bot = TelegramBot.create(vertx, telegramOptions)
                .useCommandManager()
                .receiver(new LongPollingReceiver())
                .addFacility("db", db)
                .start();
```
Then you can retrieve this facility in your command from CommandContext:
```java
@BotCommand(isPreExecute = true)
public class UserCheck extends Command {
    @Override
    public void execute(CommandContext context, Handler<Boolean> handler) {
        Storage db = context.get("db");
        String username = context.getUpdate().getMessage().getFrom().getUsername();
        handler.handle(db.isRegisteredUser(username));
    }
}
```

 ### Webhook receiver
 to be done

