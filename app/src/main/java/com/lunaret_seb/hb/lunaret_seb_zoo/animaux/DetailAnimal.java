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

        final TextView textList = (TextView)findViewById(R.id.text_detail);
        textList.setText(animal.toString());

        //TODO SPECIE AND AGE + ONCLICK ON THE BUTTON

        Button bouton;
        bouton= (Button) findViewById(R.id.btm);





    }
    public void onClickBtnSuppr(View v)
    {
        Toast.makeText(this, "Clicked on Button Suppr Animal", Toast.LENGTH_LONG).show();
    }


}
