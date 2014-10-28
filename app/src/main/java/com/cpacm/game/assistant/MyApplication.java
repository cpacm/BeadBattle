package com.cpacm.game.assistant;

import android.app.Application;
import android.content.Context;

/**
 * Created by cpacm on 2014/10/25.
 */
public class MyApplication extends Application {
    private static MyApplication mcontext;

    @Override
    public void onCreate() {
        super.onCreate();
        mcontext = this;
    }

    public static Context getAppContext(){
        return mcontext;
    }
}
