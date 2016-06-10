package com.lunaret_seb.hb.lunaret_seb_zoo.ticket;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

public class DetailTicketActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ticket);

        Bundle extra = getIntent().getExtras();
        Ticket ticket = (Ticket) extra.get("Ticket");

        final TextView name = (TextView)findViewById(R.id.name);
        name.setText(ticket.getName());

        final TextView price = (TextView)findViewById(R.id.price);
        price.setText(""+ticket.getPrice());

        Button bouton;
        bouton= (Button) findViewById(R.id.btm);
    }

    public void onClickBtnSuppr(View v) {
        Toast.makeText(this, "Suppression du ticket", Toast.LENGTH_LONG).show();
    }

}
