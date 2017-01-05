package net.cpacm.beadbattle;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.util.LruCache;

import java.util.Random;

/**
 * @author: cpacm
 * @date: 2014/10/25
 * @desciption: 利用 LruCache 缓存珠子图片
 *
 */
class BeadUtil {

    private final static int BEAD_TOTAL = 5;

    private final static int BEAD_RED = 0;
    private final static int BEAD_BLUE = 1;
    private final static int BEAD_GREEN = 2;
    private final static int BEAD_YELLOW = 3;
    private final static int BEAD_PURPLE = 4;

    final static int MAX_MEMORY = (int) (Runtime.getRuntime().maxMemory() / 1024) / 8;

    private static final Random RNG = new Random();

    private static LruCache<Integer, Bitmap> bitmapCache = new LruCache<Integer, Bitmap>(MAX_MEMORY) {
        @Override
        protected int sizeOf(Integer key, Bitmap bitmap) {
            return bitmap.getByteCount() / 1024;
        }
    };

    static Bitmap getBeadBitmap(Context context,int type){
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
            default:resId = R.drawable.bead1;break;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resId);
        bitmapCache.put(type,bitmap);
        return bitmap;
    }

    //随机的属性珠子
    static int getRandomType() {
        return RNG.nextInt(BEAD_TOTAL);
    }


}
