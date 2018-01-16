package com.example.brandonzhu.decisionmakingapplication.userinput;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.example.brandonzhu.decisionmakingapplication.R;

public class DecisionMenu extends AppCompatActivity {

    public ArrayList<String> storeDec = new ArrayList<String>();
    public String option = "";
    public String showalldec = "";//string that shows all stored decisions
    public TextView choices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_decision_menu);
    }


    @Override
    protected void onStart(){
        super.onStart();

    }


    //return something from the dialog
    public void adddesc(){
        final Dialog addbox = new Dialog(this);
        addbox.setContentView(R.layout.dialogbox);


        Button submit = (Button) addbox.findViewById(R.id.yes);
        Button cancel = (Button) addbox.findViewById(R.id.no);


       submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText takeW = (EditText) addbox.findViewById(R.id.whattodo);
                if (isempty(takeW)) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please enter a decision", 2);
                    toast.show();
                } else {
                    option = takeW.getText().toString();

                    TextView choices = (TextView) findViewById(R.id.ado);

                    if (option.equals("")){
                        choices.setText("Add decisions");
                    }
                    else {
                        storeDec.add(option); //add a decision to the array list
                        showalldec = ""; // reset array to string saved list
                        for (String s:storeDec){
                            showalldec += s + "\t \n";

                        }
                        showalldec = showalldec.replace(",", "").replace("[", "").replace("]", "").trim();
                        choices.setText(showalldec);

                        addbox.dismiss();
                    }

            }}});

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addbox.dismiss();
            }});
       addbox.show();

    }


    //Button for adding decisions
    public void moreDecisions(View v){
       // choices.setText("");
        adddesc();  //Dialog box will appear

    }



    //A dialog box for the delete button
    public void dele(View v){


        if (storeDec.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "No decisions to delete", 2); // check if anything is in arraylist
            toast.show();
        }
        else {

            CharSequence[] ar = new String[storeDec.size()]; // store Arraylist into CharSeq to make multi list
            int i = 0;
            for (String j : storeDec) {
                ar[i] = j;
                i++;
            }


            final AlertDialog.Builder deleteb = new AlertDialog.Builder(this);
            deleteb.setTitle("Delete a decision from the list...");
            deleteb.setItems(ar, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    storeDec.remove(which);
                    showalldec = "";
                    for (String s : storeDec) {
                        showalldec += s + "\t \n";             // dialog box appears

                    }
                    showalldec = showalldec.replace(",", "").replace("[", "").replace("]", "").trim();
                    TextView replace = (TextView) findViewById(R.id.ado);
                    replace.setText(showalldec);     // reprint the list of decisions
                    dialog.cancel();
                }
            });
            deleteb.show();


        }
    }

    public void RusR(View v){
        if (storeDec.isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "No decisions to roulette", 2);
            toast.show();
        }
        else {
            final AlertDialog.Builder pick = new AlertDialog.Builder(this);
            pick.setTitle("Your decision is...");
            CharSequence finaldec = storeDec.get(random());
            pick.setMessage(finaldec);
            pick.show();
        }

    }

    //generate a random value and picks from the arraylist
    public int random(){
        Random r = new Random();
        int any = r.nextInt(storeDec.size());
        return any;
    }

    private boolean isempty(EditText eT){
        return eT.getText().toString().trim().length() == 0;
    }


}
