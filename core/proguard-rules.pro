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
#-dontwarn java.lang.invoke.StringConcatFactory
#-keep class **.databinding.* { *; }
#-keep class **.BR { *; }
#-keep class test.dapuk.core.di.** { *; }
#-keep class test.dapuk.core.domain.model.** { *; }
#-keep class test.dapuk.core.domain.usecase.** { *; }
#-keep class test.dapuk.dicodingcapstone.presentation.** { *; }
#-keep class retrofit.** { *; }
-keep class test.dapuk.core.databinding.** { *; }
-keep class test.dapuk.core.di.** { *; }
-keep class test.dapuk.core.domain.usecase.**{*;}
-keep class * implements retrofit2.CallAdapter { *; }
-keep class * implements retrofit2.Converter { *; }
-keep class * extends retrofit2.Retrofit$Builder { *; }
-keep,allowobfuscation interface test.dapuk.core.data.remote.ApiService { *; }
-keep class test.dapuk.core.domain.model.** { *; }
-keep class test.dapuk.core.data.remote.** { *; }
-dontwarn java.lang.invoke.StringConcatFactory

