package com.cpacm.game.Bead;

import android.graphics.BitmapFactory;
import android.view.View;

import com.cpacm.game.beadbattle.R;

/**
 * Created by cpacm on 2014/10/9.
 */
public class GreenBead extends Bead {

    private BeadType type = BeadType.GREEN;

    public GreenBead(View view) {
        super(view);
        setBeadType(type);
    }

    public GreenBead(View view, float locX, float locY, int indexX, int indexY) {
        super(view, locX, locY, indexX, indexY);
        setBeadType(type);
    }

    @Override
    protected void initBitmap() {
        super.initBitmap();
        bitmap = BitmapFactory.decodeResource(view.getResources(), R.drawable.bead2);
    }
}
