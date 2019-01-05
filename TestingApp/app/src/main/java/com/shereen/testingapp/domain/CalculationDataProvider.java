package com.shereen.testingapp.domain;

import com.shereen.testingapp.data.model.Calculation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shereen on 12/31/18
 */

public class CalculationDataProvider {

    public static List<Calculation> calculationList;

    static{
        calculationList = new ArrayList<>();

        calculationList.add(Action.operate(new Calculation("3","4","+")));

        calculationList.add(Action.operate(new Calculation("3","4","-")));

        calculationList.add(Action.operate(new Calculation("3","4","*")));
    }


}
