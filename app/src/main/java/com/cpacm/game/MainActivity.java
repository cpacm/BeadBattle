package com.cpacm.game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;

import net.cpacm.beadbattle.BeadBattleLayout;

import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    private RelativeLayout relativeLayout;
    private BeadBattleLayout battleLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main);
        battleLayout = (BeadBattleLayout) findViewById(R.id.battle_layout);

    }

}
