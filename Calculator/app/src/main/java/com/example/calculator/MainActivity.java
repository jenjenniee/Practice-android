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

    //    Button btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9;
    // 숫자 버튼 10개 관리하는 다른 방법 - 배열을 사용하여 숫자 버튼에 대한 ID 관리
    // Button 위젯의 객체를 저장할 Button 타입 배열 생성
    Button[] btnNums = new Button[10]; // => 메서드 내에서 반복문을 사용하여 ID 에 대한 객체를 저장
    // Button 위젯에 대한 ID 를 저장할 int형 배열 생성
    int[] btnNumsIds = { // 10개의 Button 에 대한 ID 를 배열에 직접 저장
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

        // 숫자 Button 위젯 10개에 대한 ID 가져오는 작업을 반복문으로 수행
        for(int i = 0; i < btnNumsIds.length; i++) { // ID가 저장된 배열 크기를 조건식으로 사용
            // Button 위젯의 ID 가 저장된 배열의 각 요소를 findViewById() 메서드에 전달하여
            // 해당 ID 에 대한 위젯 객체를 Button 타입 배열의 각 요소에 저장
            btnNums[i] = findViewById(btnNumsIds[i]);
        }
        // -----------------------------------------------------
        // 숫자 버튼 클릭 시 EditText 에 숫자 표시를 위한 이벤트 처리
        // => 숫자 버튼 클릭에 대한 작업이 동일하므로 반복문을 사용하여 동일 작업을 처리
        for(int i = 0; i < btnNumsIds.length; i++) {
            // 내부 클래스에서 외부에 있는 변수 접근 시 해당 변수가 final 이어야한다.
            // 그러나, 반복문의 제어변수에 final이 붙을 수 없으므로
            // 반복문 제어변수 값을 대신 저장할 변수 1개 필요
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

        // 계산결과 버튼 4단계로 구현
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

                // 입력된 숫자 2개 가져와셔 변수에 저장
                int num1 = Integer.parseInt(etNum1.getText().toString());
                int num2 = Integer.parseInt(etNum2.getText().toString());

                int result = 0;

                switch(v.getId()) { // 클릭된 View 타입 객체의 ID 가져오기
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

                // 연산 결과를 TextView에 출력
                tvResult.setText("계산 결과 : " + result); // "계산 결과 : "라는 문자열이 없었다면 int형이니 + "" 문자열 붙여줄 필요가 있음
            }
        };

        btnAdd.setOnClickListener(operatorListener);
        btnSub.setOnClickListener(operatorListener);
        btnMul.setOnClickListener(operatorListener);
        btnDiv.setOnClickListener(operatorListener);
    }
}