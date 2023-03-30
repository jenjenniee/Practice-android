package com.example.example4_8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button button;
    ImageView img;
    float degree = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button)findViewById(R.id.button);
        img = (ImageView) findViewById(R.id.img);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                degree = degree + 10;
                img.setRotation(degree);
            }
        });
    }
}