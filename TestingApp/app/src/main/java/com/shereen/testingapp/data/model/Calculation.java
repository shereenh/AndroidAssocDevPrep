package com.shereen.testingapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;


import com.shereen.testingapp.data.Constants;

/**
 * Created by shereen on 12/31/18
 */
@Entity(tableName = Constants.CALC_TABLE_NAME)
public class Calculation {

    @PrimaryKey(autoGenerate = true)
    private long calculationId;

    private String firstNumber;

    private String secondNumber;

    private String operator;

    private String answer;

    public Calculation() {
    }

    @Ignore
    public Calculation(String firstNumber, String secondNumber, String operator) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
        this.operator = operator;
    }

    public long getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(long calculationId) {
        this.calculationId = calculationId;
    }

    public String getFirstNumber() {
        return firstNumber;
    }

    public void setFirstNumber(String firstNumber) {
        this.firstNumber = firstNumber;
    }

    public String getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(String secondNumber) {
        this.secondNumber = secondNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }


    @Override
    public String toString() {

        return  calculationId + ": " + firstNumber + " " + operator + " " + secondNumber + " = " + answer;
    }
}
