package com.example.brandonzhu.decisionmakingapplication.userview;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.brandonzhu.decisionmakingapplication.R;

/**
 * Created by ezzhubr on 1/16/2018.
 */

public class MainMenu extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        Button playButton = (Button) findViewById(R.id.begin_app);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gameIntent = new Intent(MainMenu.this, DecisionMenu.class);
                startActivity(gameIntent);
            }
        });
    }

}
