package com.antoniosuarez.simplecalculator.model;

/**
 * Created by Antonio Suarez Galan on 19/04/17.
 *
 * This class takes an expression, parses each part and resolves it, returning a double value
 */

public class MathParser {
    private double result;
    private String expression;

    public MathParser(String expression) throws NumberFormatException {
        this.expression = expression;
        result = resolve(expression);
    }

    public double resolve(String expression) throws NumberFormatException {
        try {
            int operatorPosition = expression.indexOf("+");
            //If there's an addition, then split the two parts of the expression and return the addition
            if (operatorPosition != -1) {
                String firstPart = expression.substring(0, operatorPosition);
                String secondPart = expression.substring(operatorPosition + 1, expression.length());
                return resolve(firstPart) + resolve(secondPart);
            } else {
                //If there's a substraction, split the two parts of the expression and return the substraction
                operatorPosition = expression.indexOf("-");
                if (operatorPosition != -1) {
                    String firstPart = expression.substring(0, operatorPosition);
                    String secondPart = expression.substring(operatorPosition + 1, expression.length());
                    return resolve(firstPart) - resolve(secondPart);
                } else {
                    //If no operator is shown, then it's a number
                    return Double.valueOf(expression);
                }
            }
        } catch (NumberFormatException e) {
            throw e;
        }
    }

    public double getResult() {
        return result;
    }
}
