package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.support.design.widget.FloatingActionButton;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Listeanimaux extends AppCompatActivity {

    Animaux nanimal1 = new Animaux("totor", "Tiger",  new Date(2016,06,06));
    Animaux nanimal2 = new Animaux("tator", "Rabit",  new Date(2016,06,06));
    Animaux nanimal3 = new Animaux("totur", "Seale",  new Date(2016,06,06));
    Animaux nanimal4 = new Animaux("titir", "Horse",  new Date(2016,06,06));
    Animaux nanimal5 = new Animaux("tontonr", "Tiger",  new Date(2016,06,06));


    List<Animaux> listAnimaux = Arrays.asList(nanimal1, nanimal2, nanimal3, nanimal4, nanimal5);

    List<String> listAnimauxName = Arrays.asList(nanimal1.getName(), nanimal2.getName(), nanimal3.getName(), nanimal4.getName(), nanimal5.getName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_animaux);

        final TextView textList = (TextView)findViewById(R.id.text_list_app);
        textList.setText("Liste des animaux du zoooooooooooooooooooo");

        ArrayAdapter<Animaux> adapter = new ArrayAdapter<Animaux>(this,R.layout.item_liste, R.id.text, listAnimaux);
        ListView vueAnimaux = (ListView) findViewById(R.id.list);
        vueAnimaux.setAdapter(adapter);


        vueAnimaux.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Listeanimaux.this, DetailAnimal.class).putExtra("animal", listAnimaux.get(position)  );
                startActivity(intent);
            }
        });

        FloatingActionButton fab_add_anim = (FloatingActionButton) findViewById(R.id.fab_add_anim);
        fab_add_anim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Listeanimaux.this, FormAddAnimaux.class);
                startActivity(intent);
            }
        });

      //TODO RECUP DES INFO DU FORM D'AJOUT D'ANIMAUX POUR LES AFFICHER DANS LE TOAST
        // Toast.makeText(animalSTR"", "msg msg", Toast.LENGTH_LONG).show();
    }

}
