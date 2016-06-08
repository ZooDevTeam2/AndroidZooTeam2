package com.lunaret_seb.hb.lunaret_seb_zoo.animaux;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

import java.util.Date;

public class FormAddAnimaux extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_animaux);
        final TextView textTitle = (TextView)findViewById(R.id.title_form_add_anim);
        textTitle.getText();

        final EditText fieldName = (EditText)findViewById(R.id.form_field_name_add_anim);
        final EditText fieldSpecie = (EditText)findViewById(R.id.form_field_specie_add_anim);
        final EditText fieldAge = (EditText)findViewById(R.id.form_field_age_add_anim);

        final String animalString = fieldName.getText() +"-"+ fieldSpecie.getText() +"-"+ fieldAge.getText();




        FloatingActionButton fab_add_anim_form = (FloatingActionButton) findViewById(R.id.fab_add_anim_form);
        if (fab_add_anim_form != null) {
            fab_add_anim_form.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    String[] separated = fieldAge.getText().toString().split("\\/");
                    int Year = Integer.valueOf(separated[0]);
                    int month= Integer.valueOf(separated[1]);
                    int day= Integer.valueOf(separated[2]);
                    Date dateForm = new Date(Year,month,day);
                    //For debug
                    // textTitle.setText(dateForm.toString());


                    final Animaux animalFromForm = new Animaux(""+fieldName.getText(),""+fieldSpecie.getText(),dateForm);
                    Intent intent = new Intent(FormAddAnimaux.this, Listeanimaux.class).putExtra("animalFromForm",animalFromForm);

                    setResult(Activity.RESULT_OK, intent);
                    finish();
                   // startActivity(intent);
                }
            });
        }
    }


}
