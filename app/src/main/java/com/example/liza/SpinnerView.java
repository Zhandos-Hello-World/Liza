package com.example.liza;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Arrays;

public class SpinnerView extends View {
    private int height, width;
    private int padding;
    private int fontSize;
    private int numeralSpacing;
    private int radius;
    private Paint paint;
    private boolean isInt;
    private int[] numbers = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    private Rect rect = new Rect();
    public int[] values = new int[12];

    public SpinnerView(Context context) {
        super(context);
    }

    public SpinnerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SpinnerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void initClock() {
        height = getHeight();
        width = getWidth();
        padding = numeralSpacing + 60;
        fontSize = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                13, getResources().getDisplayMetrics());
        int min = Math.min(height, width);
        radius = min / 2 - padding;
        paint = new Paint();
        isInt = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!isInt) {
            initClock();
        }
        for (int i = 0; i < values.length; i++) {
            values[i] = (int)(Math.random() * 1000 + 0);
        }

        canvas.drawColor(Color.BLACK);
        drawCircle(canvas);
        drawCenter(canvas);
        drawNumerical(canvas);
        drawDivider(canvas);
        super.onDraw(canvas);
    }
    private void drawDivider(Canvas canvas) {
        for (int i:numbers) {
            double angle = (Math.PI * (i + 0.5) / 30.0 - Math.PI / 2.0) * 5f;

            canvas.drawLine(width / 2f, height / 2f,
                    (float)(width / 2.0 + Math.cos(angle) * (radius + padding - 10)),
                    (float)(height / 2.0 + Math.sin(angle) * (radius + padding - 10)),
                    paint);
        }
    }


    private void drawNumerical(Canvas canvas) {
        paint.setTextSize(fontSize);
        int index = -1;
        for (int i:numbers) {
            String temp = String.valueOf(values[++index]);
            paint.getTextBounds(temp, 0, temp.length(), rect);
            double angle = Math.PI / 6 * (i - 3);
            int x = (int) (width / 2 +  Math.cos(angle) * radius - rect.width() / 2);
            int y = (int) (height / 2 +  Math.sin(angle) * radius - rect.height() / 2) + 20;
            canvas.drawText(temp, x, y, paint);
        }
    }

    private void drawCenter(Canvas canvas) {
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width / 2f, height / 2f, 12, paint);
    }

    private void drawCircle(Canvas canvas) {
        paint.reset();
        paint.setColor(getResources().getColor(android.R.color.white));
        paint.setStrokeWidth(5f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        canvas.drawCircle(width / 2f, height / 2f, radius + padding - 10, paint);
    }
}
