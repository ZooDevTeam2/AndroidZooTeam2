package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KEVIN on 20/06/2016.
 */
public class ListStockAdapter extends BaseAdapter{

    private Context context;
    private List<Stock> listStock ;

    public ListStockAdapter(Context context) {
        this.context = context;
        Stock stock = new Stock();
        stock.setName("Aucun stock");
        listStock = new ArrayList<Stock>();
        listStock.add(stock);
    }

    @Override
    public int getCount() {
        return listStock.size();
    }

    @Override
    public Object getItem(int position) {
        return listStock.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listStock.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.item_liste, parent, false);
        }
        // object item based on the position
        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textView = (TextView) convertView.findViewById(R.id.text);
        textView.setText(listStock.get(position).toString());
        return convertView;
    }

    public void setNewData(List<Stock> data) {
        listStock = data;
        notifyDataSetChanged();
    }
}
