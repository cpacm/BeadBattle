package net.cpacm.beadbattle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;

/**
 * @author: cpacm
 * @date: 2016/11/23
 * @desciption: 珠子视图
 */

public class BeadView extends View {

    // 五种状态：正常，显示，下落，选择和消失
    public final static int STATE_NORMAL = 0;
    public final static int STATE_APPEAR = 1;
    public final static int STATE_DROP = 2;
    public final static int STATE_SELECTED = 3;
    public final static int STATE_DISAPPEAR = 4;

    private static Interpolator appearInterpolator = new OvershootInterpolator();
    private static Interpolator deceInterpolator = new DecelerateInterpolator();
    private static Interpolator selectedInterpolator = new CycleInterpolator(6);

    private ObjectAnimator valueAnimator = new ObjectAnimator();
    private ObjectAnimator selectedAnimator = new ObjectAnimator();

    private Context context;
    //珠子的类型
    private int beadType;
    //珠子的id
    private int beadId;
    //珠子当前状态
    private int state = STATE_DISAPPEAR;

    private Rect src, dst;

    private Bitmap beadBitmap;

    private Paint paint;

    private int drop;

    public BeadView(Context context, int beadId) {
        super(context);
        this.context = context;
        this.beadId = beadId;
        initBead();
    }

    private void initBead() {
        if (paint == null) {
            paint = new Paint();
        }
        beadType = BeadUtil.getRandomType();
        beadBitmap = BeadUtil.getBeadBitmap(context, beadType);
        src = new Rect(0, 0, beadBitmap.getWidth(), beadBitmap.getHeight());
        drop = 0;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(widthSize, heightSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        dst = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(beadBitmap, src, dst, paint);
    }

    /**
     * 改变当前 BeadView 的状态
     *
     * @param state
     * @return
     */
    public boolean changeState(int state) {
        switch (state) {
            case STATE_APPEAR:
                return changeToAppear(state);
            case STATE_NORMAL:
                return changeToNormal(state);
            case STATE_DROP:
                return changeToDrop(state);
            case STATE_SELECTED:
                return changeToSelected(state);
            case STATE_DISAPPEAR:
                return changeToDisappear(state);
        }
        return false;
    }

    private boolean changeToAppear(int state) {
        if (getState() == STATE_DISAPPEAR) {
            setState(state);
            setAlpha(0f);
            return true;
        }
        return false;
    }

    private boolean changeToNormal(int state) {
        if (this.state == STATE_APPEAR || this.state == STATE_DROP || this.state == STATE_SELECTED) {
            setState(state);
            selectedAnimator.cancel();
            valueAnimator.cancel();
            setAlpha(1f);
            setScaleX(1f);
            setScaleY(1f);
            setTranslationY(0);
            setTranslationX(0);
            drop = 0;
            return true;
        }
        return false;
    }

    private boolean changeToDrop(int state) {
        if (this.state == STATE_NORMAL) {
            setState(state);
            return true;
        }
        return false;
    }

    private boolean changeToSelected(int state) {
        if (this.state == STATE_NORMAL) {
            setState(state);
            startSelectedAnimator();
            return true;
        }
        return false;
    }

    private boolean changeToDisappear(int state) {
        if (this.state == STATE_SELECTED) {
            setState(state);
            return true;
        }
        return false;
    }


    /**
     * 消失动画
     *
     * @return
     */
    public ObjectAnimator getDisappearAnimator() {
        if (!changeState(STATE_DISAPPEAR)) {
            return null;
        }
        selectedAnimator.cancel();
        valueAnimator = new ObjectAnimator();
        valueAnimator.setInterpolator(deceInterpolator);
        valueAnimator.setTarget(this);
        valueAnimator.setFloatValues(1f, 0f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                setAlpha(0.8f * value);
                setScaleX(0.8f * (value * 0.5f + 0.5f));// 0.8f-0.5f
                setScaleY(0.8f * (value * 0.5f + 0.5f));
                setRotation(90 * (1 - value));
                setTranslationY((1 - value) * 2 * getMeasuredHeight());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                setAlpha(0f);
                setRotation(0);
                setScaleX(1f);
                setScaleY(1f);
                initBead();
                postInvalidate();
            }
        });
        return valueAnimator;
    }

    /**
     * BeadView 的出现动画
     *
     * @return
     */
    public ObjectAnimator getAppearAnimator() {
        if (!changeState(STATE_APPEAR)) {
            return null;
        }
        valueAnimator = new ObjectAnimator();
        valueAnimator.setInterpolator(appearInterpolator);
        valueAnimator.setTarget(this);
        valueAnimator.setFloatValues(0f, 1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                setAlpha(value);
                setScaleX(value);
                setScaleY(value);
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                setTranslationY(0);
                setTranslationX(0);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                changeState(STATE_NORMAL);
                setAlpha(1f);
                setScaleX(1f);
                setScaleY(1f);
            }
        });
        return valueAnimator;
    }

    /**
     * BeadView选中的动画
     *
     * @return
     */
    public void startSelectedAnimator() {
        if (!changeState(STATE_SELECTED)) return;
        valueAnimator = new ObjectAnimator();
        valueAnimator.setInterpolator(deceInterpolator);
        valueAnimator.setTarget(this);
        valueAnimator.setFloatValues(1f, .8f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                setAlpha(value);
                setScaleX(value);
                setScaleY(value);
            }
        });
        selectedAnimator.setInterpolator(selectedInterpolator);
        selectedAnimator.setTarget(this);
        selectedAnimator.setProperty(View.TRANSLATION_X);
        selectedAnimator.setFloatValues(0f, 4f);
        selectedAnimator.setRepeatCount(Integer.MAX_VALUE);
        valueAnimator.start();
        selectedAnimator.start();
    }

    /**
     * 下落动画
     *
     * @return
     */
    public ObjectAnimator getDropAnimator() {
        if (!changeState(STATE_DROP)) {
            return null;
        }
        valueAnimator = new ObjectAnimator();
        valueAnimator.setInterpolator(deceInterpolator);
        valueAnimator.setTarget(this);
        valueAnimator.setFloatValues(1f, 0f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (Float) animation.getAnimatedValue();
                setTranslationY((1 - value) * drop * getMeasuredHeight());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                changeState(STATE_NORMAL);
            }
        });
        return valueAnimator;
    }


    public int getState() {
        return state;
    }

    private void setState(int state) {
        this.state = state;
    }

    public int getBeadType() {
        return beadType;
    }

    public int getBeadId() {
        return beadId;
    }

    public void setBeadId(int beadId) {
        this.beadId = beadId;
    }

    public int getDrop() {
        return drop;
    }

    public void addDropCount() {
        drop++;
    }
}
