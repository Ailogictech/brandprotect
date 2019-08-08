# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn java.lang.invoke.*
-dontwarn **$$Lambda$*

-dontwarn com.google.common.**
# Ignores: can't find referenced class javax.lang.model.element.Modifier
-dontwarn com.google.errorprone.annotations.**
-dontwarn javax.naming.**
-dontwarn okio.**
-dontwarn sun.misc.Unsafe

-dontwarn com.fasterxml.jackson.**
-dontwarn okhttp3.**
-dontwarn retrofit2.**

-dontwarn github.nisrulz.**
-keep public class com.google.android.gms.* { public *; }
-dontwarn com.google.android.gms.**

-dontwarn com.akexorcist.roundcornerprogressbar.**
-dontnote com.akexorcist.roundcornerprogressbar.**

-keep class com.github.mikephil.charting.** { *; }