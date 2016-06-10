package com.lunaret_seb.hb.lunaret_seb_zoo.ticket;

import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

import java.util.ArrayList;
import java.util.List;

public class ListTicketActivity extends AppCompatActivity {

    public final static int REQ_CODE_CHILD = 1;

    Ticket ticket1 = new Ticket("Tarif normal", 8);
    Ticket ticket2 = new Ticket("Tarif réduit", 6);
    Ticket ticket3 = new Ticket("Tarif enfant", 5);
    Ticket ticket4 = new Ticket("Tarif carnet 10", 65);
    Ticket ticket5 = new Ticket("Tarif groupe adultes", 55);
    Ticket ticket6 = new Ticket("Tarif groupe scolaire", 80);
    Ticket ticket7 = new Ticket("Tarif groupe étudiant", 85);

    List<Ticket> listTicket = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ticket);
        //Display of the text introduction of the activity
        final TextView textList = (TextView) findViewById(R.id.text_list_app);
        textList.setText("Liste des Tickets");

        //Hard creation of the list of referenced ticket
        listTicket.add(ticket1);
        listTicket.add(ticket2);
        listTicket.add(ticket3);
        listTicket.add(ticket4);
        listTicket.add(ticket5);
        listTicket.add(ticket6);
        listTicket.add(ticket7);

        //Initialization of the adapter
        ArrayAdapter<Ticket> adapter = new ArrayAdapter<Ticket>(this, R.layout.item_liste, R.id.text, listTicket);

        ListView listView = (ListView) findViewById(R.id.list);
        //Here, the id of the view is linking directly on the id of the ListView of
        // the associated layout
        //Then, send to the view
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListTicketActivity.this, DetailTicketActivity.class).putExtra("Ticket", listTicket.get(position));
                startActivity(intent);
            }
        });

        //BUTTON FOR ADDING A NEW ANIMAL
        FloatingActionButton fab_add_ticket = (FloatingActionButton) findViewById(R.id.fab_add_ticket);
        if (fab_add_ticket != null) {
            fab_add_ticket.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(ListTicketActivity.this, FormAddTicketActivity.class);
                    startActivityForResult(intent,REQ_CODE_CHILD);

                }
            });
        }

    }

    /*
    protected void onActivityResult (int requestCode, int resultCode, Intent data) {

        if(requestCode == REQ_CODE_CHILD) {
            super.onActivityResult(requestCode, resultCode, data);

           String tiquetFormStr = (String) data.getStringExtra("ticketFromForm");

            String[] separatedTicket = tiquetFormStr.split("\\*");
            String name = separatedTicket[0];
            double price = separatedTicket[1];

            Ticket ticketForm = new Ticket(name, price);

            Toast.makeText(this, ticketForm.getName(), Toast.LENGTH_LONG).show();;
        }

    }*/

    @Override
    public void onRestart() {

        super.onRestart();
        Intent i = new Intent(ListTicketActivity.this, ListTicketActivity.class);
        Toast.makeText(this,"La liste a été mise à jour", Toast.LENGTH_LONG).show();
        startActivity(i);
        finish();
    }

}
