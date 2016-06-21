package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.lunaret_seb.hb.lunaret_seb_zoo.R;
import java.util.ArrayList;

public class ListStockActivity extends AppCompatActivity {

    public final static int REQ_CODE_CHILD = 1;

    private ArrayList<Stock> listStock;
    private StockCRUD stockCRUD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_stock);

        //Initialization of the listView form the xml
        final ListView listView = (ListView) findViewById(R.id.list);
        //Initialization of the custom adapter to display a list of the stock
        final ListStockAdapter adapter = new ListStockAdapter(this);
        //Initialisation of a custom Manager for stock.
        //The manager is in charge to bind the activity to the service
        StockManager manager = new StockManager();
        //Application of the manager method refreshList. This method links to the service to get the
        //updated list.
        manager.refreshList(ListStockActivity.this, adapter);
        //Then, set the adapter in the listView
        listView.setAdapter(adapter);

        //The goal of this component is to send a object via the put Extra to the Detail Activity.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Stock stock = (Stock) adapter.getItem(position);
               Intent intent = new Intent(ListStockActivity.this, DetailStockActivity.class);
               intent.putExtra("stock", stock);
               startActivity(intent);
            }
        });

        //BUTTON FOR ADDING A NEW STOCK
        FloatingActionButton fab_add_anim = (FloatingActionButton) findViewById(R.id.fab_add_anim);
        if (fab_add_anim != null) {
            fab_add_anim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ListStockActivity.this, FormAddStockActivity.class);
                    startActivityForResult(intent, REQ_CODE_CHILD);
                }
            });
        }
    }

    //@Override
  //  protected void onRestart() {

       // // TODO Auto-generated method stub
        //super.onRestart();
        //Intent intent = new Intent(ListStockActivity.this, ListStockActivity.class);
        //startActivity(i);
        //finish();

    //}
    // Method to start the service
    public void startService(View view) {
        startService(new Intent(getBaseContext(), ListStockIntentService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), ListStockIntentService.class));
    }
}

