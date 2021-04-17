# Projects
 **Chat Room**
 1. Written a server and client programs to let users exchange messages. 
 2. Each user runs the ChatClient program and connect to a shared ChatServer. 
 3. The server is responsible for forwarding incoming messages to all clients that are connected.
 4. Typing a message and hitting send transmits the desired message to the server, which will in turn forward the message to any other client that is connected. 
 5. The server prints to its screen that a message was sent by the user Joe. 
 6. Multiple chat rooms and allowed other users to join.


**finVine**
used source code:
  A simple iOS app for viewing your bank account balance and transactions.
  ![Screenshots](/Assets/Screenshot.png)
  ## How does it work?
  This app uses the [Plaid API](https://www.plaid.com) to fetch information about your bank account(s).
  ## How can I use it?
  1. You're going to need some Plaid API keys, which you can apply for with [Plaid](https://www.plaid.com).
  2. Once you've done this, move the file I've provided named `Plaid.example.plist` to `Plaid.plist`.
  3. Open the Xcode project (Bank.xcworkspace) in Xcode.
  4. Fill in Plaid.plist with your API information from Plaid.
  5. Change the app bundle identifier and group identifiers to your own values, and change the team to your own.
  6. Update the app group identifier in `PlaidManager.swift` to the one you created in the previous step (static variable at top called `groupIdentifier`).
  7. Build the app and install it on your iOS device.

  ## IMPORTANT: Production Usage
  **This project is meant for personal use.** If you plan to ship any of the code, [please read this](/production-usage.md) to **ensure you distribute it safely.**
  ## Reporting Issues
  If you find a bug or code issue, report it on the [issues page](/issues). Keep in mind that this is for actual bugs, **NOT BUILD ISSUES**. 
  ## Contributing
  Feel free to contribute to the source code of Bank to make it something even better! Just try to adhere to the general coding style throughout, to make it as  readable as possible.

  ## License
  This project is licensed under the [MIT license](/LICENSE). Please make sure you comply with its terms while using it in any way.

**FINISHED PROJECT LINK:**
https://devpost.com/software/finvine

**Mental Health Dinos**
1. Harvested tweets using the twitter API
2. Filtered tweets using the criteria of location, mental health keywords, date of tweet
3. Analysed tweets using sentiment analysis and gave each tweet in the database a sentiment tag.
4. Created a interactive map of the world, in which each tweet is “plotted” with the given sentiment
5. Accurately documented all the observation in the map and made it available on a website called mental health dinos. along with resources to deal with mental health issues.
https://devpost.com/software/mental-health-dino
https://magentacloudymedian.vhennemann.repl.co
