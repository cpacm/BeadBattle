package com.cpacm.game.beadbattle;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import com.cpacm.game.util.ConstantUtil;

public class BeadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getScreen();
        setContentView(R.layout.activity_bead);
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
