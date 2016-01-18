package com.seweryn.schess.Activities;

/**
 * Created by sew on 2015-10-25.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
    import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.seweryn.schess.R;

public class SCMainMenuActivity extends Activity {
    private  LayoutInflater boardLayoutInflater;
    @Override
    public void onCreate(Bundle savedInstanceeState) {

        super.onCreate(savedInstanceeState);
        setContentView(R.layout.menu_main);
        Button selectChallengeButton  = (Button)findViewById(R.id.Button01);
        Button quickPlayButton = (Button) findViewById(R.id.Button02);
        Button createMapButton = (Button) findViewById(R.id.Button04);
        boardLayoutInflater = LayoutInflater.from(this);

        quickPlayButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(SCMainMenuActivity.this, GameActivity.class);
                SCMainMenuActivity.this.startActivity(mainMenu);

            }
        });
        createMapButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                final View layout = boardLayoutInflater.inflate(R.layout.choose_board_size_popup,(ViewGroup)v.findViewById(R.id.popup));
                final PopupWindow pwindo = new PopupWindow(layout, 300, 370, true);
                pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
                Button cancelButton = (Button)layout.findViewById(R.id.cancelButton);
                cancelButton.setOnClickListener(new OnClickListener(){
                    @Override
                    public void onClick(View v) {
                    pwindo.dismiss();
                    }
                });
                Button gotToCreateMapButton = (Button)layout.findViewById(R.id.goToCreateMap);

                gotToCreateMapButton.setOnClickListener(new OnClickListener() {
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
        selectChallengeButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent(SCMainMenuActivity.this, ChooseMapActivity.class);
                SCMainMenuActivity.this.startActivity(mainMenu);


            }
        });

    }

}