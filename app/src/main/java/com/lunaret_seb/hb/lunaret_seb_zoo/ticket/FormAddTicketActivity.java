package com.lunaret_seb.hb.lunaret_seb_zoo.ticket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

public class FormAddTicketActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_add_ticket);

        final EditText fieldName = (EditText)findViewById(R.id.form_field_name_add_ticket);
        final EditText fieldPrice = (EditText)findViewById(R.id.form_field_price_add_ticket);

        FloatingActionButton fab_add_ticket_form = (FloatingActionButton) findViewById(R.id.fab_add_ticket);
        if (fab_add_ticket_form != null) {
            fab_add_ticket_form.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    final String ticketFromForm = fieldName.getText().toString()+"*"+fieldPrice.getText().toString();
                    Intent intent = new Intent(FormAddTicketActivity.this, ListTicketActivity.class).putExtra("ticketFromForm",ticketFromForm);

                    setResult(Activity.RESULT_OK, intent);
                    finish();

                }
            });
        }
    }
}
