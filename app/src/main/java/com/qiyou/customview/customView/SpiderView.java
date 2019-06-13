package com.qiyou.customview.customView;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by QiYou
 * on 2019/3/21
 */
public class SpiderView extends View {

    private Paint gridPaint;
    private Paint valuePaint;
    private Paint circlePaint;
    private float radius;
    private int centerX;
    private int centerY;

    //蜘蛛网的层数
    private int count = 6;
    private double angle;
    private float spiderDistunce;

    public SpiderView(Context context) {
        super(context);
        init();
    }

    public SpiderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SpiderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //网格
        gridPaint = new Paint();
        gridPaint.setColor(Color.GREEN);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(10);

        //数据
        valuePaint = new Paint();
        valuePaint.setColor(Color.BLUE);
        valuePaint.setStyle(Paint.Style.FILL);
        valuePaint.setStrokeWidth(2);

        //参考圆
        circlePaint = new Paint();
        circlePaint.setColor(Color.RED);
        circlePaint.setStyle(Paint.Style.STROKE);
        circlePaint.setStrokeWidth(10);

        angle = 360 / count;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2 * 0.9f;
        centerX = w / 2;
        centerY = h / 2;
        //每层网格之间的距离
        spiderDistunce = radius / count;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //参考圆
        // canvas.drawCircle(centerX, centerY, radius, circlePaint);

        drawSpiderLine(canvas);
        drawCircleCenterLine(canvas);
        drawSpiderValue(canvas);
    }

    //网格线
    private void drawSpiderLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 1; i <= count; i++) {
            for (int j = 0; j < count; j++) {
                if (j == 0) {
                    path.moveTo((float) Math.cos(Math.toRadians(j * angle)) * (i * spiderDistunce) + centerX,
                            (float) Math.sin(Math.toRadians(j * angle)) * (i * spiderDistunce) + centerY);
                } else {
                    path.lineTo((float) Math.cos(Math.toRadians(j * angle)) * (i * spiderDistunce) + centerX,
                            (float) Math.sin(Math.toRadians(j * angle)) * (i * spiderDistunce) + centerY);
                }
            }
            path.close();
            canvas.drawPath(path, gridPaint);
        }

    }


    //中心到外直线
    private void drawCircleCenterLine(Canvas canvas) {
        Path path = new Path();
        for (int i = 0; i < count; i++) {
            path.moveTo(centerX, centerY);
            path.lineTo((float) Math.cos(Math.toRadians(i * angle)) * radius + centerX, (float) Math.sin(Math.toRadians(i * angle)) * radius + centerY);
        }
        canvas.drawPath(path, gridPaint);
    }

    //数据区域
    private void drawSpiderValue(Canvas canvas) {

        //先画数据中的五点
        Path path = new Path();
        valuePaint.setAlpha(127);
        for (int i = 0; i < count; i++) {
            float x = (float) (Math.cos(Math.toRadians(angle * i)) * (i + 1) * spiderDistunce + centerX);
            float y = (float) (Math.sin(Math.toRadians(angle * i)) * (i + 1) * spiderDistunce + centerY);
            canvas.drawCircle(x, y, 10, valuePaint);
            if (i == 0) {
                path.moveTo(x, y);
            } else {
                path.lineTo(x, y);
            }
        }
        valuePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawPath(path, valuePaint);
    }
}
