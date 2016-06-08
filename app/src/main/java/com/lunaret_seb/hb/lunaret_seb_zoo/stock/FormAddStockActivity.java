package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

public class FormAddStockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_stock);

    final TextView textTitle = (TextView)findViewById(R.id.title_form_add_stock);
    textTitle.getText();

    final EditText fieldName = (EditText)findViewById(R.id.form_field_name_add_anim);
    final EditText fieldSpecie = (EditText)findViewById(R.id.form_field_quantities_add_anim);
    final EditText fieldAge = (EditText)findViewById(R.id.form_field_max_add_anim);

    FloatingActionButton fab_add_anim_form = (FloatingActionButton) findViewById(R.id.fab_add_anim_form);
    if (fab_add_anim_form != null) {
        fab_add_anim_form.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String stockFromForm = fieldName.getText().toString()+"*"+fieldSpecie.getText().toString()+"*"+fieldAge.getText().toString();
                Intent intent = new Intent(FormAddStockActivity.this, ListStockActivity.class).putExtra("stockFromForm",stockFromForm);

                setResult(Activity.RESULT_OK, intent);
                finish();

            }
        });
    }
}

}
