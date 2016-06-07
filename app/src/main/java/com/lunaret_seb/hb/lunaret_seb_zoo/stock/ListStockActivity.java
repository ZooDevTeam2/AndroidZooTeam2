package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.lunaret_seb.hb.lunaret_seb_zoo.R;
import java.util.ArrayList;
import java.util.List;

public class ListStockActivity extends Activity {
    Stock stock1 = new Stock("Carotte",150,500);
    Stock stock2 = new Stock("Poisson",70,350);
    Stock stock3 = new Stock("Viande",50,400);

    List<Stock> listStock = new ArrayList<>();
    List<String> listNameStock = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_stock);

        final TextView textList = (TextView)findViewById(R.id.text_list_app);
        textList.setText("Liste des stocks");

        listStock.add(stock1);
        listStock.add(stock2);
        listStock.add(stock3);

        for(int i=0; i < listStock.size();i++ ){
            listNameStock.add(listStock.get(i).getName());
        }
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this,
                R.layout.item_liste, R.id.text, listNameStock);

        ListView listView = (ListView) findViewById(R.id.list);
        //Here, the id of the view is linking directly on the id of the ListView of
        // the associated layout
        listView.setAdapter(adapter);
    }
}
