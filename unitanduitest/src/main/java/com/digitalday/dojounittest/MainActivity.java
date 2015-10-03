package com.digitalday.dojounittest;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.digitalday.dojounittest.validator.TwoDigitsIntegerValidator;
import com.digitalday.dojounittest.validator.Validator;

public class MainActivity extends Activity {

    private Calculator calculator;

    private EditText sumOp1;
    private EditText sumOp2;
    private Button sumButton;
    private TextView sumTotal;

    private EditText subtractOp1;
    private EditText subtractOp2;
    private Button subtractButton;
    private TextView subtractTotal;

    private EditText multiplyOp1;
    private EditText multiplyOp2;
    private Button multiplyButton;
    private TextView multiplyTotal;

    private EditText divisionOp1;
    private EditText divisionOp2;
    private Button divisionButton;
    private TextView divisionTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calculator = new Calculator();

        loadViews();
        setListeners();
    }

    private void loadViews() {
        sumOp1 = (EditText) findViewById(R.id.sumOp1);
        sumOp2 = (EditText) findViewById(R.id.sumOp2);
        sumButton = (Button) findViewById(R.id.buttonSum);
        sumTotal = (TextView) findViewById(R.id.sumTotal);

        subtractOp1 = (EditText) findViewById(R.id.subtractOp1);
        subtractOp2 = (EditText) findViewById(R.id.subtractOp2);
        subtractButton = (Button) findViewById(R.id.buttonSubtract);
        subtractTotal = (TextView) findViewById(R.id.subtractTotal);

        multiplyOp1 = (EditText) findViewById(R.id.multiplyOp1);
        multiplyOp2 = (EditText) findViewById(R.id.multiplyOp2);
        multiplyButton = (Button) findViewById(R.id.buttonMultiply);
        multiplyTotal = (TextView) findViewById(R.id.multiplyTotal);

        divisionOp1 = (EditText) findViewById(R.id.divisionOp1);
        divisionOp2 = (EditText) findViewById(R.id.divisionOp2);
        divisionButton = (Button) findViewById(R.id.buttonDivision);
        divisionTotal = (TextView) findViewById(R.id.divisionTotal);
    }

    private void setListeners() {
        sumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeSum();
            }
        });

        subtractButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeSubtraction();
            }
        });

        multiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeMultiplication();
            }
        });

        divisionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeDivision();
            }
        });
    }

    private void executeSum() {
        Validator validator = new TwoDigitsIntegerValidator();
        String op1 = sumOp1.getText().toString();
        String op2 = sumOp2.getText().toString();

        if (!validator.validate(op1)) {
            Toast.makeText(MainActivity.this, R.string.op1Invalid, Toast.LENGTH_LONG).show();
            sumTotal.setText(R.string.invalid);
            sumTotal.setTextColor(Color.RED);

            return;
        }

        if (!validator.validate(op2)) {
            Toast.makeText(MainActivity.this, R.string.op2Invalid, Toast.LENGTH_LONG).show();
            sumTotal.setText(R.string.invalid);
            sumTotal.setTextColor(Color.RED);

            return;
        }

        sumTotal.setText(Double.toString(calculator.sum(Integer.parseInt(op1), Integer.parseInt(op2))));
        sumTotal.setTextColor(Color.BLACK);
    }

    private void executeSubtraction() {
        Validator validator = new TwoDigitsIntegerValidator();
        String op1 = subtractOp1.getText().toString();
        String op2 = subtractOp2.getText().toString();

        if (!validator.validate(op1)) {
            Toast.makeText(MainActivity.this, R.string.op1Invalid, Toast.LENGTH_LONG).show();
            subtractTotal.setText(R.string.invalid);
            subtractTotal.setTextColor(Color.RED);

            return;
        }

        if (!validator.validate(op2)) {
            Toast.makeText(MainActivity.this, R.string.op2Invalid, Toast.LENGTH_LONG).show();
            subtractTotal.setText(R.string.invalid);
            subtractTotal.setTextColor(Color.RED);

            return;
        }

        subtractTotal.setText(Double.toString(calculator.substract(Integer.parseInt(op1), Integer.parseInt(op2))));
        subtractTotal.setTextColor(Color.BLACK);
    }

    private void executeMultiplication() {
        Validator validator = new TwoDigitsIntegerValidator();
        String op1 = multiplyOp1.getText().toString();
        String op2 = multiplyOp2.getText().toString();

        if (!validator.validate(op1)) {
            Toast.makeText(MainActivity.this, R.string.op1Invalid, Toast.LENGTH_LONG).show();
            multiplyTotal.setText(R.string.invalid);
            multiplyTotal.setTextColor(Color.RED);

            return;
        }

        if (!validator.validate(op2)) {
            Toast.makeText(MainActivity.this, R.string.op2Invalid, Toast.LENGTH_LONG).show();
            multiplyTotal.setText(R.string.invalid);
            multiplyTotal.setTextColor(Color.RED);

            return;
        }

        multiplyTotal.setText(Double.toString(calculator.multiply(Integer.parseInt(op1), Integer.parseInt(op2))));
        multiplyTotal.setTextColor(Color.BLACK);
    }

    private void executeDivision() {
        Validator validator = new TwoDigitsIntegerValidator();
        String op1 = divisionOp1.getText().toString();
        String op2 = divisionOp2.getText().toString();

        if (!validator.validate(op1)) {
            Toast.makeText(MainActivity.this, R.string.op1Invalid, Toast.LENGTH_LONG).show();
            divisionTotal.setText(R.string.invalid);
            divisionTotal.setTextColor(Color.RED);

            return;
        }

        if (!validator.validate(op2)) {
            Toast.makeText(MainActivity.this, R.string.op2Invalid, Toast.LENGTH_LONG).show();
            divisionTotal.setText(R.string.invalid);
            divisionTotal.setTextColor(Color.RED);

            return;
        }

        divisionTotal.setText(Double.toString(calculator.divide(Integer.parseInt(op1), Integer.parseInt(op2))));
        divisionTotal.setTextColor(Color.BLACK);
    }
}
