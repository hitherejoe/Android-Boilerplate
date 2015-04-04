Android Boilerplate
===================

After finding no examples online of Espresso / Robolectric setup in Android Studio with the new Unit Testing feature
(without using a third party plugin) I decided to create this boilerplate project to document the setup. After 
recreating the same project setup over and over again I decided it was also about time to do so! The project structure is as follows:

<p align="center"><img src="http://i617.photobucket.com/albums/tt254/joeyerrr/project_structure.png" /></p>

All ready to go with:

- Espresso testing
- Robolectric testing
- Network requests using retrofit and Rx Java
- Database management using Rx java and pure SQLite
- Holders for list items using EasyAdapter
- Butterknife for easy view injection
- Picasso for easy image downloading / display

Requirements
------------

 - [Android SDK](http://developer.android.com/sdk/index.html).
 - Android [5.1 (API 22) ](http://developer.android.com/tools/revisions/platforms.html#5.1).
 - Android SDK Tools
 - Android SDK Build tools 22.0.1 
 - Android Support Repository
 - Android Support library

Building
--------

To build, install and run a debug version, run this from the root of the project:

    ./gradlew installRunDebug
    
Testing
--------

For Android Studio to use syntax highlighting for Automated tests and Unit tests you **must** switch the Build Variant to the desired mode.

To run **unit** tests on your machine using [Robolectric] (http://robolectric.org/):

    ./gradlew testDebug
    
To run **automated** tests on connected devices:

    ./gradlew connectedAndroidTest
