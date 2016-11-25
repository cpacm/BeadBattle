package net.cpacm.beadbattle;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author: cpacm
 * @date: 2016/11/23
 * @desciption: bead battle
 */

public class BeadBattleLayout extends ViewGroup {

    private static int APPEAR_SEQUENT_DURATION = 50;
    private static int APPEAR_TOGETHER_DURATION = 300;
    private static int DISAPPEAR_TOGETHER_DURATION = 500;
    private static int DISAPPEAR_SEQUENT_DURATION = 200;
    private static int DROP_TOGETHER_DURATION = 100;

    private AnimatorSet appearAnimation = new AnimatorSet();
    private AnimatorSet disappearAnimation = new AnimatorSet();
    private AnimatorSet dropAnimation = new AnimatorSet();

    private List<BeadView> appearAnimationList = new ArrayList<>();
    private List<BeadView> dropAnimationList = new ArrayList<>();
    private List<BeadView> disappearAnimationList = new ArrayList<>();
    private List<BeadView> selectedAnimationList = new ArrayList<>();

    private Context context;

    private int countX = 8;
    private int countY = 8;
    private SparseArray<BeadView> beadViewArray;
    private int beadSquare = 100;
    private boolean canTouch = true;

    public BeadBattleLayout(Context context) {
        this(context, null);
    }

