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
 * on 2019/3/18
 */
public class FiveStarView extends View {


    private Paint mPaint;
    private Paint leftPaint;
    private Paint rightPaint;
    private float radius;
    private int centerX;
    private int centerY;
    private int mWidth;
    private int mHeight;
    private float angle = (float) (Math.PI * 2 / 5);//计算出每个夹角的度数
    private float secondRadius;

    public FiveStarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {

        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);

        leftPaint = new Paint();
        leftPaint.setColor(Color.BLUE);
        leftPaint.setStyle(Paint.Style.FILL);
        leftPaint.setStrokeWidth(10);

        rightPaint = new Paint();
        rightPaint.setColor(Color.YELLOW);
        rightPaint.setStyle(Paint.Style.FILL);
        rightPaint.setStrokeWidth(10);


        mWidth = context.getResources().getDisplayMetrics().widthPixels;
        mHeight = context.getResources().getDisplayMetrics().heightPixels;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        radius = Math.min(w, h) / 2 * 0.9f;
        secondRadius = radius / 2;
        centerX = w / 2;
        centerY = h / 2;
        postInvalidate();
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //外圆
//        canvas.drawCircle(centerX, centerY, radius, mPaint);
        //内圆
//        canvas.drawCircle(centerX, centerY, secondRadius, leftPaint);
//
//        /**
//         * 画外五点，既A、E、D、C、B 顺时针方向
//         * A-D-B-E-C-A
//         */
//        Path path = new Path();

        //A
//        path.moveTo(centerX, centerY);
//        path.lineTo(centerX, centerY - radius);

        //E
//        path.lineTo(centerX, centerY);
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(-18)) * radius, centerY + (float) Math.sin(Math.toRadians(-18)) * radius);

        //D
//        path.lineTo(centerX, centerY);
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(54)) * radius, centerY + (float) Math.sin(Math.toRadians(54)) * radius);

        //C
//        path.lineTo(centerX, centerY);
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(126)) * radius, centerY + (float) Math.sin(Math.toRadians(126)) * radius);

        //B
//        path.lineTo(centerX, centerY);
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(180 + 18)) * radius, centerY + (float) Math.sin(Math.toRadians(180 + 18)) * radius);

//        path.moveTo(centerX, centerY - radius);
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(54)) * radius, centerY + (float) Math.sin(Math.toRadians(54)) * radius);
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(180 + 18)) * radius, centerY + (float) Math.sin(Math.toRadians(180 + 18)) * radius);
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(-18)) * radius, centerY + (float) Math.sin(Math.toRadians(-18)) * radius);
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(126)) * radius, centerY + (float) Math.sin(Math.toRadians(126)) * radius);
//        path.close();
//        canvas.drawPath(path, mPaint);
//
//        //C
//        path.lineTo(centerX + (float) Math.cos(Math.toRadians(126)) * radius, centerY + (float) Math.sin(Math.toRadians(126)) * radius);
//        path.close();
//        canvas.drawPath(path, mPaint);
//
//        /**
//         * 画内五点，既F、G、H、I、K 顺时针方向 从A开始
//         */
//        Path path1 = new Path();
        //F
//        path1.moveTo(centerX, centerY);
//        path1.lineTo(centerX + (float) Math.cos(Math.toRadians(-(72 / 2 + 18))) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(-(72 / 2 + 18))) * secondRadius);

       //G
//        path1.lineTo(centerX, centerY);
//        path1.lineTo(centerX + (float) Math.cos(Math.toRadians(18)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(18)) * secondRadius);
        //H
//        path1.lineTo(centerX, centerY);
//        path1.lineTo(centerX + (float) Math.cos(Math.toRadians(90)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(90)) * secondRadius);

        //I
//        path1.lineTo(centerX, centerY);
//        path1.lineTo(centerX + (float) Math.cos(Math.toRadians(36 * 4 + 18)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(18 + 36 * 4)) * secondRadius);
        //K
//        path1.lineTo(centerX, centerY);
//        path1.lineTo(centerX + (float) Math.cos(Math.toRadians(36 * 6 + 18)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(18 + 36 * 6)) * secondRadius);
//        canvas.drawPath(path1, leftPaint);

        //把10个点连接起来 A-F-E-G-D-H-C-I-B-K-A
        Path path = new Path();
        path.moveTo(centerX, centerY - radius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(-(72 / 2 + 18))) * secondRadius,
                centerY + (float) Math.sin(Math.toRadians(-(72 / 2 + 18))) * secondRadius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(-18)) * radius, centerY + (float) Math.sin(Math.toRadians(-18)) * radius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(18)) * secondRadius,
                centerY + (float) Math.sin(Math.toRadians(18)) * secondRadius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(54)) * radius, centerY + (float) Math.sin(Math.toRadians(54)) * radius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(90)) * secondRadius,
                centerY + (float) Math.sin(Math.toRadians(90)) * secondRadius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(126)) * radius, centerY + (float) Math.sin(Math.toRadians(126)) * radius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(36 * 4 + 18)) * secondRadius,
                centerY + (float) Math.sin(Math.toRadians(18 + 36 * 4)) * secondRadius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(180 + 18)) * radius, centerY + (float) Math.sin(Math.toRadians(180 + 18)) * radius);
        path.lineTo(centerX + (float) Math.cos(Math.toRadians(36 * 6 + 18)) * secondRadius,
                centerY + (float) Math.sin(Math.toRadians(18 + 36 * 6)) * secondRadius);
        path.close();
        canvas.drawPath(path, mPaint);

        //填充左一半  A-K-B-I-C-H
//        Path leftPath = new Path();
//        leftPath.moveTo(centerX, centerY - radius);
//        leftPath.lineTo(centerX + (float) Math.cos(Math.toRadians(36 * 6 + 18)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(18 + 36 * 6)) * secondRadius);
//        leftPath.lineTo(centerX + (float) Math.cos(Math.toRadians(180 + 18)) * radius, centerY + (float) Math.sin(Math.toRadians(180 + 18)) * radius);
//        leftPath.lineTo(centerX + (float) Math.cos(Math.toRadians(36 * 4 + 18)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(18 + 36 * 4)) * secondRadius);
//        leftPath.lineTo(centerX + (float) Math.cos(Math.toRadians(126)) * radius, centerY + (float) Math.sin(Math.toRadians(126)) * radius);
//        leftPath.lineTo(centerX + (float) Math.cos(Math.toRadians(90)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(90)) * secondRadius);
//        leftPath.close();
//        canvas.drawPath(leftPath, leftPaint);


        //填充右一半 A-F-E-G-D-H
//        Path rightPath = new Path();
//        rightPath.moveTo(centerX, centerY - radius);
//        rightPath.lineTo(centerX + (float) Math.cos(Math.toRadians(-(72 / 2 + 18))) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(-(72 / 2 + 18))) * secondRadius);
//        rightPath.lineTo(centerX + (float) Math.cos(Math.toRadians(-18)) * radius, centerY + (float) Math.sin(Math.toRadians(-18)) * radius);
//        rightPath.lineTo(centerX + (float) Math.cos(Math.toRadians(18)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(18)) * secondRadius);
//        rightPath.lineTo(centerX + (float) Math.cos(Math.toRadians(54)) * radius, centerY + (float) Math.sin(Math.toRadians(54)) * radius);
//        rightPath.lineTo(centerX + (float) Math.cos(Math.toRadians(90)) * secondRadius,
//                centerY + (float) Math.sin(Math.toRadians(90)) * secondRadius);
//        rightPath.close();
//        canvas.drawPath(rightPath, rightPaint);

    }


}
