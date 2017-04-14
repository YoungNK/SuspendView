package shao.suspendviewdemo;

import android.view.View;
import android.widget.Toast;

import shao.manager.ManagerUtils;
import shao.service.SuspendViewService;

/**
 * Created by shaozhaoyang on 2017/4/13.
 */

public class SuspendService extends SuspendViewService {
    @Override
    public void setView() {
        MyView myView=new MyView(getBaseContext());
        ManagerUtils.addView(getBaseContext(), myView, myView.viewWidth, myView.viewHeight, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
