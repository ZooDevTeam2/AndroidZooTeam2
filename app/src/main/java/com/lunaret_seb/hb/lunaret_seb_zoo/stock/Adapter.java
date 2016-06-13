package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.widget.TextView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by KEVIN on 13/06/2016.
 */
public interface Adapter {
    void setNewData(List<Stock> data);
}

class ListStockAdapter implements Adapter{
    private List<Stock> data  = Collections.emptyList();
    private TextView view;


    @Override
    public void setNewData(List<Stock> data) {

    }
}
