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

## Running

You can run the application with the command: 
```
mvn clean install
```

If you have performed all of the steps correctly up til now - you should now be able to interact with the bot
in your Slack client.