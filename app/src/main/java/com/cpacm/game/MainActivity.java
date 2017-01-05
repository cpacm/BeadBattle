package com.cpacm.game;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.cpacm.beadbattle.BeadBattleLayout;

import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    private TextView scoreTv;
    private BeadBattleLayout battleLayout;

    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        battleLayout = (BeadBattleLayout) findViewById(R.id.battle_layout);
        scoreTv = (TextView) findViewById(R.id.bead_score);
        battleLayout.setOnBeadScoreListener(new BeadBattleLayout.OnBeadScoreListener() {
            @Override
            public void onBeadScore(int count) {
                score += count;
                scoreTv.setText("SCORE: " + score);
            }
        });
    }

}
