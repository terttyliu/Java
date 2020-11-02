package com.java基础.泛型;

import java.util.Date;

class Pair<T> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

public class DateInter extends Pair<Date> {

    @Override
    public void setValue(Date obj) {
        super.setValue(obj);
    }

    @Override
    public Date getValue() {
        return super.getValue();
    }

}

