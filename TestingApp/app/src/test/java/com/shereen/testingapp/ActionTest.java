package com.shereen.testingapp;

import com.shereen.testingapp.domain.Action;
import com.shereen.testingapp.data.model.Calculation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by shereen on 12/31/18
 */

public class ActionTest {

    public Calculation getCalc(){
        Calculation calc = new Calculation("7","6","-");
        return calc;
    }

    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void conv_works_firstNumber(){
        Calculation calc = getCalc();
        assertEquals(7, Action.conv(calc.getFirstNumber()));
    }
    @Test
    public void conv_works_secondNumber(){
        Calculation calc = getCalc();
        assertEquals(6, Action.conv(calc.getSecondNumber()));
    }

    @Test
    public void operate_works_minus() {

        Calculation calc = getCalc();
        assertEquals("1", Action.operate(calc).getAnswer());
    }

    @Test
    public void operate_works_add() {

        Calculation calc = getCalc();
        calc.setOperator("+");
        assertEquals("13", Action.operate(calc).getAnswer());
    }

    @Test
    public void operate_works_multiply() {

        Calculation calc = getCalc();
        calc.setOperator("*");
        assertEquals("42", Action.operate(calc).getAnswer());
    }

}
