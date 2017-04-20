package shao.customerview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by shaozhaoyang on 2017/4/20.
 */

public class MyView extends View {

    private Paint mPaint;
    private Drawable mDrawable;
    private int mWidth;
    private int mHeight;
    Rect bounds;

    private int textSize=40;
    String testString = "测试字符";

    public void setTextSize(int size){
        textSize=size;
        requestLayout();
        invalidate();
    }
    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(attrs);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
    }

    private void initAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray array = null;
            try {
                array = getContext().obtainStyledAttributes(attributeSet, R.styleable.MyView);
                mDrawable = array.getDrawable(R.styleable.MyView_src);
                measureDrawable();
            } finally {
                if (array != null) {
                    array.recycle();
                }
            }
        }
    }

    private void measureDrawable() {
        if (mDrawable == null) {
            throw new RuntimeException("drawable 不能为空");
        }
        mWidth = mDrawable.getIntrinsicWidth();
        mHeight = mDrawable.getIntrinsicHeight();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mPaint.setStrokeWidth(3);
        mPaint.setTextSize(textSize);
        mPaint.setColor(Color.RED);
        mPaint.setTextAlign(Paint.Align.LEFT);
        bounds = new Rect();
        mPaint.getTextBounds(testString, 0, testString.length(), bounds);
        mWidth = mWidth > bounds.width() ? mWidth : bounds.width();
        mHeight = mHeight > bounds.height() ? mHeight : bounds.height();
        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (mDrawable == null) {
            return;
        }
        canvas.drawBitmap(ImageUtils.drawableToBitmap(mDrawable), 0, 0, mPaint);
        canvas.drawText(testString, 0, bounds.height(), mPaint);
    }
}
