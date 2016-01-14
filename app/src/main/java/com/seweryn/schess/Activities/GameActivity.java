package com.seweryn.schess.Activities;

import android.app.Activity;

/**
 * Created by sew on 2015-10-25.
 */

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.seweryn.schess.Controllers.DatabaseContextController;
import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.DAL.DatabaseHandler;
import com.seweryn.schess.Models.DatabaseObject;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Adapters.GameBoardAdapter;
import com.seweryn.schess.R;

public class GameActivity  extends Activity {
    GameBoardAdapter gameBoardAdapter=null;
    IDatabaseContextController databaseContextController;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard_main);
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        Button undoButton = (Button) findViewById(R.id.undoButton);
        Button hintsButton =(Button) findViewById(R.id.hintButton);
        Button resetButton =(Button) findViewById(R.id.resetButton);
        Button nextButton = (Button) findViewById(R.id.nextBoardButton);
        Button previousButton = (Button) findViewById(R.id.previousButton);
      //
      //  String boardName= "dsa";
        if(getIntent().getExtras()!=null) {
            String boardName = (String) getIntent().getExtras().get("boardName");
            PuzzleType boardType = (PuzzleType) getIntent().getExtras().get("boardType");
            databaseContextController = new DatabaseContextController(this);
            DatabaseObject databaseObject = databaseContextController.read(boardType, boardName);

           // gridView.setNumColumns(databaseObject.getBoard()[0].length);
            gameBoardAdapter = new GameBoardAdapter(this,databaseObject,boardName, boardType);
            gridView.setNumColumns(databaseObject.getBoard()[0].length);
            gridView.setAdapter(gameBoardAdapter);
        }
        else{
           // gridView.setAdapter(new GameBoardAdapter(this, "1E",PuzzleType.EASY));
        }
        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoardAdapter.undoMove(gridView);
            }
        });
        hintsButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               gameBoardAdapter.showNextMove(gridView);
           }
        });
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
              //  gameBoardAdapter.setNextBoard();
            }

        });
        previousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

            }
        });


    }
}