package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText etNum1, etNum2;
    Button btnAdd, btnSub, btnMul, btnDiv;
    TextView tvResult;


    Button[] btnNums = new Button[10];
    int[] btnNumsIds = {
            R.id.btnNum0, R.id.btnNum1, R.id.btnNum2, R.id.btnNum3, R.id.btnNum4,
            R.id.btnNum5, R.id.btnNum6, R.id.btnNum7, R.id.btnNum8, R.id.btnNum9
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);
        btnAdd = findViewById(R.id.btnAdd);
        btnSub = findViewById(R.id.btnSub);
        btnMul = findViewById(R.id.btnMul);
        btnDiv = findViewById(R.id.btnDiv);
        tvResult = findViewById(R.id.tvResult);

        for(int i = 0; i < btnNumsIds.length; i++) { // ID가 저장된 배열 크기를 조건식으로 사용

            btnNums[i] = findViewById(btnNumsIds[i]);
        }

        for(int i = 0; i < btnNumsIds.length; i++) {
            final int index = i;

            btnNums[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 현재 커서가 위치한 EditText 위젯을 판별 = isFocused() 메서드 활용
                    if(etNum1.isFocused()) {
                        etNum1.setText(etNum1.getText().toString() + btnNums[index].getText());
                    } else if(etNum2.isFocused()) {
                        etNum2.setText(etNum2.getText().toString() + btnNums[index].getText());
                    } else {
                        Toast.makeText(MainActivity.this, "숫자 입력창 선택 필수!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        View.OnClickListener operatorListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etNum1.length() == 0) {
                    Toast.makeText(MainActivity.this, "숫자1 입력 필수!", Toast.LENGTH_SHORT).show();
                    etNum1.requestFocus();
                    return;
                } else if(etNum2.length() == 0) {
                    Toast.makeText(MainActivity.this, "숫자2 입력 필수!", Toast.LENGTH_SHORT).show();
                    etNum2.requestFocus();
                    return;
                }

                // 입력된 숫자 2개 가져와셔 변수 저장
                int num1 = Integer.parseInt(etNum1.getText().toString());
                int num2 = Integer.parseInt(etNum2.getText().toString());

                int result = 0;

                switch(v.getId()) {
                    case R.id.btnAdd:
                        result = num1 + num2;
                        break;
                    case R.id.btnSub:
                        result = num1 - num2;
                        break;
                    case R.id.btnMul:
                        result = num1 * num2;
                        break;
                    case R.id.btnDiv:
                        if(num1 == 0) {
                            Toast.makeText(MainActivity.this, "0으로 나눌 수 없음!", Toast.LENGTH_SHORT).show();
                            etNum1.requestFocus();
                        } else if(num2 == 0) {
                            Toast.makeText(MainActivity.this, "0으로 나눌 수 없음!", Toast.LENGTH_SHORT).show();
                            etNum2.requestFocus();
                        }

                        result = num1 / num2;
                        break;
                }

                tvResult.setText("계산 결과 : " + result); // "계산 결과 : "라는 문자열이 없었다면 int형이니 + "" 문자열 붙여줄 필요가 있음
            }
        };

        btnAdd.setOnClickListener(operatorListener);
        btnSub.setOnClickListener(operatorListener);
        btnMul.setOnClickListener(operatorListener);
        btnDiv.setOnClickListener(operatorListener);
    }
}