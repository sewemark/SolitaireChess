package com.seweryn.schess.Activities;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.seweryn.schess.Controllers.DatabaseContextController;
import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.R;

import java.util.ArrayList;
import java.util.Arrays;

public class ChooseMapActivity extends Activity {

    IDatabaseContextController  controller;
    public ChooseMapActivity(){
    }
    @Override
    public void onCreate(Bundle savedInstanceeState) {
        super.onCreate(savedInstanceeState);
        controller = new DatabaseContextController(this);

        setContentView(R.layout.choose_map);
        GridView easyGridView = (GridView)findViewById(R.id.chooseMapGridView);
        String[] easyPuzzles = controller.getPuzzleListByType(PuzzleType.EASY);
        //this.helperFunction(easyPuzzles,"E");
        String[] mediumPuzzles = controller.getPuzzleListByType(PuzzleType.MEDIUM);
        //this.helperFunction(mediumPuzzles, "M");
        ArrayList<String> lista = new ArrayList<String>();
        lista.addAll(0,Arrays.asList(easyPuzzles));
        lista.addAll(Arrays.asList(mediumPuzzles));
       // String[] easyPuzzles = new String[]{"bla","bla","bla"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,lista );
        easyGridView.setAdapter(adapter);
        easyGridView.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {

                Intent chooseBoard = new Intent(ChooseMapActivity.this, GameActivity.class);
                chooseBoard.putExtra("boardName", ((TextView) v).getText());
                chooseBoard.putExtra("boardType", PuzzleType.EASY);

                ChooseMapActivity.this.startActivity(chooseBoard);
                startActivity(chooseBoard);
            }
        });


    }
}
