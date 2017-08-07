package cn.com.cdgame.aitest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import cn.com.cdgame.aitest.alice.Alice;

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


        final Alice A9 = new Alice.Bulider(MainActivity.this)
                .loadDataXml("A9.xml")
                .build();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("提取关键词"+HanLP.extractKeyword(editText.getText().toString(),4));
//                System.out.println("提取短语"+HanLP.extractPhrase(editText.getText().toString(),4));
//
                A9.talk(editText.getText().toString(), new Alice.TalkCallback() {
                    @Override
                    public void respond(String respond) {
                        textView.setText(String.format("A9>%s", respond));
                    }
                });
            }
        });


    }
}
