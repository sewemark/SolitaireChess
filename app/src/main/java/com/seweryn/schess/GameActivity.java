package com.seweryn.schess;

import android.app.Activity;

/**
 * Created by sew on 2015-10-25.
 */

import android.os.Bundle;
import android.widget.GridView;
public class GameActivity  extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard_main);
        GridView gridView = (GridView) findViewById(R.id.gridview);
        gridView.setNumColumns(4);
      //  String boardName= "dsa";
        if(getIntent().getExtras()!=null) {
            String boardName = (String) getIntent().getExtras().get("boardName");
            PuzzleType boardType = (PuzzleType) getIntent().getExtras().get("boardType");
            gridView.setAdapter(new GameBoardAdapter(this, 4, boardName, boardType));
        }
        else{
            gridView.setAdapter(new GameBoardAdapter(this,4, "1E",PuzzleType.EASY));
        }

    }
}