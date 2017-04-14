package shao.manager;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by shaozhaoyang on 2017/4/11.
 */

public class ManagerUtils {
    private static WindowManager windowManager;
    private static WindowManager.LayoutParams params;

    public static WindowManager getManagerInstance(Context context) {
        return windowManager == null ? (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE) : windowManager;
    }

    public static WindowManager.LayoutParams getParams(int viewWidth, int viewHeight) {
        if (params == null) {
            params = new WindowManager.LayoutParams();
            params.type = WindowManager.LayoutParams.TYPE_PHONE;
            params.format = PixelFormat.RGBA_8888;
            params.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
            params.gravity = Gravity.LEFT | Gravity.TOP;
            params.width = viewWidth;
            params.height = viewHeight;
            params.x = 100;
            params.y = 100;
        }
        return params;
    }

    public static boolean removeView(Context context, View view) {
        if (view == null) return false;
        getManagerInstance(context).removeView(view);
        return true;
    }

    public static boolean addView(final Context context, View view, int width, int height, View.OnClickListener onClickListener) {
        if (view == null) return false;
        getManagerInstance(context).addView(view, getParams(width, height));
        view.setOnClickListener(onClickListener);
        return true;
    }
}
