package com.example.currency;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView textSign1, textSign2, text_Rate, textView1, textView2;
    Spinner spinner1, spinner2;
    ArrayAdapter<String> adapter;

    int unit1, unit2; // xac dinh units la gi ? 0:vnd , 1:usd , 2:won , 3:yen , 4:kip
    double op1, op2, rate;
    DecimalFormat df = new DecimalFormat("###,###,###.###");;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ket noi voi cac view va button
        textSign1 = findViewById(R.id.text_sign1);
        textSign2 = findViewById(R.id.text_sign2);
        textView1 = findViewById(R.id.text_view1);
        textView2 = findViewById(R.id.text_view2);
        text_Rate = findViewById(R.id.text_rate);
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_remove).setOnClickListener(this);
        findViewById(R.id.btn_ce).setOnClickListener(this);
        findViewById(R.id.btn_decimal).setOnClickListener(this);

        //khoi tao cac don vi
        rate = 0.0;
        textView1.setText("0");
        textView2.setText("0");
        unit1 = unit2 = 0; //mac dinh ban dau la vnd

        //tao cac don vi cho adapter
        String units[] = {"Vietnam - Dong", "United States - Dollar", "Korea - Won", "Japan - Yen", "Laos - Kip"};
        final String signs[] = {"₫", "$", "₩", "¥", "₭"};

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, units);

        spinner1 = findViewById(R.id.spinner_unit1);
        spinner2 = findViewById(R.id.spinner_unit2);

        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit1 = position;
                textSign1.setText(signs[position]);
                getRate();
                calculator();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                unit2 = position;
                textSign2.setText(signs[position]);
                getRate();
                calculator();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        //nhap so
        if(id == R.id.btn_0)
            addDigit(0);
        else if(id == R.id.btn_1)
            addDigit(1);
        else if(id == R.id.btn_2)
            addDigit(2);
        else if(id == R.id.btn_3)
            addDigit(3);
        else if(id == R.id.btn_4)
            addDigit(4);
        else if(id == R.id.btn_5)
            addDigit(5);
        else if(id == R.id.btn_6)
            addDigit(6);
        else if(id == R.id.btn_7)
            addDigit(7);
        else if(id == R.id.btn_8)
            addDigit(8);
        else if(id == R.id.btn_9)
            addDigit(9);
        else if(id == R.id.btn_ce)
            deleteCurrentOperator();
        else if(id == R.id.btn_decimal)
            convertDecimal();
        else if(id == R.id.btn_remove)
            removeDigit();

    }

    private void deleteCurrentOperator() {
        textView1.setText("0");
        textView2.setText("0");
    }

    private void removeDigit() {
        String s = textView1.getText().toString();
        s = s.substring(0, s.length() - 1);
        if(s.length() == 0)
            s = "0";
        textView1.setText(s);
        calculator();
    }

    private void convertDecimal() {
        String s = textView1.getText().toString();
        if (s.indexOf(".") == -1) {
            s = s + ".";
            textView1.setText(s);
            calculator();
        }
    }

    private void addDigit(int i) {
        String s = textView1.getText().toString();
        if(s.equals("0"))
            s = String.valueOf(i);
        else
            s = s.concat(String.valueOf(i));
        textView1.setText(s);
        calculator();
    }

    private void calculator() {
        op1 = Double.parseDouble(textView1.getText().toString());
        op2 = op1 * rate;
        textView2.setText(String.valueOf(df.format(op2)));
    }

    private void getRate() {
        if(unit1 == 0) { //doi tu VND --> ...
            switch (unit2) {
                case 0 : {
                    rate = 1.0;
                    text_Rate.setText("1 VND = " + String.valueOf(rate) + " VND");
                    break;
                }
                case 1 : {
                    rate = 0.00004261;
                    text_Rate.setText("1 VND = " + String.valueOf(rate) + " USD");
                    break;
                }
                case 2 : {
                    rate = 0.05163;
                    text_Rate.setText("1 VND = " + String.valueOf(rate) + " KRW");
                    break;
                }
                case 3 : {
                    rate = 0.004568;
                    text_Rate.setText("1 VND = " + String.valueOf(rate) + " JPY");
                    break;
                }
                case 4 : {
                    rate = 0.38;
                    text_Rate.setText("1 VND = " + String.valueOf(rate) + " LAK");
                    break;
                }
            }
        }
        else if(unit1 == 1) { //doi tu USD --> ...
            switch (unit2) {
                case 0 : {
                    rate = 23471.0;
                    text_Rate.setText("1 USD = " + String.valueOf(rate) + " VND");
                    break;
                }
                case 1 :
                    rate = 1.0;
                    text_Rate.setText("1 USD = " + String.valueOf(rate) + " USD");
                    break;
                case 2 :
                    rate = 1211.81;
                    text_Rate.setText("1 USD = " + String.valueOf(rate) + " KRW");
                    break;
                case 3 :
                    rate = 107.22;
                    text_Rate.setText("1 USD = " + String.valueOf(rate) + " JPY");
                    break;
                case 4 :
                    rate = 8918.17;
                    text_Rate.setText("1 USD = " + String.valueOf(rate) + " LAK");
                    break;
            }
        }
        else if(unit1 == 2) { //doi tu Won (KRW) --> ...
            switch (unit2) {
                case 0 : {
                    rate = 19.3685;
                    text_Rate.setText("1 KRW = " + String.valueOf(rate) + " VND");
                    break;
                }
                case 1 : {
                    rate = 0.0008252;
                    text_Rate.setText("1 KRW = " + String.valueOf(rate) + " USD");
                    break;
                }
                case 2 : {
                    rate = 1.0;
                    text_Rate.setText("1 KRW = " + String.valueOf(rate) + " KRW");
                    break;
                }
                case 3 : {
                    rate = 0.08848;
                    text_Rate.setText("1 KRW = " + String.valueOf(rate) + " JPY");
                    break;
                }
                case 4 : {
                    rate = 7.3594;
                    text_Rate.setText("1 KRW = " + String.valueOf(rate) + " LAK");
                    break;
                }
            }
        }
        else if(unit1 == 3) { //doi tu Yen (JPY)--> ...
            switch (unit2) {
                case 0 : {
                    rate = 218.9051;
                    text_Rate.setText("1 JPY = " + String.valueOf(rate) + " VND");
                    break;
                }
                case 1 : {
                    rate = 0.009327;
                    text_Rate.setText("1 JPY = " + String.valueOf(rate) + " USD");
                    break;
                }
                case 2 : {
                    rate = 11.3021;
                    text_Rate.setText("1 JPY = " + String.valueOf(rate) + " KRW");
                    break;
                }
                case 3 : {
                    rate = 1.0;
                    text_Rate.setText("1 JPY = " + String.valueOf(rate) + " JPY");
                    break;
                }
                case 4 : {
                    rate = 83.1764;
                    text_Rate.setText("1 JPY = " + String.valueOf(rate) + " LAK");
                    break;
                }
            }
        }
        else if(unit1 == 4) { //doi tu Kip (LAK) --> ...
            switch (unit2) {
                case 0 : {
                    rate = 2.6318;
                    text_Rate.setText("1 LAK = " + String.valueOf(rate) + " VND");
                    break;
                }
                case 1 : {
                    rate = 0.0001121;
                    text_Rate.setText("1 LAK = " + String.valueOf(rate) + " USD");
                    break;
                }
                case 2 : {
                    rate = 0.1359;
                    text_Rate.setText("1 LAK = " + String.valueOf(rate) + " KRW");
                    break;
                }
                case 3 : {
                    rate = 0.01202;
                    text_Rate.setText("1 LAK = " + String.valueOf(rate) + " JPY");
                    break;
                }
                case 4 : {
                    rate = 1.0;
                    text_Rate.setText("1 LAK = " + String.valueOf(rate) + " LAK");
                    break;
                }
            }
        }
    }

}
