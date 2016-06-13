package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
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
        //Display of the text introduction of the activity

        final TextView textList = (TextView) findViewById(R.id.text_list_app);
        textList.setText("Liste des stocks");
        //Hard creation of the list of referenced stock
        //Initialization of the adapter
        ArrayAdapter<Stock> adapter = new ArrayAdapter<Stock>(this,
                R.layout.item_liste, R.id.text, stockCRUD.retrieveAll(this));

        ListView listView = (ListView) findViewById(R.id.list);
        //Here, the id of the view is linking directly on the id of the ListView of
        // the associated layout
        //Then, send to the view
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListStockActivity.this, DetailStockActivity.class).putExtra("stock", listStock.get(position));
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

    @Override
    protected void onRestart() {

        // TODO Auto-generated method stub
        super.onRestart();
        Intent i = new Intent(ListStockActivity.this, ListStockActivity.class);
        startActivity(i);
        finish();

    }
    // Method to start the service
    public void startService(View view) {
        startService(new Intent(getBaseContext(), ListStockIntentService.class));
    }

    // Method to stop the service
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), ListStockIntentService.class));
    }
}

