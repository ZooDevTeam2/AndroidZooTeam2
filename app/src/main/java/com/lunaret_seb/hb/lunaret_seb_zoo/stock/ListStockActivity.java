package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.lunaret_seb.hb.lunaret_seb_zoo.R;
import java.util.ArrayList;
import java.util.List;

public class ListStockActivity extends AppCompatActivity {
    Stock stock1 = new Stock("Carotte",150,500);
    Stock stock2 = new Stock("Poisson",70,350);
    Stock stock3 = new Stock("Viande",50,400);

    List<Stock> listStock = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_stock);
        //Display of the text introduction of the activity
        final TextView textList = (TextView)findViewById(R.id.text_list_app);
        textList.setText("Liste des stocks");

        //Hard creation of the list of referenced stock
        listStock.add(stock1);
        listStock.add(stock2);
        listStock.add(stock3);

        //Initialization of the adapter
        ArrayAdapter<Stock> adapter= new ArrayAdapter<Stock>(this,
                       R.layout.item_liste, R.id.text, listStock);

        ListView listView = (ListView) findViewById(R.id.list);
        //Here, the id of the view is linking directly on the id of the ListView of
        // the associated layout
        //Then, send to the view
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListStockActivity.this, DetailStockActivity.class).putExtra("stock", listStock.get(position)  );
                startActivity(intent);
            }
        });
    }
}
