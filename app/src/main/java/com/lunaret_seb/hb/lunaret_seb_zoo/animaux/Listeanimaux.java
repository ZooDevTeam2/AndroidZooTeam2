package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;
import com.lunaret_seb.hb.lunaret_seb_zoo.animauxCRUD.AnimauxCRUD;

import java.util.ArrayList;
import java.util.Date;

public class ListeAnimaux extends AppCompatActivity {

    public final static int REQ_CODE_CHILD = 1;
    /*
    private Animaux nanimal1 = new Animaux("totor", "Tiger",  new Date(2010,06,06));
    private Animaux nanimal2 = new Animaux("tator", "Rabit",  new Date(2011,06,06));
    private Animaux nanimal3 = new Animaux("totur", "Seale",  new Date(1999,06,06));
    private Animaux nanimal4 = new Animaux("titir", "Horse",  new Date(2002,06,06));
    private Animaux nanimal5 = new Animaux("tontonr", "Tiger",  new Date(2014,06,06));
*/

    private ArrayList<String> listAnimauxSTR = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_animaux);

        final TextView textList = (TextView)findViewById(R.id.text_list_app);
        textList.setText("Liste des animaux du zoooooooooooooooooooo");

//--à remplacer par le fait que le serviceAnimaux revoi une liste via le WebService----------------------
        AnimauxCRUD animauxCRUD = new AnimauxCRUD();
        final ArrayList<Animaux> listAnimaux = animauxCRUD.retrieveAll();

        for(Animaux animal : listAnimaux){
            listAnimauxSTR.add(animal.getName());
        }
//----------------------------------------

        ArrayAdapter<Animaux> adapter = new ArrayAdapter<Animaux>(this,R.layout.item_liste, R.id.text, listAnimaux);
        ListView vueAnimaux = (ListView) findViewById(R.id.list_anim);
        vueAnimaux.setAdapter(adapter);

        // creation of the service for the list
        Intent intentAnimService = new Intent (this, AnimauxService.class).putExtra("listAnim", listAnimauxSTR);
        startService(intentAnimService);


        //CLICK LISTENER, WHO SEND TO THE DETAIL ACTVITY WHEN WE CLICK ON A ROW OF THE LIST
        vueAnimaux.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(ListeAnimaux.this, listAnimaux.get(position).getName() , Toast.LENGTH_SHORT).show();
                Intent intentOnItemClick = new Intent(ListeAnimaux.this, DetailAnimal.class).putExtra("animalName", listAnimaux.get(position).getName());
                startActivity(intentOnItemClick);
            }
        });

        //BUTTON FOR ADDING A NEW ANIMAL
        FloatingActionButton fab_add_anim = (FloatingActionButton) findViewById(R.id.fab_add_anim);
        if (fab_add_anim != null) {
            fab_add_anim.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intentOnClickAdd = new Intent(ListeAnimaux.this, FormAddAnimaux.class);
                    startActivityForResult(intentOnClickAdd,REQ_CODE_CHILD);

                }
            });
        }


    }
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE_CHILD) {
            super.onActivityResult(requestCode, resultCode, data);

            String animalFormStr = (String) data.getStringExtra("animalFromForm");

            String[] separatedAnimaux = animalFormStr.split("\\*");
            String name = separatedAnimaux[0];
            String specie= separatedAnimaux[1];
            String dateOfBornStr= separatedAnimaux[2];


            String[] separated = dateOfBornStr.split("\\/");
            int Year = Integer.valueOf(separated[0]);
            int month= Integer.valueOf(separated[1]);
            int day= Integer.valueOf(separated[2]);
            Date dateOfBorn = new Date(Year,month,day);

            Animaux animalForm = new Animaux(name,specie,dateOfBorn);


            Toast.makeText(this, animalForm.getName()+" "+animalForm.getSpecie()+" "+animalForm.getDateOfBorn(), Toast.LENGTH_LONG).show();

        }


    }
}
