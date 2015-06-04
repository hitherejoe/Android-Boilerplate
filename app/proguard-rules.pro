# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in <android-sdk>/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}


# ButterKnife rules
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewInjector { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}

# Retrofit rules
-dontwarn retrofit.**
-keep class retrofit.** { *; }
-keepattributes Signature
-keepattributes Exceptions

# OkHttp rules
-dontwarn okio.**
-dontwarn com.squareup.okhttp.**

# Otto rules
-keepclassmembers class ** {
    @com.squareup.otto.Subscribe public *;
    @com.squareup.otto.Produce public *;
}

# Related to RxJava
-dontwarn sun.misc.Unsafe

# EasyAdapter rules
-keepclassmembers class * extends uk.co.ribot.easyadapter.ItemViewHolder {
    public <init>(...);
 }

# Gson rules
-keepattributes Signature
-keep class sun.misc.Unsafe { *; }
# Keep non static or private fields of models so Gson can find their names
# TODO change this to match your models package
-keepclassmembers class uk.co.ribot.androidboilerplate.data.model.** {
    !static !private <fields>;
}

# Produces useful obfuscated stack traces
# http://proguard.sourceforge.net/manual/examples.html#stacktrace
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
