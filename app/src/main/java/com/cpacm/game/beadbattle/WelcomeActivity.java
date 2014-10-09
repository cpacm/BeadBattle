package com.cpacm.game.beadbattle;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.BoringLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

import com.cpacm.game.util.ConstantUtil;
import com.cpacm.game.views.WelcomeView;

import java.lang.reflect.Method;

public class WelcomeActivity extends Activity {

    private WelcomeView wView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int ver = Build.VERSION.SDK_INT;
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);//无标题模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏*/

        DisplayMetrics metric = new DisplayMetrics();
        Display display = getWindowManager().getDefaultDisplay();
        display.getMetrics(metric);
        ConstantUtil.ScreenWidth = metric.widthPixels;
        ConstantUtil.ScreenHeight = metric.heightPixels;
        ConstantUtil.density = metric.density;
        ConstantUtil.DPI = metric.densityDpi;

        Log.d("TEST",ConstantUtil.ScreenWidth+" "+ConstantUtil.ScreenHeight+" "+metric.density+" "+ConstantUtil.DPI);

        setContentView(R.layout.activity_welcome);
        wView = (WelcomeView)findViewById(R.id.welcome);
        //getFragmentManager();
    }

}
