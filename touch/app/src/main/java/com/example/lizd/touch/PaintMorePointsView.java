package com.example.lizd.touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by lizd on 2017/8/23.
 */

public class PaintMorePointsView extends View {

    public PaintMorePointsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initPaintView();
    }

    public PaintMorePointsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaintView();
    }

    public PaintMorePointsView(Context context) {
        super(context);
        initPaintView();
    }

    public void clear( ){
        if( null != mPath ){
            mPath.reset( );
            invalidate( );
        }
    }

    private void initPaintView() {
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeWidth(5f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPath(mPath, mPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                mPath.moveTo(eventX, eventY);
                invalidate();
            }
            return true;
            case MotionEvent.ACTION_MOVE: {
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    float historicalX = event.getHistoricalX(i);
                    float historicalY = event.getHistoricalY(i);
                    mPath.lineTo(historicalX, historicalY);
                }

                mPath.lineTo(eventX, eventY);
                invalidate();
            }
            break;
            case MotionEvent.ACTION_UP: {
                int historySize = event.getHistorySize();
                for (int i = 0; i < historySize; i++) {
                    float historicalX = event.getHistoricalX(i);
                    float historicalY = event.getHistoricalY(i);
                    mPath.lineTo(historicalX, historicalY);
                }

                mPath.lineTo(eventX, eventY);
                invalidate();
            }
            break;
            default: {

            }
            return false;
        }

        return true;
    }

    private Paint mPaint = new Paint();
    private Path mPath = new Path();
}
