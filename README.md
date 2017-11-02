# Stone Soup PotLuck

#### Epicodus Android Independent Project, November/3/2017

#### By **Kimberly H. Lu**

[Github repository](https://github.com/kchamp45/StoneSoup2)

Welcome to the ultimate website for those who love soups!  This application allows you to find inspirations from a list of amazing soups so you can gather your ingredients and other cook enthusiasts to cook and socialize.  The application will also allow you to browse soups and determine their ingredients before trying the recipe out.

![app-screenshot](https://github.com/kchamp45/StoneSoup/blob/master/app/src/main/res/drawable/soup.jpg?raw=true)

## Setup/Installation Requirements

This application is intended to be used on your mobile Android device.  To view on your desktop, in your computer terminal, please navigate to your desktop and execute:  $ git clone https://github.com/kchamp45/StoneSoup2.  You will need to download Android Studio. Before installing Android Studio, you will need a JDK 6 or higher (please verify by typing in CLI: $ javac -version). Follow the instructions here https://developer.android.com/studio/archive.html to download Android Studio.  Please note that the AS version used in this app is 2.3.2. Higher or lower version of AS may result in poorer performance.

## Known Bugs

There are no known bugs.

## Technologies Used

JAVA and Android Studios.

## User Stories:

* I want to learn more about Stone Soup.
* I want to find soups.
* I want to determine the soup's ingredients before deciding to try it out (for example, I may have food restrictions or preferences).
* I want to get the recipe for the soup.
* I want an image of the soups.
* I want my food restriction / ingredient exclusion to persist across sessions
* I want my account to be secured.
* I want to re-order my list of saved soups
* I want to delete items from my saved list
* I want to view my soup list in both portrait and landscape views


## Specs

| Behavior  | Input | Output |
| ------------- |:-------------:| -----|
| create an account | enter user information and click "create account"| account created and saved |
| login into a saved account | enter user email and password | after authentication, user enters main view|
| display history of Stone Soup  | click "Folklore" | Display description |
| get inspiration for soups for my Stone Soup gathering | click "Let's cook!"| place to enter a soup ingredients |
| get a list of soups | enter favorite ingredient and excluded ingredient and click "Find Soups"| display list of soups|
| get a list of soups | enter previous favorite ingredient without entering excluded ingredient again & click "Find Soups"| display same list of soups as before|
| find recipes | click "Click for Recipe" | transfer to website for recipe|
| get details of soup| click on soup item | Display the details|
| view saved list | click "saved list" | display saved list|
| reorder saved list items | drag and drop item in new place | display reordered list |
| delete item from saved list | horizontally swipe item off list | display list with said item removed|
| view soup list in landscape view | turn phone or emulator | list displayed in horizontal position |

### License

Published under MIT License.

Copyright (c) 2017 **_Kimberly H. Lu_**
