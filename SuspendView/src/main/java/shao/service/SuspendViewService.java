package shao.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;


/**
 * Created by shaozhaoyang on 2017/4/11.
 */

public abstract class SuspendViewService extends Service{

    @Override
    public void onCreate() {
        super.onCreate();
        setView();
    }

    public abstract void setView();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
