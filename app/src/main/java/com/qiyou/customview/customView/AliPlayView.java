package com.qiyou.customview.customView;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.provider.ContactsContract;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.qiyou.customview.contant.Contants;

/**
 * Created by QiYou
 * on 2019/5/22
 */
public class AliPlayView extends View {

    private float radius = 100;
    private int centerX = 200;
    private int centerY = 200;
    private final Path circlePath;
    private final Paint paint;
    private final Path truePath;
    private final Path dstCirclePath;
    private final Path dstTruePath;
    private final PathMeasure circlePathMeasure;
    private final PathMeasure trueMeasure;
    private float circleCurValue;
    private float trueCurValue;
    private ValueAnimator trueAnimator;
    private Context context;
    private int viewStatue = -1;
    private ValueAnimator circleAnimator;
    private final Path warPath1;
    private final Path warPath2;
    private final Path dstWarPath1;
    private final Path dstWarPath2;
    private final PathMeasure warPathMeasure1;
    private final PathMeasure warPathMeasure2;
    private ValueAnimator warAnimator1;
    private float warCurValue1;
    private ValueAnimator warAnimator2;
    private float warCurValue2;

    public AliPlayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        setLayerType(LAYER_TYPE_SOFTWARE, null);
        this.context = context;


        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);

        circlePath = new Path();
        circlePath.addCircle(centerX, centerY, radius, Path.Direction.CW);

        truePath = new Path();
        truePath.moveTo(centerX - radius / 2, centerY);
        truePath.lineTo(centerX, centerY + radius / 2);
        truePath.lineTo(centerX + radius / 2, centerY - radius / 3);

        warPath1 = new Path();
        warPath1.moveTo(centerX - radius / 2, centerY - radius / 2);
        warPath1.lineTo(centerX + radius / 2, centerY + radius / 2);
        warPath2 = new Path();
        warPath2.moveTo(centerX + radius / 2, centerY - radius / 2);
        warPath2.lineTo(centerX - radius / 2, centerY + radius / 2);


        dstCirclePath = new Path();
        dstTruePath = new Path();
        dstWarPath1 = new Path();
        dstWarPath2 = new Path();

        circlePathMeasure = new PathMeasure(circlePath, false);
        trueMeasure = new PathMeasure(truePath, false);
        warPathMeasure1 = new PathMeasure(warPath1, false);
        warPathMeasure2 = new PathMeasure(warPath2, false);

    }

    private void aniMator(final int statue) {
        this.viewStatue = statue;

        circleAnimator = ValueAnimator.ofFloat(0, 1);
        circleAnimator.setDuration(2000);

        if (statue == Contants.ALIPLAY_LOADING) {
            circleAnimator.setRepeatCount(ValueAnimator.INFINITE);
            circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    circleCurValue = (float) valueAnimator.getAnimatedValue();
                    invalidate();
                }
            });
            circleAnimator.start();
        }

        if (statue == Contants.ALIPLAY_SUCCESS) {
            circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    circleCurValue = (float) valueAnimator.getAnimatedValue();
                    invalidate();
                    if (circleCurValue == 1) {
                        trueAnimator.start();
                    }
                }
            });
            circleAnimator.start();

            trueAnimator = ValueAnimator.ofFloat(0, 1);
            trueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    trueCurValue = (float) valueAnimator.getAnimatedValue();
                    invalidate();
                }
            });
            trueAnimator.setDuration(2000);
        }

        if (viewStatue == Contants.ALIPLAY_FAILED) {
            circleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    circleCurValue = (float) valueAnimator.getAnimatedValue();
                    invalidate();
                    if (circleCurValue == 1) {
                        warAnimator1.start();
                    }
                }
            });
            circleAnimator.start();

            warAnimator1 = ValueAnimator.ofFloat(0, 1);
            warAnimator1.setDuration(1000);
            warAnimator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    warCurValue1 = (float) valueAnimator.getAnimatedValue();
                    invalidate();
                    if (warCurValue1 == 1) {
                        warAnimator2.start();
                    }
                }
            });

            warAnimator2 = ValueAnimator.ofFloat(0, 1);
            warAnimator2.setDuration(1000);
            warAnimator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    warCurValue2 = (float) valueAnimator.getAnimatedValue();
                    invalidate();
                }
            });
        }

    }

    public void startLoading() {
        if (trueAnimator != null) {
            trueAnimator.cancel();
        }
        if (circleAnimator != null) {
            circleAnimator.cancel();
        }
        aniMator(Contants.ALIPLAY_LOADING);
    }

    public void success() {
        if (trueAnimator != null) {
            trueAnimator.cancel();
        }
        if (circleAnimator != null) {
            circleAnimator.cancel();
        }
        trueCurValue = 0;
        aniMator(Contants.ALIPLAY_SUCCESS);
    }

    public void failed() {
        if (trueAnimator != null) {
            trueAnimator.cancel();
        }
        if (circleAnimator != null) {
            circleAnimator.cancel();
        }
        warCurValue1 = 0;
        warCurValue2 = 0;
        aniMator(Contants.ALIPLAY_FAILED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (viewStatue == Contants.ALIPLAY_LOADING) {
            float loadStop = circleCurValue * circlePathMeasure.getLength();
            float loadStart = (float) (loadStop - (0.5 - Math.abs(circleCurValue - 0.5)) * circlePathMeasure.getLength());
            dstCirclePath.reset();
            circlePathMeasure.getSegment(loadStart, loadStop, dstCirclePath, true);
            canvas.drawPath(dstCirclePath, paint);
        }

        if (viewStatue == Contants.ALIPLAY_SUCCESS) {
            float circleStop = circlePathMeasure.getLength() * circleCurValue;
            dstCirclePath.reset();
            circlePathMeasure.getSegment(0, circleStop, dstCirclePath, true);
            canvas.drawPath(dstCirclePath, paint);

            float trueStop = trueMeasure.getLength() * trueCurValue;
            dstTruePath.reset();
            trueMeasure.getSegment(0, trueStop, dstTruePath, true);
            canvas.drawPath(dstTruePath, paint);
        }

        if (viewStatue == Contants.ALIPLAY_FAILED) {

            float circleStop = circlePathMeasure.getLength() * circleCurValue;
            dstCirclePath.reset();
            circlePathMeasure.getSegment(0, circleStop, dstCirclePath, true);
            canvas.drawPath(dstCirclePath, paint);

            float warStop1 = warCurValue1 * warPathMeasure1.getLength();
            dstWarPath1.reset();
            warPathMeasure1.getSegment(0, warStop1, dstWarPath1, true);
            canvas.drawPath(dstWarPath1, paint);

            float warStop2 = warCurValue2 * warPathMeasure2.getLength();
            dstWarPath2.reset();
            warPathMeasure2.getSegment(0, warStop2, dstWarPath2, true);
            canvas.drawPath(dstWarPath2, paint);
        }

    }
}
