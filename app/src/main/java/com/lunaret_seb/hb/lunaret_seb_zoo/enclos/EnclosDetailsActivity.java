package com.lunaret_seb.hb.lunaret_seb_zoo.enclos;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lunaret_seb.hb.lunaret_seb_zoo.R;

public class EnclosDetailsActivity extends AppCompatActivity {
    Enclos enclos;
    int position=0;
    String mode="new";
    TextView areaTxt=null;
    TextView nameTxt=null;
    TextView DescriptionTxt=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enclos_details);
        areaTxt =  (TextView)findViewById(R.id.AreaText);
        nameTxt =  (TextView)findViewById(R.id.NameText);
        DescriptionTxt =  (TextView)findViewById(R.id.DescriptionText);

        Intent myIntent= getIntent();
        Bundle b = myIntent.getExtras();

        //for display
        if(b!=null)
        {
            enclos =(Enclos) b.get("myEnclos");

            areaTxt.setText(enclos.getArea());
            DescriptionTxt.setText(enclos.getDescription());
            nameTxt.setText(enclos.getName());
            mode = (String)b.get("mode");
            position = (int)b.get("position");
        }

        Button button= (Button)findViewById(R.id.AddButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myDetailsIntent = new Intent(EnclosDetailsActivity.this, EnclosActivity.class);
                enclos = new Enclos(nameTxt.getText().toString(),areaTxt.getText().toString(),DescriptionTxt.getText().toString());
                myDetailsIntent.putExtra("myEnclos", enclos);
                myDetailsIntent.putExtra("position", position);

                if(mode.equals("update")){
                    myDetailsIntent.putExtra("mode", "update");
                }
                else{
                    myDetailsIntent.putExtra("mode", "new");
                }
                startActivity(myDetailsIntent);
            }
        });

        Button removeBtn= (Button)findViewById(R.id.deleteButton);
        removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog diaBox = AskOption(position);
                diaBox.show();
            }
        });

        //plumbing !!!1
        if(mode.equals("new"))
            removeBtn.setVisibility(View.INVISIBLE);
    }


    private AlertDialog AskOption(final int pos)
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Do you want to Delete")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
//deleting code
                        Intent myDetailsIntent = new Intent(EnclosDetailsActivity.this, EnclosActivity.class);
                        myDetailsIntent.putExtra("position", pos);
                        myDetailsIntent.putExtra("mode", "delete");
                        startActivity(myDetailsIntent);

                        dialog.dismiss();
                    }
                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }
}