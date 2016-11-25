package net.cpacm.beadbattle;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

/**
 * @author: cpacm
 * @date: 2016/11/24
 * @desciption: 动画帮助类
 */

public class AnimationUtil {

    private static final int ANIMATION_DURATION = 300;

    private static AnimatorSet appearAnimation = new AnimatorSet().setDuration(ANIMATION_DURATION);

    public static void appearAnimation(BeadView beadView){

    }

}
