package com.seweryn.schess.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import com.seweryn.schess.R;


public class SCMainMenuActivity extends AppCompatActivity {
    private  LayoutInflater boardLayoutInflater;
    @Override
    public void onCreate(Bundle savedInstanceeState) {

        super.onCreate(savedInstanceeState);
        setContentView(R.layout.menu_main);

        boardLayoutInflater = LayoutInflater.from(this);
        Button selectChallengeButton  = (Button)findViewById(R.id.Button01);
        Button quickPlayButton = (Button) findViewById(R.id.Button02);
        Button createMapButton = (Button) findViewById(R.id.Button04);

        boardLayoutInflater = LayoutInflater.from(this);
        quickPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(SCMainMenuActivity.this, GameActivity.class);
                SCMainMenuActivity.this.startActivity(mainMenu);

            }
        });
        createMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View layout = boardLayoutInflater.inflate(R.layout.choose_board_size_popup,(ViewGroup)v.findViewById(R.id.popup));
                final PopupWindow pwindo = new PopupWindow(layout, 350, 460, true);
                pwindo.showAtLocation(layout, Gravity.CENTER,0, 0);
                Button cancelButton = (Button)layout.findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                    pwindo.dismiss();
                    }
                });
                Button gotToCreateMapButton = (Button)layout.findViewById(R.id.goToCreateMap);

                gotToCreateMapButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent mainMenu = new Intent(SCMainMenuActivity.this, CreateMapActivity.class);
                        EditText boardWidthTextView = (EditText) layout.findViewById(R.id.widthTextView);
                        EditText boardHeightTextView = (EditText) layout.findViewById(R.id.heightTextView);
                        mainMenu.putExtra("BoardWidth", Integer.valueOf(boardWidthTextView.getText().toString()));
                        mainMenu.putExtra("BoardHeight", Integer.valueOf(boardHeightTextView.getText().toString()));
                        SCMainMenuActivity.this.startActivity(mainMenu);
                    }
                });



            }
        });
        selectChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent(SCMainMenuActivity.this, ChooseMapActivity.class);
                SCMainMenuActivity.this.startActivity(mainMenu);
            }
        });
    }

}