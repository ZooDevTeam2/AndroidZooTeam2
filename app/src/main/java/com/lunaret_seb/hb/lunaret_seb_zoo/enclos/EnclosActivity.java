package com.lunaret_seb.hb.lunaret_seb_zoo.enclos;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

import java.util.List;

public class EnclosActivity extends ListActivity {

    private TextView text;
    private DB myData = DB.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {



        List<Enclos> EnclosList = myData.getAll();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enclos);
        text = (TextView) findViewById(R.id.mainText);


        //update or add
        Intent myIntent= getIntent();
        Bundle b = myIntent.getExtras();
        if(b!=null) {
            Enclos enclos = (Enclos) b.get("myEnclos");
            String mode = (String) b.get("mode");
            int position = (int) b.get("position");
            if (mode.equals("new")){
                myData.add(enclos);
            }
            else if (mode.equals("update")){
                myData.update(enclos,position);
            }
            else{
                myData.remove(position);
            }
        }

        Button button= (Button) findViewById(R.id.AddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myDetailsIntent = new Intent(EnclosActivity.this, EnclosDetailsActivity.class);
                startActivity(myDetailsIntent);
            }
        });


        ArrayAdapter<Enclos> myAdapter = new ArrayAdapter <Enclos>(this,
                R.layout.rowlayout, R.id.listText, EnclosList);
        setListAdapter(myAdapter);
    }

    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        Enclos selectedItem = (Enclos) getListView().getItemAtPosition(position);
        //String selectedItem = (String) getListAdapter().getItem(position);
        Enclos enclos = myData.getByIndex(position);
        Intent myDetailsIntent = new Intent(this, EnclosDetailsActivity.class);
        myDetailsIntent.putExtra("myEnclos", enclos);
        myDetailsIntent.putExtra("mode", "update");
        myDetailsIntent.putExtra("position", position);
        startActivity(myDetailsIntent);
    }
}
