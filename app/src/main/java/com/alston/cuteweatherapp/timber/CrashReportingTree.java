//package com.alston.cuteweatherapp.timber;
//
//
//import android.app.Application;
//import android.support.annotation.NonNull;
//import android.util.Log;
//import timber.log.Timber;
//
//import static timber.log.Timber.DebugTree;
//
//public class CrashReportingTree extends Timber.Tree {
//    @Override protected void log(int priority, String tag, @NonNull String message, Throwable t) {
//        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
//            return;
//        }
//
//        FakeCrashLibrary.log(priority, tag, message);
//
//        if (t != null) {
//            if (priority == Log.ERROR) {
//                FakeCrashLibrary.logError(t);
//            } else if (priority == Log.WARN) {
//                FakeCrashLibrary.logWarning(t);
//            }
//        }
//    }
//}
//}