package shao.widget;
/**
 * Created by shaozhaoyang on 2017/4/11.
 */

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.lang.reflect.Field;

import shao.manager.ManagerUtils;

public abstract class SuspendView extends LinearLayout {

    private int sensitivity = 3;
    private int statusBarHeight;
    private Context context;
    public int viewWidth, viewHeight;
    private int startX, startY;
    private int endX, endY;
    private WindowManager.LayoutParams params;

    public SuspendView(Context context) {
        this(context, null);
    }

    public SuspendView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SuspendView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
    }

    public View onCreate(@LayoutRes int layoutResID) {
        statusBarHeight = getStatusBarHeight();
        View view = LayoutInflater.from(context).inflate(layoutResID, this);
        LinearLayout layout = (LinearLayout) ((ViewGroup) view).getChildAt(0);
        viewWidth = layout.getLayoutParams().width;
        viewHeight = layout.getLayoutParams().height;
        params = ManagerUtils.getParams(viewWidth, viewHeight);
        return view;
    }

    private int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
            return 75;
        }
    }

    /**
     * 设置滑动灵敏度
     * @param sensitivity
     */
    public void setSensitivity(int sensitivity) {
        this.sensitivity = sensitivity;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                if (Math.abs(startX - event.getRawX()) > sensitivity || Math.abs(startY - event.getRawY()) > sensitivity) {
                    params.x = (int) (event.getRawX() - viewWidth / 2);
                    params.y = (int) (event.getRawY() - viewHeight / 2 - statusBarHeight);
                    ManagerUtils.getManagerInstance(context).updateViewLayout(this, params);
                    return true;
                }
            case MotionEvent.ACTION_UP:
                endX = (int) event.getRawX();
                endY = (int) event.getRawY();
                if (Math.abs(startX - endX) > sensitivity && Math.abs(startY - endY) > sensitivity) {
                    return true;
                }
        }
        return super.dispatchTouchEvent(event);
    }
}