    public BeadBattleLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BeadBattleLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initLayout();
    }

    private void initLayout() {
        beadViewArray = new SparseArray<>();
        appearAnimationList.clear();
        for (int i = countX * countY - 1; i >= 0; i--) {
            BeadView beadView = new BeadView(context, i);
            beadViewArray.put(i, beadView);
            appearAnimationList.add(beadView);
            addView(beadView);
        }
        appearAnimation(false);
    }

    /**
     * 根据行数和列数，对子view重新计算宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int perW = (width - getPaddingLeft() - getPaddingRight()) / countX;
        int perH = (height - getPaddingTop() - getPaddingBottom()) / countY;
        beadSquare = Math.min(perW, perH);
        for (int i = 0; i < getChildCount(); i++) {
            beadViewArray.get(i).measure(beadSquare, beadSquare);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        //排列珠子的位置
        for (int i = 0; i < countX; i++) {
            for (int j = 0; j < countY; j++) {
                BeadView beadView = beadViewArray.get(j * countX + i);
                int width = beadView.getMeasuredWidth();
                int height = beadView.getMeasuredHeight();
                int offsetX = (r - l - countX * width) / 2;
                int offsetY = (b - t - countY * height) / 2;
                beadView.layout(i * width + offsetX, j * height + offsetY, i * width + width + offsetX, j * height + height + offsetY);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (!canTouch) return false;
        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            selectedAnimationList.clear();
            int index = calculateIndexFromXY(motionEvent.getX(), motionEvent.getY());
            if (index != -1) {
                BeadView beadView = beadViewArray.get(index);
                addBeadAfterSelected(beadView);
            }
        } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
            int index = calculateIndexFromXY(motionEvent.getX(), motionEvent.getY());
            if (index != -1) {
                BeadView beadView = beadViewArray.get(index);
                addBeadAfterSelected(beadView);
            }
        } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            if (selectedAnimationList.size() < 2) {
                selectedAnimationList.get(0).changeState(BeadView.STATE_NORMAL);
                selectedAnimationList.clear();
            } else {
                calculateDropBead();
                disappearAnimation(true);
            }
        }
        return true;
    }

    /**************************
     * Game Rule
     ***************************/

    public int calculateIndexFromXY(float x, float y) {
        int offsetX = getPaddingLeft();
        int offsetY = getPaddingTop();
        int ix = ((int) x - offsetX) / beadSquare;
        int iy = ((int) y - offsetY) / beadSquare;
        if (ix < 0 || ix < 0) return -1;
        if (ix >= countX || iy >= countY) return -1;
        return ix + iy * countX;
    }

    /**
     * 判断是否添加到选择列表中
     */
    public void addBeadAfterSelected(BeadView beadView) {
        if (beadView.getState() != BeadView.STATE_NORMAL) {
            return;
        }
        if (selectedAnimationList.size() == 0) {
            selectedAnimationList.add(beadView);
            beadView.startSelectedAnimator();
            return;
        }
        if (selectedAnimationList.contains(beadView)) {
            return;
        }
        BeadView lastBeadView = selectedAnimationList.get(selectedAnimationList.size() - 1);
        if (lastBeadView.getBeadType() != beadView.getBeadType()) {
            return;
        }
        if (!sudoku(lastBeadView.getBeadId(), beadView.getBeadId())) {
            return;
        }
        selectedAnimationList.add(beadView);
        beadView.startSelectedAnimator();
    }

    /**
     * 添加要下落的珠子
     */
    public void calculateDropBead() {
        dropAnimationList.clear();
        for (BeadView beadView : selectedAnimationList) {
            int index = beadView.getBeadId();
            while (index >= countX) {
                index = index - countX;
                BeadView dropView = beadViewArray.get(index);
                dropView.addDropCount();
                if (!dropAnimationList.contains(dropView)) {
                    dropAnimationList.add(dropView);
                }
            }
        }
        calculateSwitchBead();
    }

    /**
     * 珠子index置换,避免错位
     */
    public void calculateSwitchBead() {
        for (BeadView beadView : dropAnimationList) {
            int sIndex = beadView.getBeadId() + countX * beadView.getDrop();
            BeadView sBeadView = beadViewArray.get(sIndex);
            sBeadView.setBeadId(beadView.getBeadId());
            beadViewArray.put(beadView.getBeadId(), sBeadView);
            beadView.setBeadId(sIndex);
            beadViewArray.put(sIndex, beadView);
        }
    }

    /**
     * 珠子连接判断
     *
     * @param lastIndex
     * @param index
     * @return
     */
    public boolean sudoku(int lastIndex, int index) {
        int x = lastIndex % countX;
        int y = lastIndex / countX;
        int lastLeft = (x - 1 >= 0) ? x - 1 : x;
        int lastTop = (y - 1 >= 0) ? y - 1 : y;
        int lastRight = (x + 1 < countX) ? x + 1 : x;
        int lastBottom = (y + 1 < countY) ? y + 1 : y;
        x = index % countX;
        y = index / countX;
        return x >= lastLeft && x <= lastRight && y >= lastTop && y <= lastBottom;
    }

    /**
     * 出现动画
     *
     * @param withAnimation
     */
    public void appearAnimation(boolean withAnimation) {
        if (appearAnimationList.size() == 0) {
            return;
        }
        appearAnimation = new AnimatorSet();
        List<Animator> animators = new ArrayList<>();
        for (BeadView beadView : appearAnimationList) {
            ObjectAnimator animator = beadView.getAppearAnimator();
            if (animator != null) {
                animators.add(animator);
            }
        }
        Collections.reverse(animators);
        if (withAnimation) {
            appearAnimation.setDuration(200);
            appearAnimation.setDuration(APPEAR_TOGETHER_DURATION);
            appearAnimation.playTogether(animators);
        } else {
            appearAnimation.setDuration(APPEAR_SEQUENT_DURATION);
            appearAnimation.playSequentially(animators);
        }
        appearAnimation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                canTouch = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                appearAnimationList.clear();
                canTouch = true;
            }
        });
        appearAnimation.start();
    }

    /**
     * 消失动画
     *
     * @param withAnimation
     */
    public void disappearAnimation(boolean withAnimation) {
        if (selectedAnimationList.size() == 0) {
            return;
        }
        disappearAnimation = new AnimatorSet();
        List<Animator> animators = new ArrayList<>();
        for (BeadView beadView : selectedAnimationList) {
            ObjectAnimator animator = beadView.getDisappearAnimator();
            if (animator != null) {
                animators.add(animator);
            }
        }
        if (withAnimation) {
            disappearAnimation.setDuration(DISAPPEAR_TOGETHER_DURATION);
            disappearAnimation.playTogether(animators);
        } else {
            disappearAnimation.setDuration(DISAPPEAR_SEQUENT_DURATION);
            disappearAnimation.playSequentially(animators);
        }
        disappearAnimation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                canTouch = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                canTouch = true;
                dropAnimation();
            }
        });
        disappearAnimation.start();
    }

    /**
     * 掉落动画
     */
    public void dropAnimation() {
        if (dropAnimationList.size() == 0) {
            return;
        }
        dropAnimation = new AnimatorSet();
        List<Animator> animators = new ArrayList<>();
        for (BeadView beadView : dropAnimationList) {
            ObjectAnimator animator = beadView.getDropAnimator();
            if (animator != null) {
                animators.add(animator);
            }
        }
        dropAnimation.setDuration(DROP_TOGETHER_DURATION);
        dropAnimation.playTogether(animators);
        dropAnimation.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                canTouch = false;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                canTouch = true;
                appearAnimationList.addAll(selectedAnimationList);
                dropAnimationList.clear();
                requestLayout();
                appearAnimation(true);
            }
        });
        dropAnimation.start();
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(beadSquare, beadSquare);
    }
}
