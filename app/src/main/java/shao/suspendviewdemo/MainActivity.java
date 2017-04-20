package shao.suspendviewdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import shao.customerview.*;
import shao.customerview.MyView;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private shao.customerview.MyView myview;
    int size=40;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
/*        Intent intent=new Intent();
        intent.setClass(this,SuspendService.class);
        startService(intent);*/
        button=(Button)findViewById(R.id.bt_setTextSize);
        myview=(shao.customerview.MyView)findViewById(R.id.mv_myview);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myview.setTextSize(--size);
            }
        });
    }
}
