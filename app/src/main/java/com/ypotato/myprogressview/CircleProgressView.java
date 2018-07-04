package com.ypotato.myprogressview;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.OvershootInterpolator;

/**
 * Created by Administrator on 2018/7/2.
 */

public class CircleProgressView extends View {
    /**
     * 文字画笔
     */
    private Paint textPaint;
    /**
     * 进度条画笔
     */
    private Paint progressPaint;
    /**
     * 动画
     */
    private ObjectAnimator animator;
    /**
     * 进度条所在矩阵
     */
    private RectF rectF;

    /**
     * 文字颜色
     */
    private String textColor = "#00ff00";
    /**
     * 进度条颜色
     */
    private String progressColor = "#E91E63";
    /**
     * 文字内容
     */
    private String textInfo;
    /**
     * 文字大小
     */
    private float textSize = 50;
    /**
     * 弧形半价
     */
    private float radius = 100;
    /**
     * 弧形粗细
     */
    private float progressWidth = 10;
    /**
     * 进度值
     */
    private float progress;

    public CircleProgressView(Context context) {
        super(context);
        init();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public CircleProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        rectF = new RectF();
        radius = dpToPixel(radius);
        textSize = dpToPixel(textSize);
        progressWidth = dpToPixel(progressWidth);

        //初始化文字画笔
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setTextSize(textSize);
        textPaint.setColor(Color.parseColor(textColor));
        textPaint.setTextAlign(Paint.Align.CENTER);

        //初始化进度条画笔
        progressPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        progressPaint.setColor(Color.parseColor(progressColor));
        progressPaint.setStrokeCap(Paint.Cap.ROUND);
        progressPaint.setStrokeWidth(progressWidth);
        progressPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float centerX = getWidth() / 2;
        float centerY = getHeight() / 2;

        canvas.save();
        //绘制进度条
        rectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        //从135度开始为0%,  100%转过的角度为270， 所以旋转角度为270 * progress/100
        canvas.drawArc(rectF, 135, 2.7f * progress, false, progressPaint);
        //绘制文字
        canvas.drawText((int)progress + "%", centerX, centerY - (textPaint.ascent() + textPaint.descent())/2, textPaint);
        canvas.restore();
    }

    private void start(float progress){
        animator = ObjectAnimator.ofFloat(this, "progress", 0, progress);
        animator.setDuration(1500);
        animator.setInterpolator(new OvershootInterpolator());
        animator.start();
    }

    private float getProgress() {
        return progress;
    }

    private void setProgress(float progress) {
        this.progress = progress;
        invalidate();
    }

    private float dpToPixel(float dp) {
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        return dp * metrics.density;
    }

    /**
     * 设置进度条百分比
     * @param progress
     */
    public void setCircleProgress(int progress){
        start(progress);
    }

    /**
     * 设置进度条的颜色
     * @param color
     */
    public void setProgressColor(String color){
        this.progressColor = color;
        progressPaint.setColor(Color.parseColor(textColor));
    }

    /**
     * 设置文字颜色
     * @param color
     */
    public void setTextColor(String color){
        this.textColor = color;
        textPaint.setColor(Color.parseColor(textColor));
    }

    /**
     * 设置文字大小
     * @param textSize
     */
    public void setTextSize(float textSize){
        this.textSize = textSize;
        textPaint.setTextSize(textSize);
    }

    /**
     * 设置进度条粗细
     * @param progressWidth
     */
    public void setProgressWidth(float progressWidth) {
        this.progressWidth = progressWidth;
        progressPaint.setStrokeWidth(progressWidth);
    }
}
