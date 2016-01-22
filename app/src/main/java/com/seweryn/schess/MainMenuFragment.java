package com.seweryn.schess;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.seweryn.schess.Activities.ChooseMapActivity;
import com.seweryn.schess.Activities.CreateMapActivity;
import com.seweryn.schess.Activities.GameActivity;
import com.seweryn.schess.Activities.SCMainMenuActivity;
import com.seweryn.schess.R;

/**
 * Created by sew on 2016-01-20.
 */
public class MainMenuFragment extends Fragment {
    @Override
    public View onCreateView(final LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.main_menu, container, false);
        Button selectChallengeButton  = (Button)rootView.findViewById(R.id.Button01);
        Button quickPlayButton = (Button) rootView.findViewById(R.id.Button02);
        Button createMapButton = (Button) rootView.findViewById(R.id.Button04);
        quickPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenu = new Intent(getActivity(), GameActivity.class);
                startActivity(mainMenu);

            }
        });
        createMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View layout = inflater.inflate(R.layout.choose_board_size_popup,(ViewGroup)v.findViewById(R.id.popup));
                final PopupWindow pwindo = new PopupWindow(layout, 300, 370, true);
                pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
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

                        Intent mainMenu = new Intent(getActivity(), CreateMapActivity.class);
                        EditText boardWidthTextView = (EditText) layout.findViewById(R.id.widthTextView);
                        EditText boardHeightTextView = (EditText) layout.findViewById(R.id.heightTextView);
                        mainMenu.putExtra("BoardWidth", Integer.valueOf(boardWidthTextView.getText().toString()));
                        mainMenu.putExtra("BoardHeight", Integer.valueOf(boardHeightTextView.getText().toString()));
                        startActivity(mainMenu);
                    }
                });



            }
        });
        selectChallengeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainMenu = new Intent(getActivity(), ChooseMapActivity.class);
                startActivity(mainMenu);


            }
        });
        return rootView;
    }

}
