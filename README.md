Android Boilerplate [![Build Status](https://travis-ci.org/hitherejoe/Android-Boilerplate.svg?branch=master)](https://travis-ci.org/hitherejoe/Android-Boilerplate)
===================

<p align="center">
    <img src="images/ic_web.png" alt="Web Launcher"/>
</p>


A simple boilerplate application which demonstrates the downloading, persisting and syncing of data - displayed with a common layout used amongst applications.

The project is setup using:

- Functional tests with [Espresso](http://google.github.io/android-testing-support-library/docs/espresso)
- Unit tests with [Mockito](http://mockito.org/) and [Robolectric](http://robolectric.org/) 
- [RxJava](https://github.com/ReactiveX/RxJava) and [RxAndroid](https://github.com/ReactiveX/RxAndroid) 
- [Retrofit](http://square.github.io/retrofit/) and [OkHttp](https://github.com/square/okhttp)
- [Dagger 2](http://google.github.io/dagger/)
- [Butterknife](https://github.com/JakeWharton/butterknife)
- [Timber] (https://github.com/JakeWharton/timber)
- [Mockito](http://mockito.org/)
- [Glide](https://github.com/bumptech/glide)

<p align="center">
    <img src="images/device.png" alt="Web Launcher"/>
</p>


Requirements
------------

 - [Android SDK](http://developer.android.com/sdk/index.html).
 - Android [6.0 (API 23) ](http://developer.android.com/tools/revisions/platforms.html#6.0).
 - Android SDK Tools
 - Android SDK Build tools 23.0.2
 - Android Support Repository
 - Android Support library

Building
--------

To build, install and run a debug version, run this from the root of the project:

    ./gradlew installRunDebug
    
Testing
--------

For Android Studio to use syntax highlighting for Automated tests and Unit tests you **must** switch the Build Variant to the desired mode.

To run **pmd**, **checkstyle** and **findbug** checks on your machine:

    ./gradlew check

To run **unit** tests on your machine:

    ./gradlew testDebugUnitTest
    
To run **automated** tests on connected devices:

    ./gradlew connectedDebugAndroidTest

Thanks
--------

Thanks to the following for contributions!

[ivacf] (https://github.com/ivacf)  
[Jawnnypoo] (https://github.com/Jawnnypoo)

Attributions
------------

Thanks to the following for use of icons off of Noun Project:

[Iconoci](https://thenounproject.com/iconoci)
