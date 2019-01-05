package com.shereen.testingapp.data;

/**
 * Created by shereen on 12/31/18
 */

public class Constants {

    public static final String DATABASE_NAME = "calculator_schema_1";
    public static final String CALC_TABLE_NAME = "calculation";
    public static final String PLUS = "+";
    public static final String MINUS = "-";
    public static final String MULTIPLY = "*";

    public static final int PAGE_SIZE = 15; // page size
    public static final int PAGE_INITIAL_LOAD_SIZE_HINT = 30; // page size on first load
    public static final int PAGE_PREFETCH_DISTANCE = 10; // triggers when to fetch a page

}
