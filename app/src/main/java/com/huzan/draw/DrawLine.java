package com.huzan.draw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by huzan on 16/3/22.
 */
public class DrawLine extends View {
    float mx;
    float my;
    Path path;
    Paint paint;

    public DrawLine(Context context) {
        super(context);
        paint = new Paint();
        path = new Path();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setColor(Color.GREEN);


    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {

            case MotionEvent.ACTION_DOWN:
                touchDown(event);
                break;
            case MotionEvent.ACTION_MOVE:
                touchMove(event);
                break;
        }
        invalidate();
        return true;
    }

    //滑动
    private void touchMove(MotionEvent event) {
        final float x = event.getX();
        final float y = event.getY();

        final float previousX = mx;
        final float previousY = my;

        final float dx = Math.abs(x - previousX);
        final float dy = Math.abs(y - previousY);

        //两点之间的距离大于等于3时，生成贝塞尔绘制曲线
        if (dx >= 3 || dy >= 3) {
            //设置贝塞尔曲线的操作点为起点和终点的一半
            float cX = (x + previousX) / 2;
            float cY = (y + previousY) / 2;

            //二次贝塞尔，实现平滑曲线；previousX, previousY为操作点，cX, cY为终点
            path.quadTo(previousX, previousY, cX, cY);
            mx = x;
            my = y;
        }


    }

    //按下
    private void touchDown(MotionEvent event) {
//        path.reset();
        float x = event.getX();
        float y = event.getY();

        mx = x;
        my = y;
        //mPath绘制的绘制起点
        path.moveTo(x, y);

    }

}
