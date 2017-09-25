package com.google.cal;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {


    EditText et_firstNum;
    EditText et_secNum;
    TextView tv_result;
    Spinner spin_operator;
    String operator = "+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        view_init();
    }

    private void view_init() {
        et_firstNum = (EditText) findViewById(R.id.first_num);
        et_secNum = (EditText) findViewById(R.id.sec_num);
        tv_result = (TextView) findViewById(R.id.result);
        spin_operator = (Spinner) findViewById(R.id.operator);

        spin_operator.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv = (TextView)view;
                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                String[] operators = MainActivity.this.getResources().getStringArray(R.array.operator);
                operator = operators[position];
//                Toast.makeText(MainActivity.this, "current operator : "+operator, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void bt_click(View view){

        switch (view.getId()){
            case R.id.cal:
                String str_firstNum = et_firstNum.getText().toString();
                String str_secNum = et_secNum.getText().toString();
                if ("".equals(str_firstNum)){
                    str_firstNum = "0";
                }
                if ("".equals(str_secNum)){
                    str_secNum = "0";
                }
                float firstNum = Float.valueOf(str_firstNum);
                float secNum = Float.valueOf(str_secNum);
                switch (operator){
                    case "+":
                        tv_result.setText((firstNum+secNum)+"");
                        break;
                    case "-":
                        tv_result.setText((firstNum-secNum)+"");
                        break;
                    case "*":
                        tv_result.setText((firstNum*secNum)+"");
                        break;
                    case "/":
                        if (0 == secNum){
                            tv_result.setText("除数不能为0");
                        }else {
                            tv_result.setText((firstNum/secNum)+"");
                        }
                        break;
                }
                break;
            case R.id.clear:
                et_firstNum.setText("");
                et_secNum.setText("");
                tv_result.setText("0");
                break;
            case R.id.exit:
                System.exit(0);
                break;

        }
    }
}
