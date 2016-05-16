package com.example.view_test;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Ls on 2016/5/10.
 */
public class mView extends View {
    private Paint paint = null, proPaint = null, textPaint = null;
    //容器的宽高
    private float mx, my;
    //半径
    private float radius;
    //圆的宽度
    private float strokeWidth = 80;
    //定义圆的左上角和右下角坐标的对象
    private RectF rectF = null;
    //进度条的起始值
    float sweepAngle;
    //字体大小
    private float textSize;
    //进度条的最大值
    private float max;
    //进度条的百分比
    private float percent;
    //进度额百分比数字
    private int num;
    //进度条底色
    private int roundColor;
    //进度条颜色
    private int proColor;

    public mView(Context context) {
        super(context);
    }

    public mView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取属性的数组
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.mView);
        textSize = array.getDimension(R.styleable.mView_textSize, 12);
        percent = array.getFloat(R.styleable.mView_percent, 50);
        roundColor = array.getColor(R.styleable.mView_roundColor, Color.YELLOW);
        proColor = array.getColor(R.styleable.mView_proColor, Color.RED);
        //回收数组资源
        array.recycle();
    }

    public mView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        //初始化画笔
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(roundColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
        mx = getWidth();
        my = getHeight();
        radius = (Math.min(mx, my) - strokeWidth) / 2;

        proPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        proPaint.setColor(proColor);
        proPaint.setStyle(Paint.Style.STROKE);
        proPaint.setStrokeWidth(strokeWidth);
        rectF = new RectF(mx / 2 - radius, my / 2 - radius, mx / 2 + radius, my / 2 + radius);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.DKGRAY);
        textPaint.setTextSize(textSize);

        max = (float) (percent * 3.6);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
        canvas.drawCircle(mx / 2, my / 2, radius, paint);
        canvas.drawArc(rectF, 0, sweepAngle, false, proPaint);
        canvas.drawText(String.valueOf(num) + "%", mx / 2 - textSize / 2, my / 2 + textSize / 2, textPaint);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (sweepAngle <= max) {

                    num = (int) (sweepAngle / 360 * 100);
                    sweepAngle += 2;
                    postInvalidate();
                    try {
                        Thread.currentThread().sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void setProgress(float progress){
        float pg = 10;
        this.percent = progress;
        if(progress > 100){
            this.percent  = 100;
        }
        // 直接在线程中更新界面
        postInvalidate();
    }
}
