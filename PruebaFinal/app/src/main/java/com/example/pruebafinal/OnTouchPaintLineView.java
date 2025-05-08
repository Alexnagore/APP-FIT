package com.example.pruebafinal;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.content.res.Resources;
import android.view.View;

import androidx.core.content.res.ResourcesCompat;

public class OnTouchPaintLineView extends View {

    private Paint mPaint;
    private Path mPath;
    private int mDrawColor;
    private int mBackgroundColor;
    private Canvas mExtraCanvas;
    private Bitmap mExtraBitmap;

    // Constructor for inflating from XML
    public OnTouchPaintLineView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    // Constructor for programmatic use
    public OnTouchPaintLineView(Context context) {
        super(context);
        init(context);
    }

    private void init(Context context) {
        mBackgroundColor = ResourcesCompat.getColor(getResources(), android.R.color.white, null);
        mDrawColor = ResourcesCompat.getColor(getResources(), android.R.color.black, null);

        mExtraBitmap = Bitmap.createBitmap(getScreenWidth(), getScreenHeight(), Bitmap.Config.ARGB_8888);
        mExtraCanvas = new Canvas(mExtraBitmap);
        mExtraCanvas.drawColor(mBackgroundColor);

        mPath = new Path();
        mPaint = new Paint();
        mPaint.setColor(mDrawColor);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Dibuja el marco
        Paint framePaint = new Paint();
        framePaint.setColor(Color.BLACK);
        framePaint.setStyle(Paint.Style.STROKE);
        framePaint.setStrokeWidth(10);
        canvas.drawRect(0, 0, getWidth(), getHeight(), framePaint);

        // Dibuja el contenido
        canvas.drawBitmap(mExtraBitmap, 0, 0, null);
    }

    // Método para cambiar el color del pincel
    public void setDrawColor(int color) {
        mDrawColor = color;
        mPaint.setColor(mDrawColor);
    }

    private float mX, mY;

    private void touchStart(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    private void touchMove(float x, float y) {
        mPath.lineTo(x, y);
        mExtraCanvas.drawPath(mPath, mPaint);
    }

    private void touchUp() {
        mPath.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touchStart(x, y);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touchUp();
                break;
        }
        return true;
    }

    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
