package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

public class DetailAnimal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_animal);

        Bundle extra = getIntent().getExtras();
        Animaux animal = (Animaux) extra.get("animal");

        final TextView textListName = (TextView)findViewById(R.id.text_detail_name);
        textListName.setText(animal.getName());
        final TextView textListSpecie = (TextView)findViewById(R.id.text_detail_specie);
        textListSpecie.setText(animal.getSpecie());
        final TextView textListAge = (TextView)findViewById(R.id.text_detail_age);
        textListAge.setText(""+animal.getAge());

        //TODO WHEN ONCLICK ON THE DELETE BUTTON, ASK FOR A CONFIRMATION AND THEN, DELETE THE CURRENT ANIMAL
        //TODO A MODIFY BUTTON + AN ACTIVITY FOR IT, WHERE YOU CAN UPDATE THE CURRENT OBJECT.
        Button bouton;
        bouton= (Button) findViewById(R.id.btm);





    }
    public void onClickBtnSuppr(View v)
    {
        Toast.makeText(this, "Clicked on Button Suppr Animal", Toast.LENGTH_LONG).show();
    }


}
