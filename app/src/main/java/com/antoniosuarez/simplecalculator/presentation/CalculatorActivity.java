package com.antoniosuarez.simplecalculator.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;

import com.antoniosuarez.simplecalculator.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Antonio Suarez Galan on 19/04/17.
 *
 * This class is the main UI of the Calculator App
 */
public class CalculatorActivity extends AppCompatActivity {

    @BindView(R.id.editText) EditText screen;

    private CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        ButterKnife.bind(this);
        setFields();
        setViews();
    }

    private void setFields() {
        presenter = new CalculatorPresenter(this);
    }

    private void setViews() {
        //Disable input
        screen.setInputType(InputType.TYPE_NULL);
        //Initiate value
        screen.setText("0");

    }

    //Add value to calculator screen
    public void appendToScreenText(String text) {
        if (screen.getText().toString().equalsIgnoreCase("0")) {
            screen.setText("");
        }
        screen.append(text);
    }

    //Reset the calculator screen
    @OnClick(R.id.button_clear)
    public void clearScreenText() {
        screen.setText("0");
    }

    //On click listener for number buttons
    @OnClick({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9})
    public void numberPressed(Button b) {
        String keyText = b.getText().toString();
        presenter.numberPressed(keyText);
    }

    //On click listener for operation buttons
    @OnClick({R.id.button_substract, R.id.button_add})
    public void operationPressed(Button b) {
        String keyText = b.getText().toString();
        presenter.operationPressed(keyText);
    }

    //On click listener for decimal point button
    @OnClick(R.id.button_decimalPoint)
    public void decimalPointPressed(Button b) {
        String keyText = b.getText().toString();
        presenter.decimalPointPressed(keyText);
    }

    //On click listener for equal button
    @OnClick(R.id.button_equals)
    public void resolve() {
        presenter.resolve(screen.getText().toString());
    }
}
