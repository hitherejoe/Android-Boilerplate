Android Boilerplate
===================

A simple boilerplate application which demonstrates the downloading, persisting and syncing of data - displayed with a common layout used amongst applications.

The project is setup to use:

- Espresso automated tests, contained in a seperate androidTest module
- Robolectric Unit tests
- Dagger 2 for dependancy injection
- Networking using retrofit with Rx Java
- Database management using Rx java, SQLBrite and pure SQLite
- Data Sync Service
- Design Support library integrated for Material Style view animations (includes TabLayout, CollapsingToolbar & CoordinatorLayout implementations)
- Holders (adapters) for recycler views using EasyAdapter
- Butterknife for easy view injection
- Glide for easy image downloading / display
- Timber for simple Debug logging

Requirements
------------

 - [Android SDK](http://developer.android.com/sdk/index.html).
 - Android [5.1 (API 22) ](http://developer.android.com/tools/revisions/platforms.html#5.1).
 - Android SDK Tools
 - Android SDK Build tools 23.0.0.0 rc3
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
