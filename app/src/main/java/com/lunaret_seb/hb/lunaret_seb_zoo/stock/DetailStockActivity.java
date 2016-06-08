package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

public class DetailStockActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_stock);

        Bundle extra = getIntent().getExtras();
        Stock stock = (Stock) extra.get("stock");

        final TextView name = (TextView)findViewById(R.id.name);
        name.setText("Nom : "+stock.getName());

        final TextView quantities = (TextView)findViewById(R.id.quantities);
        quantities.setText("Quantité : "+stock.getQuantities());

        final TextView textMax = (TextView)findViewById(R.id.max);
        textMax.setText("Limite de stockage : "+stock.getMaximum());

        Button bouton;
        bouton= (Button) findViewById(R.id.btm);
    }

    public void onClickBtnSuppr(View v)
    {
        Toast.makeText(this, "Suppression de la fourniture", Toast.LENGTH_LONG).show();
    }
}
