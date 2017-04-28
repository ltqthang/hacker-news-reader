package com.alpha.hackernewsreader;

import android.app.Application;

/**
 * @author Thang
 * @since 25/4/17 20:52
 */
public class App extends Application {
    private static App sApp;
    private AppComponent sAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        sApp = this;
    }

    public AppComponent getsAppComponent() {
        return sAppComponent;
    }

    public static App getInstance() {
        return sApp;
    }
}
