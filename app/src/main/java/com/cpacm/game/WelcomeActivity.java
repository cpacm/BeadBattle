package com.cpacm.game;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.cpacm.game.util.ConstantUtil;
import com.cpacm.game.views.WelcomeView;

public class WelcomeActivity extends Activity {

    private WelcomeView wView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);//无标题模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//全屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//横屏*/
        getScreen();
        setContentView(R.layout.activity_welcome);
        wView = (WelcomeView)findViewById(R.id.welcome);
        //getFragmentManager();
    }

    public void getScreen(){
        DisplayMetrics metric = new DisplayMetrics();
        Display display;
        display = getWindowManager().getDefaultDisplay();
        display.getMetrics(metric);
        ConstantUtil.ScreenWidth = metric.widthPixels;
        ConstantUtil.ScreenHeight = metric.heightPixels;
        ConstantUtil.density = metric.density;
        ConstantUtil.DPI = metric.densityDpi;
        Log.d("TEST", ConstantUtil.ScreenWidth + " " + ConstantUtil.ScreenHeight + " " + metric.density + " " + ConstantUtil.DPI);
    }

}
