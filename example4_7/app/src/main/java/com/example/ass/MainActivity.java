package com.example.ass;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    CheckBox checkBox, checkBox2, checkBox3;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("연습문제 4-7");
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        button = findViewById(R.id.button);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                    button.setEnabled(true); // 버튼을 45도로 회전
                } else {
                    button.setEnabled(false); // 버튼의 회전을 초기화
                }
            }
        });

        checkBox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox2.isChecked()){
                    button.setClickable(true); // 버튼을 45도로 회전
                } else {
                    button.setClickable(false); // 버튼의 회전을 초기화
                }
            }
        });

        checkBox3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkBox3.isChecked()){
                    button.setRotation(45); // 버튼을 45도로 회전
                } else {
                    button.setRotation(0); // 버튼의 회전을 초기화
                }
            }
        });

    }
}
