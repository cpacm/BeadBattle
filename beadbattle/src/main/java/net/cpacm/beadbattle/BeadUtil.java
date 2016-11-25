package net.cpacm.beadbattle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.util.LruCache;

import java.util.Random;

/**
 * Created by cpacm on 2014/10/25.
 */
public class BeadUtil {

    public final static int BEAD_TOTAL = 5;

    public final static int BEAD_RED = 0;
    public final static int BEAD_BLUE = 1;
    public final static int BEAD_GREEN = 2;
    public final static int BEAD_YELLOW = 3;
    public final static int BEAD_PURPLE = 4;

    public final static int BEAD_ANIMATION_RED = 5;
    public final static int BEAD_ANIMATION_BLUE = 6;
    public final static int BEAD_ANIMATION_GREEN = 7;
    public final static int BEAD_ANIMATION_YELLOW = 8;
    public final static int BEAD_ANIMATION_PURPLE = 9;

    public final static int MAX_MEMORY = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;

    private static final Random RNG = new Random();

    private static LruCache<Integer, Bitmap> bitmapCache = new LruCache<Integer, Bitmap>(MAX_MEMORY) {
        @Override
        protected int sizeOf(Integer key, Bitmap bitmap) {
            return bitmap.getByteCount() / 1024;
        }
    };

    public static Bitmap getBeadBitmap(Context context,int type){
        Bitmap bitmap = bitmapCache.get(type);
        if(bitmap == null){
            bitmap = initBeadBitmap(context,type);
        }
        return bitmap;
    }

    private static Bitmap initBeadBitmap(Context context,int type){
        @DrawableRes int resId;
        switch (type){
            case BEAD_RED:resId = R.drawable.bead1;break;
            case BEAD_BLUE:resId = R.drawable.bead2;break;
            case BEAD_GREEN:resId = R.drawable.bead3;break;
            case BEAD_YELLOW:resId = R.drawable.bead4;break;
            case BEAD_PURPLE:resId = R.drawable.bead5;break;
            case BEAD_ANIMATION_RED:resId = R.drawable.red;break;
            case BEAD_ANIMATION_BLUE:resId = R.drawable.blue;break;
            case BEAD_ANIMATION_GREEN:resId = R.drawable.green;break;
            case BEAD_ANIMATION_YELLOW:resId = R.drawable.yellow;break;
            case BEAD_ANIMATION_PURPLE:resId = R.drawable.purple;break;
            default:resId = R.drawable.bead1;break;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        bitmapCache.put(type,bitmap);
        return bitmap;
    }

    //随机的属性珠子
    public static int getRandomType() {
        int roll = RNG.nextInt(BEAD_TOTAL);
        return roll;
    }

    //获取珠子的动画bitmap编号
    public static int getBeadAnimationType(int type){
        return type + BEAD_TOTAL;
    }

}
