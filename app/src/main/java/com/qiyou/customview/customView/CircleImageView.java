package com.qiyou.customview.customView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import com.qiyou.customview.R;

/**
 * Created by QiYou
 * on 2019/3/21
 */
public class CircleImageView extends View {

    private int q_circle_color;
    private float q_cirlce_width;
    private Paint mPaint;
    private float radius;
    private int imgID;
    private Bitmap bitmap;
    private int size;
    private boolean q_show_border;


    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initCustomAttr(context, attrs);
        initPaint();
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initCustomAttr(context, attrs);
        initPaint();
    }

    private void initCustomAttr(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleImageView);
        imgID = typedArray.getResourceId(R.styleable.CircleImageView_q_img_scr, R.drawable.my);
        q_circle_color = typedArray.getColor(R.styleable.CircleImageView_q_ciecle_color, Color.BLACK);
        q_cirlce_width = typedArray.getDimension(R.styleable.CircleImageView_q_circle_border_width, 5);
        q_show_border = typedArray.getBoolean(R.styleable.CircleImageView_q_show_border, true);
        typedArray.recycle();
    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setColor(q_circle_color);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(q_cirlce_width);

        bitmap = resID2Bitmap(imgID);
    }

    private Bitmap resID2Bitmap(int resID) {
        return BitmapFactory.decodeResource(getResources(), resID);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        size = Math.min(getMeasuredWidth(), getMeasuredHeight());
        radius = size / 2;
        setMeasuredDimension(size, size);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (q_show_border) {
            canvas.drawCircle(radius, radius, radius, mPaint);
        }

        BitmapShader bitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        float mScale = radius * 2.0f / Math.min(bitmap.getWidth(), bitmap.getHeight());
        Matrix matrix = new Matrix();
        matrix.setScale(mScale, mScale);
        bitmapShader.setLocalMatrix(matrix);
        mPaint.setShader(bitmapShader);
        canvas.drawCircle(radius,
                radius,
                q_show_border ? radius - q_cirlce_width : radius,
                mPaint);
    }


}
