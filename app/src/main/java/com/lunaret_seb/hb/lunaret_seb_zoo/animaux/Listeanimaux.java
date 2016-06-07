package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.app.ListActivity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

import java.util.Arrays;
import java.util.List;

public class Listeanimaux extends ListActivity {
    Animaux nanimal1 = new Animaux("totor");
    Animaux nanimal2 = new Animaux("tator");
    Animaux nanimal3 = new Animaux("totur");
    Animaux nanimal4 = new Animaux("titir");
    Animaux nanimal5 = new Animaux("tontonr");


    List<Animaux> listAnimaux = Arrays.asList(nanimal1, nanimal2, nanimal3, nanimal4, nanimal5);

    List<String> listAnimauxName = Arrays.asList(nanimal1.getName(), nanimal2.getName(), nanimal3.getName(), nanimal4.getName(), nanimal5.getName());


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_animaux);

        final TextView textList = (TextView)findViewById(R.id.text_list_app);
        textList.setText("Liste des animaux du zoooooooooooooooooooo");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.item_liste, R.id.text, listAnimauxName);
        setListAdapter(adapter);

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

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        Intent intent = new Intent(this, DetailAnimal.class).putExtra("animal", listAnimaux.get(position)  );
        startActivity(intent);

    }
}
