package shao.suspendviewdemo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by shaozhaoyang on 2017/4/14.
 */

public class ToastUtil {
    public static void showToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}
