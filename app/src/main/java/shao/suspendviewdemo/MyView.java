package shao.suspendviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import shao.widget.SuspendView;

/**
 * Created by shaozhaoyang on 2017/4/13.
 */

public class MyView extends SuspendView {
    private ImageView imageView, imageView2;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(final Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = onCreate(R.layout.layout_suspend);
        imageView=(ImageView)view.findViewById(R.id.img_float_window);
        imageView2=(ImageView)view.findViewById(R.id.img_float_window2);
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(context,"第一个Item");
            }
        });
        imageView2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtil.showToast(context,"第二个Item");
            }
        });
    }

}
