package com.lunaret_seb.hb.lunaret_seb_zoo.ticket;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

import java.util.ArrayList;
import java.util.List;

public class ListTicketActivity extends AppCompatActivity {

    Ticket ticket1 = new Ticket("Tarif normal");
    Ticket ticket2 = new Ticket("Tarif réduit");
    Ticket ticket3 = new Ticket("Tarif enfant");
    Ticket ticket4 = new Ticket("Tarif carnet 10");
    Ticket ticket5 = new Ticket("Tarif groupe adultes");
    Ticket ticket6 = new Ticket("Tarif groupe scolaire");
    Ticket ticket7 = new Ticket("Tarif groupe étudiant");

    List<Ticket> listTicket = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_ticket);
        //Display of the text introduction of the activity
        final TextView textList = (TextView)findViewById(R.id.text_list_app);
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
                Intent intent = new Intent(ListTicketActivity.this, DetailTicketActivity.class).putExtra("stock", listTicket.get(position)  );
                startActivity(intent);
            }
        });
    }
}
