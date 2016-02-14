package com.seweryn.schess.Activities;

import android.app.Activity;

/**
 * Created by sew on 2015-10-25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.seweryn.schess.Controllers.BoardLogicController;
import com.seweryn.schess.Controllers.DatabaseContextController;
import com.seweryn.schess.Controllers.IBoardLogicController;
import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Controllers.IMoveRulesController;
import com.seweryn.schess.Controllers.SCBoardLogicController;
import com.seweryn.schess.Controllers.SCDatabaseContextController;
import com.seweryn.schess.Controllers.SCMoveRulesController;
import com.seweryn.schess.Models.DatabaseObject;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Adapters.GameBoardAdapter;
import com.seweryn.schess.R;

public class GameActivity  extends Activity {
    GameBoardAdapter gameBoardAdapter=null;
    IDatabaseContextController databaseContextController;
    IMoveRulesController moveRulesController;
    IBoardLogicController boardLogicController;
    String boardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gameboard_main);
        final GridView gridView = (GridView) findViewById(R.id.gridview);
        Button undoButton = (Button) findViewById(R.id.undoButton);
        Button hintsButton =(Button) findViewById(R.id.hintButton);
        Button resetButton =(Button) findViewById(R.id.resetButton);
        Button nextButton = (Button) findViewById(R.id.nextBoardButton);
        Button menuButton = (Button) findViewById(R.id.menuButton);
        Button selectChallengeButton = (Button) findViewById(R.id.selectChallengButton);
        Button previousButton = (Button) findViewById(R.id.previousButton);
        final TextView textView = (TextView) findViewById(R.id.puzzleHardnessLevel);

        if(getIntent().getExtras()!=null) {
            boardName = (String) getIntent().getExtras().get("boardName");
            PuzzleType boardType =  PuzzleType.valueOf(getIntent().getExtras().getString("boardType"));
            databaseContextController = new SCDatabaseContextController().getDatabaseContextContrller(this);
            moveRulesController = new SCMoveRulesController().getMoveRulesController();
            boardLogicController = new SCBoardLogicController().getBoardLogicController();
            DatabaseObject databaseObject = databaseContextController.read(boardType, boardName);
            gameBoardAdapter = new GameBoardAdapter(this,gridView,moveRulesController,boardLogicController,databaseContextController,boardName, boardType);

            gridView.setNumColumns(databaseObject.getBoard()[0].length);
            gridView.setAdapter(gameBoardAdapter);
            textView.setText(gameBoardAdapter.puzzleType.toString());

        }

        undoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameBoardAdapter.undoMove();
            }
        });
        hintsButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View v){
               try {
                   gameBoardAdapter.performNextMove();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        menuButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

               Intent mainMenu = new Intent(GameActivity.this, MainMenuActivity.class);

                GameActivity.this.startActivity(mainMenu);
                GameActivity.this.finish();

            }
        });
        selectChallengeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent mainMenu = new Intent(GameActivity.this, ChooseMapActivity.class);
                GameActivity.this.startActivity(mainMenu);
                GameActivity.this.finish();

            }
        });
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gameBoardAdapter.resetBoard();
            }
        });
        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                gameBoardAdapter.setNextBoard();
                textView.setText(gameBoardAdapter.getCurrentPuzzleType().toString());
                textView.invalidate();
            }

        });
        previousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                gameBoardAdapter.setPreviousBoard();
                textView.setText(gameBoardAdapter.getCurrentPuzzleType().toString());
                textView.invalidate();
            }
        });
    }
    @Override
    public void onStop(){
        super.onStop();
        this.finish();
    }
}