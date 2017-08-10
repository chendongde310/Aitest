package cn.com.cdgame.aitest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.hankcs.hanlp.HanLP;

public class LoadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        new Thread(new Runnable() {
            @Override
            public void run() {
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
