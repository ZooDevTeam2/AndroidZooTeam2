package com.lunaret_seb.hb.lunaret_seb_zoo.enclos;

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
import com.lunaret_seb.hb.lunaret_seb_zoo.animaux.FormAddAnimaux;

import java.util.ArrayList;
import java.util.List;

public class ListEnclos extends AppCompatActivity {
    Enclos stock1 = new Enclos("enclos 1",1500,"Situe dans la partie nord du zoo, superficie de 1500 m2 ");
    Enclos stock2 = new Enclos("enclos 2",70,"");
    Enclos stock3 = new Enclos("enclos 3",50,"Situe dans la partie sud du zoo, cet enclo..... blabla");

    List<Enclos> listEnclos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_stock);
        //Display of the text introduction of the activity
        final TextView textList = (TextView)findViewById(R.id.text_list_app);
        textList.setText("Liste des enclos");

        //Hard creation of the list of referenced stock
        listEnclos.add(stock1);
        listEnclos.add(stock2);
        listEnclos.add(stock3);
        
        ArrayAdapter<Enclos> adapter= new ArrayAdapter<Enclos>(this,
                       R.layout.item_liste, R.id.text, listEnclos);

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListEnclos.this, DetailEnclos.class).putExtra("enclos", listEnclos.get(position)  );
                startActivity(intent);
            }
        });


    }
}
