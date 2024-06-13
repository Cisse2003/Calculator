package main.java.world;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Stack;

import static java.lang.Math.PI;
import static java.lang.Math.sqrt;

public class World extends SubjectToObserve{
    private String toCalculate;

    public World(){
        super();
        this.toCalculate = "";
    }


    // Getters
    public String getToCalculate() {
        return toCalculate;
    }

    // Setters
    public void setToCalculate(String toCalculate) {
        this.toCalculate = this.toCalculate + toCalculate;
        notifyObservers();
    }

    public void resetToCalculate(){
        this.toCalculate = "";
        notifyObservers();
    }

    // Evaluate the mathematical expression
    public String evaluateExpression() {
        try {
            double result = evaluate(toCalculate);
            return Double.toString(result);
        } catch (Exception e) {
            return "Error";
        }
    }

    // Custom method to evaluate the expression
    private double evaluate(String expression) {
        // Stack for numbers
        Stack<Double> numbers = new Stack<>();
        // Stack for operators
        Stack<Character> operations = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            // If the character is a digit, parse the number
            if (Character.isDigit(c)) {
                double number = 0;
                while (Character.isDigit(c) || c == '.') {
                    if (c == '.') {
                        i++;
                        double decimalPlace = 0.1;
                        while (i < expression.length() && Character.isDigit(expression.charAt(i))) {
                            number += (expression.charAt(i) - '0') * decimalPlace;
                            decimalPlace /= 10;
                            i++;
                        }
                        i--;
                    } else {
                        number = number * 10 + (c - '0');
                    }
                    i++;
                    if (i < expression.length()) {
                        c = expression.charAt(i);
                    } else {
                        break;
                    }
                }
                i--;
                numbers.push(number);
            } else if (c == '(') {
                operations.push(c);
            } else if (c == ')') {
                while (operations.peek() != '(') {
                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.pop();
            } else if (c == 'π') {
                numbers.push(PI);
            } else if (c == '√') {
                // Handle sqrt separately
                int startIndex = i + 1;
                int endIndex = findClosingBracket(expression, startIndex);
                if (endIndex != -1) {
                    double value = evaluate(expression.substring(startIndex + 1, endIndex));
                    numbers.push(sqrt(value));
                    i = endIndex;
                }
            } else if (isOperator(c)) {
                while (!operations.isEmpty() && hasPrecedence(c, operations.peek())) {
                    numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
                }
                operations.push(c);
            }
        }

        while (!operations.isEmpty()) {
            numbers.push(applyOperation(operations.pop(), numbers.pop(), numbers.pop()));
        }

        return numbers.pop();
    }

    private int findClosingBracket(String expression, int startIndex) {
        int bracketCount = 1;
        for (int i = startIndex + 1; i < expression.length(); i++) {
            if (expression.charAt(i) == '(') {
                bracketCount++;
            } else if (expression.charAt(i) == ')') {
                bracketCount--;
                if (bracketCount == 0) {
                    return i;
                }
            }
        }
        return -1;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '×' || c == '÷' || c == '√' || c == 'm';
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/' || op1 == '×' || op1 == '÷' || op1 == 'm') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    private double applyOperation(char op, double b, double a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': case '×': return a * b;
            case '/': case '÷': if (b == 0) throw new ArithmeticException("Cannot divide by zero"); return a / b;
            case 'm': return b == 0 ? 0 : a % b;
            default: return 0;
        }
    }

}
