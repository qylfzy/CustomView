package com.qiyou.customview.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.qiyou.customview.R;

/**
 * Created by QiYou
 * on 2019/6/13
 */
public class BezierWaterView extends View {

    private int waveWidth = 600;
    private int waveWidth1 = 600;
    private int waveHeight = 100;
    private int waveHeight1 = 150;
    private float dx = 0;
    private Paint paint;
    private Paint paint1;
    private Path path;
    private Path mPath;
    private int waveNumber;

    public BezierWaterView(Context context) {
        super(context);
        init(context, null);
    }

    public BezierWaterView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BezierWaterView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(getResources().getColor(R.color.steelblue));
        paint.setStrokeWidth(4);

        paint1 = new Paint();
        paint1.setStyle(Paint.Style.FILL);
        paint1.setColor(getResources().getColor(R.color.dodgerblue));
        paint1.setStrokeWidth(4);

        path = new Path();
        mPath = new Path();
        startVanimator();


        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.BezierWaterView);
        waveNumber = array.getInteger(R.styleable.BezierWaterView_wave_number, 1);
        array.recycle();
    }

    private void startVanimator() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, waveWidth);
        animator.setDuration(2000);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                dx = (float) valueAnimator.getAnimatedValue();
                if (dx == waveWidth) {
                    dx = 0;
                }
                postInvalidate();
            }
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        path.moveTo(-waveWidth + dx, waveHeight / 2);
        for (int i = -waveWidth; i < getWidth() + waveWidth; i += waveWidth) {
            path.rQuadTo(waveWidth / 4, -waveHeight, waveWidth / 2, 0);
            path.rQuadTo(waveWidth / 4, waveHeight, waveWidth / 2, 0);
        }
        path.lineTo(getWidth(), getHeight());
        path.lineTo(0, getHeight());
        path.close();

        canvas.drawPath(path, paint);

        if (waveNumber == 2) {
            mPath.reset();
            mPath.moveTo(-waveWidth1 + dx, waveHeight1 / 2);
            for (int i = -waveWidth1; i < getWidth() + waveWidth1; i += waveWidth1) {
                mPath.rQuadTo(waveWidth1 / 4, -waveHeight1, waveWidth1 / 2, 0);
                mPath.rQuadTo(waveWidth1 / 4, waveHeight1, waveWidth1 / 2, 0);
            }
            mPath.lineTo(getWidth(), getHeight());
            mPath.lineTo(0, getHeight());
            mPath.close();
            canvas.drawPath(mPath, paint1);
        }


    }
}
