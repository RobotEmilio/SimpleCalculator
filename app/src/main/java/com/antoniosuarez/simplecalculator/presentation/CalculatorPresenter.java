package com.antoniosuarez.simplecalculator.presentation;

import com.antoniosuarez.simplecalculator.R;
import com.antoniosuarez.simplecalculator.model.MathParser;

/**
 * Created by Antonio Suarez Galan on 19/04/17.
 *
 * This class manages the UI logic on the Calculator Activity
 */

public class CalculatorPresenter {

    private CalculatorActivity calculatorView;

    //This flags are used to prevent user to input erronous expressions
    //This is merely aesthetic, since operations itselves can generate
    //weird expressions that can crash the app
    private boolean number = false;
    private boolean decimalPointUsed = false;

    public CalculatorPresenter(CalculatorActivity calculatorActivity) {
        calculatorView = calculatorActivity;
    }

    //Resolves the math expression
    public void resolve(String s) {
        try {
            //We use the parser in model layer to resolve the math expression
            MathParser parser = new MathParser(s);
            calculatorView.clearScreenText();
            calculatorView.appendToScreenText(
                    String.valueOf(parser.getResult()));
        } catch (NumberFormatException e) {
            //In case we get an incorrect expression, we show "Error" on screen
            calculatorView.clearScreenText();
            calculatorView.appendToScreenText(
                    calculatorView.getString(R.string.R_string_errorMessage));
        }

    }

    //On click listener logic for number buttons
    public void numberPressed(String keyText) {
        calculatorView.appendToScreenText(keyText);
        //We set that last thing we input was a number
        number = true;
    }

    //On click listener logic for operator buttons
    public void operationPressed(String keyText) {
        if (number) {
            calculatorView.appendToScreenText(keyText);
        }

        number = false;
        decimalPointUsed = false;
    }

    //On click listener logic for decimal point button
    public void decimalPointPressed(String keyText) {
        if (number && !decimalPointUsed) {
            calculatorView.appendToScreenText(keyText);
            //We set that the decimal point has been used already for the current number being input
            decimalPointUsed = true;
        }
    }
}
