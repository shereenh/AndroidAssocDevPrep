package com.shereen.testingapp.domain;

import com.shereen.testingapp.data.Constants;
import com.shereen.testingapp.data.model.Calculation;

/**
 * Created by shereen on 12/31/18
 */

public class Action {

    public static Calculation operate(Calculation calc){

        long result = 0;

        switch (calc.getOperator()){
            case Constants.PLUS : result = validate(calc.getFirstNumber()) + validate(calc.getSecondNumber()); break;
            case Constants.MINUS : result = validate(calc.getFirstNumber()) - validate(calc.getSecondNumber()); break;
            case Constants.MULTIPLY : result = validate(calc.getFirstNumber()) * validate(calc.getSecondNumber()); break;

        }
        calc.setAnswer(result+"");
        return calc;
    }

    public static long validate(Object obj){

        if(obj instanceof Long){
            return (Long)obj;
        }else if(obj instanceof String){
            return conv((String)obj);
        }
        else{
            return 0;
        }
    }

    public static long conv(String s){
        return Long.parseLong(s);
    }
}
