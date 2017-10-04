# vertx-telegram-bot-api [![Build Status](https://travis-ci.org/flicus/vertx-telegram-bot-api.png)](https://travis-ci.org/flicus/vertx-telegram-bot-api) [![FOSSA Status](https://app.fossa.io/api/projects/git%2Bgithub.com%2Fflicus%2Fvertx-telegram-bot-api.svg?type=shield)](https://app.fossa.io/projects/git%2Bgithub.com%2Fflicus%2Fvertx-telegram-bot-api?ref=badge_shield)
[Telegram bot api](https://core.telegram.org/bots/api) for [vert.x](http://vertx.io)

Bot API supported: up to 3.3

Vert.x supported : up to 3.4.2

**still in development, to be done:**
- WebhookReceiver class to implement [webhook](https://core.telegram.org/bots/api#getting-updates) way of receiving updates

**new features to implement**
- implement throttling of outgoing messages, make it configurable, make default configuration to obey Telegram limits
- develop some kind of FSM for complex dialogs, tuned to suite bot specific

**kotlin version**
- initial kotlin version commited, to implement remaining data classes 
  
## Intro
Add vertx-telegram-bot-api to your project. 

For maven:
```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
<dependencies>
    <dependency>
        <groupId>com.github.flicus</groupId>
        <artifactId>vertx-telegram-bot-api</artifactId>
        <version>1.0.1</version>
    </dependency>
</dependencies>
```
For gradle:
 ```groovy
 repositories {
    maven { url "https://jitpack.io" }
 }
 dependencies {
    compile 'com.github.flicus:vertx-telegram-bot-api:1.0.1'
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
                  .setBotToken("12345:blablabla");
          bot = TelegramBot.create(vertx, telegramOptions)
                  .receiver(new LongPollingReceiver().onUpdate(update -> {
                      bot.sendMessage(new SendMessage()
                              .setText("Hello, " + update.getMessage().getFrom().getUsername())
                              .setChatId(update.getMessage().getChatId())
                      );
                  }))
                  .start();
      }
  }
```
  In this example we using long polling method of getting updates, and saying hello back to user.
  
### Command manager
  More advanced way is to use command handler. This way you will need command class for every type of command your bot is supporting. Lets see how to implement above Hello example using command handler. First, implement HelloCommand class:
  ```java
  @BotCommand(message = "^/hello")
  public class HelloCommand extends Command {
      @Override
      public void execute(CommandContext context, Handler<Boolean> handler) {
          getBot().replyQuoting(context.getUpdate(), "Hello back, " + context.getUpdate().getMessage().getFrom().getUsername()));
          handler.handle(Boolean.TRUE);
      }
  }
```
Here, with @BotCommand annotation we saying this is bot command, and with message parameter we saying what kind of message sent by user will invoke execute() method of this command.
Message parameter is an regular expression to match with telegram message text from the user. In this example this message starting with "/hello".

Next we need to say to our bot to use command handler:
```java
bot = TelegramBot.create(vertx, telegramOptions)
                .receiver(new LongPollingReceiver().onUpdate(new CommandHandler().loadCommands()))
                .start();
```
Here we already don't need to write update handler for receiver, commands will be found automatically by annotations and its execute() method will be invoked if the message matches the regexp from commands annotation. 

### Default command
But what if message will not match any command? For this case we need to implement default command which will be executed if message does'nt match any other command definition. You can implement several of them, but only one will be set as default command. Which one - its depends on which class will be loaded last. So there is no sense to implement several default commands. This is how to implement default command: 
```java
@BotCommand(isDefault = true)
public class DefaultCommand extends Command {
    @Override
    public void execute(CommandContext context, Handler<Boolean> handler) {
        getBot().reply(context.getUpdate(), "I do not understand");
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
To dynamically find commands, this library traversing the classpath. If you have a lot of other libraries and classes in your classpath it may be time consuming operation. To dramatically reduce this time you may say to bot in which package all your annotated commands resides. You can do this like this:
```java
bot = TelegramBot.create(vertx, telegramOptions)
                .receiver(new LongPollingReceiver().onUpdate(new CommandHandler().loadCommands("org.test.bot")))
                .start();
```
where *org.test.bot* - is the package where your commands resides.

### Manual command addition
You can omit this dynamic commands loading and configure command handler yourself from the code. This is how to do this:
```java
CommandHandler commandHandler = new CommandHandler()
                .addCommand(new DefaultCommand())
                .addCommand(new HelloCommand())
                .addCommand(new PostCommand());
bot = TelegramBot.create(vertx, telegramOptions)
                .receiver(new LongPollingReceiver().onUpdate(commandHandler))
                .start();
```

### CommandContext
CommandContext is intended to keep all information regarding current message processed during all the time this message is processed inside the bot. Commands can store some context information there to use it later in another command. All facilities you are using in the bot, will also be available from CommandContext. For example you may put there some object for database access:
```java
db = new Storage(vertx, config().getString("admin"));
        
bot = TelegramBot.create(vertx, telegramOptions)
                .receiver(new LongPollingReceiver().onUpdate(new CommandHandler().loadCommands()))
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

### Receiving operation results
When you sending some operation to the Telegram server, sendMessage for example, or some of get... operations (getChat for example) and need to receive back result of the operation, you need to use corresponding method of the TelegramBot class with response handler parameter.
```java
bot.getChat(new GetChat().setChatId("chat_id"), response -> {
    if (response.succeeded()) {
        Chat chat = response.result();
        chat.getFirstName();
    } else {
        System.out.println(response.cause());
    }
});
```

 ### Webhook receiver
 to be done

