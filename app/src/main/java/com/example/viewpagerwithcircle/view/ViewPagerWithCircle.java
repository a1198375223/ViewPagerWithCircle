package com.example.viewpagerwithcircle.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class ViewPagerWithCircle extends ViewPager {
    public static final int MODE_CENTER = 1;                // center对齐模式
    public static final int MODE_RIGHT  = 2;                // right对齐模式
    public static final int MODE_LEFT   = 3;                // left对齐模式

    private int mMode                   = MODE_CENTER;      // 默认center对齐模式
    private int mSelectedColor          = Color.BLUE;       // 被选中页面的圆的颜色
    private int mUnselectedColor        = Color.GRAY;       // 未被选中页面对应圆的颜色
    private float mRadius               = 15.0f;            // 圆的默认半径
    private final float MAX_RADIUS      = 20f;              // 圆的最大半径
    private final float SMALL_RADIUS    = 5f;               // 圆的最小半径
    private int mLimitHeight            = 30;               // 与底部的距离
    private int mItemDistance           = 8;                // 两个圆之间的距离
    private Paint mCirclePaint;                             // 画圆的画笔
    private int mLeftOrRightMargin      = 20;               // 如果是左对齐模式或者右对齐模式, 设置一个距离边界的距离
    private boolean isRepeatScroll = false;                 // 设置是否支持连续滑动
    private int mActuallyCount;                             // 存储实际上的count值


    public ViewPagerWithCircle(@NonNull Context context) {
        this(context, null);
    }

    public ViewPagerWithCircle(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        // 在这个位置进行绘制, 确保内容绘制在最上层
        drawCircle(canvas);
    }

    private void drawCircle(Canvas canvas) {
        int count = getCount();
        if (count == 0 || count == 1) {
            return;
        }
        canvas.save();
        canvas.translate(getScrollX(), getScrollY());
        // 计算起始坐标和半径
        float density = getResources().getDisplayMetrics().density;
        // y起始坐标
        int y = getHeight() - (int) ((mLimitHeight) * density);
        int radius = dip2px(mRadius) / 2;

        // 起始x坐标
        int x = 0;
        if (mMode == MODE_CENTER) {
            x = (getWidth() - count * 2 * radius - (count - 1) * mItemDistance) / 2;
        } else if (mMode == MODE_LEFT) {
            x = getWidth() - count * 2 * radius - (count - 1) * mItemDistance - mLeftOrRightMargin;
        } else if (mMode == MODE_RIGHT) {
            x = mLeftOrRightMargin;
        }

        int selected = getSelectedItem();
        int dx = x;
        for (int i = 0; i < count; i++) {
            if (selected == i) {
                mCirclePaint.setColor(mSelectedColor);
            } else {
                mCirclePaint.setColor(mUnselectedColor);
            }
            canvas.drawCircle(dx + radius, y, radius, mCirclePaint);
            dx = dx + radius * 2 + mItemDistance;
        }

        canvas.restore();
    }


    private int getCount() {
        if (isRepeatScroll) {
            return mActuallyCount;
        } else {
            if (getAdapter() != null) {
                return getAdapter().getCount();
            }
            return 0;
        }
    }

    private int getSelectedItem() {
        int index = getCurrentItem();
        if (isRepeatScroll) {
            return index % mActuallyCount;
        }
        return index;
    }

    private int dip2px(float dip) {
        float density = getResources().getDisplayMetrics().density;
        return (int) (dip * density + 0.5f);
    }


    public void setMode(int mode) {
        this.mMode = mode;
    }

    public void cancelRepeatScroll() {
        this.isRepeatScroll = false;
    }

    public void setCanRepeatScroll(int actuallyCount) {
        this.isRepeatScroll = true;
        this.mActuallyCount = actuallyCount;
    }

    public void setSelectedColor(int selectedColor) {
        this.mSelectedColor = selectedColor;
    }

    public void setUnselectedColor(int unselectedColor) {
        this.mUnselectedColor = unselectedColor;
    }

    public void setRadius(float radius) {
        if (radius >= MAX_RADIUS) {
            this.mRadius = MAX_RADIUS;
        } else if (radius <= SMALL_RADIUS) {
            this.mRadius = SMALL_RADIUS;
        }else {
            this.mRadius = radius;
        }
    }

    public void setLimitHeight(int limitHeight) {
        this.mLimitHeight = limitHeight;
    }

    public void setItemDistance(int distance) {
        this.mItemDistance = distance;
    }

    public void setLeftOrRightMargin(int margin) {
        this.mLeftOrRightMargin = margin;
    }
}
