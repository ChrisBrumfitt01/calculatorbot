# Calculator Bot

Calculator Bot is a Slack bot written in Spring Boot . This application uses [JBot](https://github.com/rampatra/jbot) as 
a dependency which establishes a connection with Slack while also providing a number of helpful classes to ease the communication with the API.


## Usage

In the Slack client, you can communicate with the bot by sending it a mathematical equation - it will calculate
the answer for you. The calculator will adhere to BODMAS (it will calculate **B**rackets first, followed by **O**rders, followed by **D**ivision & **M**ultiplication
and finally **A**ddition and **S**ubtraction). The calculator will support equations with decimal places and will support negative numbers.

The symbols that are supported in equations are as follows:
+ \+ Addition
+ \- Subtraction
+ \* Multiplication
+ / Division
+ ^ To the power of. E.g. 2 ^ 3 will calculate 2 to the power of 3.
+ () To calculate the sum is the brackets first
+ . for decimal places

For example, some valid equations are:
+ 2 * (5 + 5)  
  * Will return 20
+ 10 / 4
  * Will return 2.5
+ 2 ^ 4
  * Will return 16
+ -2.542 + 5
  * Will return 2.458


## Configuring Calculator Bot

1. Get a [Slack Bot token](https://my.slack.com/services/new/bot)
2. Update the `slackBotToken` property in the [application.properties](src/main/resources/application.properties) file


## Building locally

Getting a local build of Chromie is a simple as cloning the repository and running:

```
mvn clean install
```

## Running locally

You can run the application with the command: 
```
mvn spring-boot:run
```

If you have performed all of the steps correctly up til now - you should now be able to interact with the bot
in your Slack client.




## Using the publicly distributed bot

**Important**: At time of writing, there were issues with the publicly distributed version which may not work.
The application is deployed onto the free tier of Heroku, and one of the drawbacks of this is that it will go to sleep for 7 hours a day. If it does not work, you may try at another time.

This application has been deployed on to Heroku. You can add it to your workspace using this URL or by clicking the button: 

https://slack.com/oauth/v2/authorize?client_id=1047628658710.1040260326579&scope=app_mentions:read,channels:join,channels:read,chat:write,chat:write.customize,commands,incoming-webhook,users:write,calls:read,channels:history,channels:manage,calls:write,chat:write.public,emoji:read,dnd:read,files:read,files:write,groups:history,groups:write,groups:read,im:read,links:read,links:write,im:write,mpim:read,mpim:history,im:history,mpim:write,reactions:write,pins:read,reactions:read,remote_files:read,reminders:write,remote_files:share,reminders:read,remote_files:write,pins:write,usergroups:write,users:read,users:read.email,users.profile:read,usergroups:read,team:read&user_scope=channels:read,admin

<a href="https://slack.com/oauth/v2/authorize?client_id=1047628658710.1040260326579&scope=app_mentions:read,channels:join,channels:read,chat:write,chat:write.customize,commands,incoming-webhook,users:write,calls:read,channels:history,channels:manage,calls:write,chat:write.public,emoji:read,dnd:read,files:read,files:write,groups:history,groups:write,groups:read,im:read,links:read,links:write,im:write,mpim:read,mpim:history,im:history,mpim:write,reactions:write,pins:read,reactions:read,remote_files:read,reminders:write,remote_files:share,reminders:read,remote_files:write,pins:write,usergroups:write,users:read,users:read.email,users.profile:read,usergroups:read,team:read&user_scope=channels:read,admin"><img alt="Add to Slack" height="40" width="139" src="https://platform.slack-edge.com/img/add_to_slack.png" srcset="https://platform.slack-edge.com/img/add_to_slack.png 1x, https://platform.slack-edge.com/img/add_to_slack@2x.png 2x"></a>