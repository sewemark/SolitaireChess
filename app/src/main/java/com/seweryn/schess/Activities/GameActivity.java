package com.seweryn.schess.Activities;

import android.app.Activity;

/**
 * Created by sew on 2015-10-25.
 */

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
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
    private TextView timeElapsedTextView;
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;

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
        timeElapsedTextView = (TextView)findViewById(R.id.timeElapsedTextView);
        final TextView textView = (TextView) findViewById(R.id.puzzleHardnessLevel);
        setTimer();

        if(getIntent().getExtras()!=null) {

            boardName = (String) getIntent().getExtras().get("boardName");
            PuzzleType boardType =  PuzzleType.valueOf(getIntent().getExtras().getString("boardType"));
            injectControllers();
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
                customHandler.removeCallbacks(updateTimerThread);
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
                customHandler.removeCallbacks(updateTimerThread);

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
                setTimer();
                textView.setText(gameBoardAdapter.getCurrentPuzzleType().toString());
                textView.invalidate();
            }

        });
        previousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                gameBoardAdapter.setPreviousBoard();
                setTimer();
                textView.setText(gameBoardAdapter.getCurrentPuzzleType().toString());
                textView.invalidate();
            }
        });
    }
    private void setTimer(){
        startTime = SystemClock.uptimeMillis();
        customHandler.removeCallbacks(updateTimerThread);
        customHandler.postDelayed(updateTimerThread, 0);
    }

    @Override
    public void onStop(){
        super.onStop();
        customHandler.removeCallbacks(updateTimerThread);
        this.finish();
    }
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            timeElapsedTextView.setText("" + mins + ":"
                            + String.format("%02d", secs) + ":"
                            + String.format("%03d", milliseconds));
            customHandler.postDelayed(this, 0);
        }
    };
    private void injectControllers(){
        databaseContextController = new SCDatabaseContextController().getDatabaseContextContrller(this);
        moveRulesController = new SCMoveRulesController().getMoveRulesController();
        boardLogicController = new SCBoardLogicController().getBoardLogicController();
    }


}