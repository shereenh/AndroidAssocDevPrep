package com.shereen.mylivedata.data.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.shereen.mylivedata.data.Constants;

/**
 * Created by shereen on 1/1/19
 */

@Entity(tableName = Constants.TABLE_NAME)
public class MyNumber {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private int number;

    @ColumnInfo(name = "cur_dt")
    private String curDT;

    @Ignore
    public MyNumber(int number, String curDT) {
        this.number = number;
        this.curDT = curDT;
    }

    public MyNumber() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getCurDT() {
        return curDT;
    }

    public void setCurDT(String curDT) {
        this.curDT = curDT;
    }

    @Override
    public String toString() {
        return " -> " +number + " generated at "+ curDT;
    }
}
