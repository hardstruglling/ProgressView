package com.jk.progressview.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

import com.jk.progressview.R;


public class ProgressView extends View {


    private int color;
    private static final int DEDULT_COLOR = Color.YELLOW;
    private static final int DEFAULT_SIZE = 3;
    private float size;
    private Paint mPaint;
    private int duration = 0;
    private float degress;
    private RectF rectF;
    private TextPaint textPaint;
    private static final int DEFAULT_TEXT_COLOR = Color.RED;
    private static final int DEFAULT_TEXT_SIZE = 16;
    private int textColor;
    private float textSize;
    private int num;
    private Rect rect;

    public ProgressView(Context context) {
        super(context);
        init();
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ProgressView);
        color = typedArray.getColor(R.styleable.ProgressView_circleColor, DEDULT_COLOR);
        size = typedArray.getDimension(R.styleable.ProgressView_circleSize, DEFAULT_SIZE);
        textColor = typedArray.getColor(R.styleable.ProgressView_textColor, DEFAULT_TEXT_COLOR);
        textSize = typedArray.getDimension(R.styleable.ProgressView_textSize, DEFAULT_TEXT_SIZE);
        typedArray.recycle();

        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(size);
        textPaint = new TextPaint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(textSize);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);

        rectF = new RectF(-150, -150, 150, 150);
        rect = new Rect();

    }

    public void setDuration(int duration) {
        this.duration = duration;
        invalidate();
    }

    /**
     * begin anim
     */
    public void start() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, 360);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                degress = (float) valueAnimator.getAnimatedValue();
                num = (int) (((float)valueAnimator.getAnimatedValue()*5)/18);
                invalidate();
            }
        });
        animator.setDuration(duration);
        animator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(getWidth()/2,getHeight()/2);
        String text = num+"%";
        textPaint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text,-(rect.width()/2),rect.height()/2,textPaint);
        canvas.drawArc(rectF, 90, degress, false, mPaint);
    }
}
