package com.example.smart_immunization;

public class Documentation {
    /**
     * Sending SMS Using Africas Talking
     * ----------------------------------
     * Github Link : https://github.com/AfricasTalkingLtd/africastalking-java
     * 1. Add the following gradle
     *  repositories {
     *   maven {
     *              url  "https://jitpack.io"
     *          }
     *  }
     *
     * dependencies{
     *   // Get all services
     *   implementation 'com.github.AfricasTalkingLtd.africastalking-java:core:3.4.8'
     *  }
     *
     *  defaultconfig{
     *         //MARTIN ADDED THIS
     *         multiDexEnabled true //added this line
     *  }
     *
     *  dependancies {
     *          // Get all AfricasTalkingLtd services
     *     implementation 'com.github.AfricasTalkingLtd.africastalking-java:core:3.4.8'
     *
     *     //SOLVE GUAVA ERROR
     *     implementation 'com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava'
     *     implementation 'androidx.multidex:multidex:2.0.1'

     *  }
     *
     *  Manifest:
     *      <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
     *     <uses-permission android:name="android.permission.INTERNET"/>
     *     <uses-permission android:name="android.permission.SEND_SMS"/>
     *
     * Get APi key and username from africasTalking
     *
     * To use Realm, uncomment the following in build.gradle(project)
     * 1.1 dependencies {
     *         //classpath "io.realm:realm-gradle-plugin:10.11.1"
     *     }
     * 1.2 //id 'realm-android' in build.gradle(module)
     * 1.3 //MARTIN ADDED THIS in build.gradle(module)
     * //realm {
     * //    syncEnabled = true
     * //}
     *
     * **/
}
