package com.seweryn.schess.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.Switch;

import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Controllers.SCDatabaseContextController;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.R;
import com.seweryn.schess.Static.Lodash;


public class MainMenuActivity extends AppCompatActivity {
    private  LayoutInflater boardLayoutInflater;
    protected SharedPreferences sharedpreferences;
    IDatabaseContextController databaseContextController;
    int popupWindowWidth;
    int popupWindowHeight;

    public static final String ApplicationPreferences = "SCPreferences" ;
    /**
     * overridden oncreate method that
     * set up control adapters
     * and sets UI event handlers
     * @param  savedInstanceeState bundle
     * */
    @Override
    public void onCreate(Bundle savedInstanceeState) {

        super.onCreate(savedInstanceeState);
        setContentView(R.layout.menu_main);
        popupWindowWidth = Lodash.dpToPx(220, getBaseContext());
        popupWindowHeight =Lodash.dpToPx(250, getBaseContext());
        boardLayoutInflater = LayoutInflater.from(this);
        databaseContextController = new SCDatabaseContextController().getDatabaseContextContrller(this);
        Button selectChallengeButton  = (Button)findViewById(R.id.Button01);
        Button quickPlayButton = (Button) findViewById(R.id.Button02);
        Button createMapButton = (Button) findViewById(R.id.Button04);
        Button settingsButton = (Button) findViewById(R.id.Button03);
        sharedpreferences = getSharedPreferences(ApplicationPreferences, this.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        boardLayoutInflater = LayoutInflater.from(this);
        quickPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(MainMenuActivity.this, GameActivity.class);
                mainMenu.putExtra("boardName", "1E");
                mainMenu.putExtra("boardType", "EASY");;
                MainMenuActivity.this.startActivity(mainMenu);

            }
        });
        createMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View layout = boardLayoutInflater.inflate(R.layout.choose_board_size_popup,(ViewGroup)v.findViewById(R.id.popup));
                final PopupWindow pwindo = new PopupWindow(layout, popupWindowWidth, popupWindowHeight, true);
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

                        Intent mainMenu = new Intent(MainMenuActivity.this, CreateMapActivity.class);
                        EditText boardWidthTextView = (EditText) layout.findViewById(R.id.widthTextView);
                        EditText boardHeightTextView = (EditText) layout.findViewById(R.id.heightTextView);
                        int width = Lodash.tryParse(boardWidthTextView.getText().toString());
                        int height = Lodash.tryParse(boardHeightTextView.getText().toString());
                        if(checkIfBoardDimension(width, height)) {
                            mainMenu.putExtra("BoardWidth", width);
                            mainMenu.putExtra("BoardHeight", height);
                            MainMenuActivity.this.startActivity(mainMenu);
                            pwindo.dismiss();
                        }
                        else{
                            pwindo.dismiss();
                        }
                    }
                });



            }
        });
        selectChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    Intent mainMenu = new Intent(MainMenuActivity.this, ChooseMapActivity.class);
                    MainMenuActivity.this.startActivity(mainMenu);
                }
                catch(OutOfMemoryError ex ){
                    Intent mainMenu = new Intent(MainMenuActivity.this, MainMenuActivity.class);
                    MainMenuActivity.this.startActivity(mainMenu);
                }

            }
        });
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View layout = boardLayoutInflater.inflate(R.layout.settings_popup, (ViewGroup) v.findViewById(R.id.popup));
                final PopupWindow pwindo = new PopupWindow(layout, popupWindowWidth, popupWindowHeight, true);
                pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
                final Switch hintsSwitch = (Switch) layout.findViewById(R.id.hintsSwitch);
                final ProgressDialog progressBar = new ProgressDialog(v.getContext());
                final boolean[] turnOnHints = {sharedpreferences.getBoolean("HintsSwitched", true)};
                hintsSwitch.setChecked(turnOnHints[0]);
                hintsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        turnOnHints[0] = isChecked;
                    }
                });
                Button cancelButton = (Button)layout.findViewById(R.id.cancelButton);
                Button settingsSaveButton = (Button)layout.findViewById(R.id.saveSettingsButton);
                Button resetDatabaseButton = (Button)layout.findViewById(R.id.resetDatabaseButton);
                resetDatabaseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {

                            progressBar.setCancelable(false);
                            progressBar.setMessage("Reseting database ...");
                            progressBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressBar.setProgress(0);
                            progressBar.setMax(100);
                            progressBar.show();
                            new Thread(new Runnable() {
                                public void run() {
                                    databaseContextController.resetDatabase();
                                    progressBar.dismiss();
                                }

                            }).start();
                        }
                        catch (OutOfMemoryError ex){
                            showOutOfMemoryDialog();
                            progressBar.dismiss();
                        }
                    }
                });

                cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pwindo.dismiss();
                    }
                });

                settingsSaveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        editor.putBoolean("HintsSwitched", turnOnHints[0]);
                        editor.commit();
                        pwindo.dismiss();
                    }
                });
            }
        });
    }

    private void showOutOfMemoryDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainMenuActivity.this).create();
        alertDialog.setTitle("Alert");
        alertDialog.setMessage("Unfortunately your puzzle is too complicated");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();
                    }
                });
        alertDialog.show();
    }

    public boolean checkIfBoardDimension(int width, int height){
        if(width <1 || width >20)
            return false;
        if(height<1 || height >20)
            return  false;
        return  true;
    }


}