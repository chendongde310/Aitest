package cn.com.cdgame.aitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hankcs.hanlp.HanLP;

import cn.com.cdgame.aitest.alice.Alice;

public class LoadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        final Alice A9 = new Alice.Bulider(this)
                .loadDataXml("A9.xml")
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                HanLP.Config.ShowTermNature = true;
                HanLP.segment("hi");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(LoadActivity.this, MainActivity.class));
                    }
                });
            }
        }).start();


    }
}
