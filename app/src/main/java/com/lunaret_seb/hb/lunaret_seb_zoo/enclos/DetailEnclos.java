package com.lunaret_seb.hb.lunaret_seb_zoo.enclos;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;
import com.lunaret_seb.hb.lunaret_seb_zoo.animaux.Animaux;

public class DetailEnclos extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_detail_enclos);

    Bundle extra = getIntent().getExtras();
    Enclos enclos = (Enclos) extra.get("enclos");

    final TextView textList = (TextView)findViewById(R.id.text_detail);
    textList.setText(enclos.getName());

    //TODO SPECIE AND AGE + ONCLICK ON THE BUTTON

    Button bouton;
    bouton= (Button) findViewById(R.id.btm);





}
    public void onClickBtnSuppr(View v)
    {
        Toast.makeText(this, "Clicked on Button Suppr enclos", Toast.LENGTH_LONG).show();
    }
}
