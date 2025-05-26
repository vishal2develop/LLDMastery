package CalculatorProblem;

import CalculatorProblem.Interfaces.ArithmeticExpression;

public class Number implements ArithmeticExpression {
    int number;
    public Number(int num){
        this.number = num;
    }

    @Override
    public int evaluate() {
        System.out.println("Number value is :" + number);
        return number;
    }
}
