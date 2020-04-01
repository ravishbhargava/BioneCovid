package com.bione.corona;

import android.app.Application;
import android.content.Context;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import java.lang.ref.WeakReference;

//import io.paperdb.Paper;
//import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

//import io.github.inflationx.calligraphy3.CalligraphyConfig;
//import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
//import io.github.inflationx.viewpump.ViewPump;


/**
 * Developer: Bione
 *
 * The Application class
 */

public class MyApplication extends Application {

    private static WeakReference<Context> mWeakReference;
    private static GoogleAnalytics sAnalytics;
    private static Tracker sTracker;

    /**
     * Getter to access Singleton instance
     *
     * @return instance of MyApplication
     */
    public static Context getAppContext() {
        return mWeakReference.get();
    }

    @Override
    public void onCreate() {

        super.onCreate();
//        Fabric.with(this, new Crashlytics());
//        Foreground.init(this);
//        Paper.init(this);
        mWeakReference = new WeakReference<Context>(this);

        sAnalytics = GoogleAnalytics.getInstance(this);

//        ViewPump.init(ViewPump.builder()
//                .addInterceptor(new CalligraphyInterceptor(
//                        new CalligraphyConfig.Builder()
//                                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
//                                .setFontAttrId(R.attr.fontPath)
//                                .build()))
//                .build());

//        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
//                .setDefaultFontPath("fonts/OpenSans-Regular.ttf")
//                .setFontAttrId(R.attr.fontPath)
//                .build()
//        );
    }
    synchronized public Tracker getDefaultTracker() {
        // To enable debug logging use: adb shell setprop log.tag.GAv4 DEBUG
        if (sTracker == null) {
            sTracker = sAnalytics.newTracker(R.xml.global_tracker);
        }

        return sTracker;
    }


}
