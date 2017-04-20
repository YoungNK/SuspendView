package shao.permission;

/**
 * Created by shaozhaoyang on 2017/4/14.
 */

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;
import android.os.Build;

import java.lang.reflect.Method;

/**
 * Thanks for DreamChaser.
 * Dependency:http://blog.csdn.net/liuzhuoran1110/article/details/52767952
 */

public class PermissionUtils {
    private static PermissionUtils instance = null;

    public static PermissionUtils getInstance() {
        if (instance == null)
            instance = new PermissionUtils();
        return instance;
    }

    /**
     * 检查是否获得悬浮窗权限
     *
     * @param context
     * @param op
     * @return
     */
    //OP_SYSTEM_ALERT_WINDOW=24   op = 24
    private boolean checkOp(Context context, int op) {
        final int version = Build.VERSION.SDK_INT;

        if (version >= 19) {
            AppOpsManager manager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
            try {

                Class<?> spClazz = Class.forName(manager.getClass().getName());
                Method method = manager.getClass().getDeclaredMethod("checkOp", int.class, int.class, String.class);
                int property = (Integer) method.invoke(manager, op,
                        Binder.getCallingUid(), context.getPackageName());

                if (AppOpsManager.MODE_ALLOWED == property) {
                    return true;
                } else {
                    return false;
                }
            } catch (Exception e) {

            }
        } else {

        }
        return true;
    }
}
