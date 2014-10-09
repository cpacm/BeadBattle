package com.cpacm.game.Bead;

import android.graphics.BitmapFactory;
import android.view.View;

import com.cpacm.game.beadbattle.R;

/**
 * Created by cpacm on 2014/10/9.
 */
public class RedBead extends Bead {

    private BeadType type = BeadType.RED;

    public RedBead(View view) {
        super(view);
    }

    @Override
    protected void initBitmap() {
        bitmap = BitmapFactory.decodeResource(view.getResources(), R.drawable.bead3);
    }
}
