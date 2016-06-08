package com.lunaret_seb.hb.lunaret_seb_zoo.enclos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;
import com.lunaret_seb.hb.lunaret_seb_zoo.animaux.Listeanimaux;

/**
 * Created by youssefbee on 6/8/16.
 */
public class FormAddEnclos extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_enclos);
        final TextView textTitle = (TextView)findViewById(R.id.title_form_add_enclos);
        textTitle.getText();

        final EditText fieldName = (EditText)findViewById(R.id.form_field_name_add_enclos);
        final EditText fieldArea = (EditText)findViewById(R.id.form_field_Area_add_enclos);
        final EditText fieldDescription = (EditText)findViewById(R.id.form_field_Description_add_enclos);

        final String enclosString = fieldName.getText() +"-"+ fieldDescription.getText() +"-"+ fieldArea.getText();

        FloatingActionButton fab_add_enclos_form = (FloatingActionButton) findViewById(R.id.fab_add_enclos_form);
        fab_add_enclos_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FormAddEnclos.this, ListEnclos.class).putExtra("enclosSTR",enclosString);
                startActivity(intent);
            }
        });
    }
}
