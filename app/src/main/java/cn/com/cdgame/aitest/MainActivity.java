package cn.com.cdgame.aitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hankcs.hanlp.HanLP;

public class MainActivity extends AppCompatActivity {

    private android.widget.TextView textView;
    private android.widget.EditText editText;
    private android.widget.Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.button = (Button) findViewById(R.id.button);
        this.editText = (EditText) findViewById(R.id.editText);
        this.textView = (TextView) findViewById(R.id.textView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HanLP.segment("hi");
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      textView.setText(String.format("A9>%s", "初始化完成"));
                  }
              });
            }
        }).start();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HanLP.Config.ShowTermNature = false;



          //      textView.setText(String.format("A9>%s", bot.respond(editText.getText().toString())));

                // System.out.println(HanLP.segment(editText.getText().toString()));


            }
        });


    }
}
