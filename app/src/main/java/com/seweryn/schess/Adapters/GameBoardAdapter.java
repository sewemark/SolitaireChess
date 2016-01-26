package com.seweryn.schess.Adapters;


import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.PopupWindow;

import com.seweryn.schess.Activities.ChooseMapActivity;
import com.seweryn.schess.Activities.GameActivity;
import com.seweryn.schess.Controllers.IBoardLogicController;
import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Controllers.IMoveRulesController;
import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;
import com.seweryn.schess.Models.Move;
import com.seweryn.schess.Models.Solution;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.R;
import com.seweryn.schess.Static.Lodash;

import java.lang.*;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.List;


public final class GameBoardAdapter extends BoardAdapter {


    IMoveRulesController moveLogicController;
    IBoardLogicController boardLogicController;
    IDatabaseContextController databaseContextController;
    protected SharedPreferences sharedpreferences;
    public static final String ApplicationPreferences = "SCPreferences" ;
    DatabaseObject databaseObject;
    public PuzzleType puzzleType;
    String boardName;
     GridView gridView;
     List<Solution> solutions;
     Deque<Move> moves;
     public GameBoardAdapter(Context context,GridView _gridView ,IMoveRulesController _moveRulesController, IBoardLogicController _boardLogicController,IDatabaseContextController _databaseController, String _boardName,   PuzzleType _puzleType) {
         super(context,_boardLogicController, _databaseController.read(_puzleType,_boardName).getBoard()[0].length,  _databaseController.read(_puzleType,_boardName).getBoard().length);
         this.gridView = _gridView;
         this.databaseContextController = _databaseController;
         this.moveLogicController = _moveRulesController;
         this.boardLogicController = _boardLogicController;
         this.boardName = _boardName;
         sharedpreferences = context.getSharedPreferences(ApplicationPreferences, context.MODE_PRIVATE);
         this.puzzleType = _puzleType;
         databaseObject = databaseContextController.read(puzzleType,boardName);
         initializeBoard(databaseObject);
     }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       View v=super.getView(i,view,viewGroup);
       v.setOnDragListener(new GameBoardDragListener());
       v.findViewById(R.id.grid_item_piece).setOnTouchListener(new MyTouchListener());
       return  v;
    }
    public void initializeBoard(DatabaseObject databaseObject){
        //DatabaseObject databaseObject = databaseContextController.read(puzzleType,boardName);
        this.boardLogicController.setBoard(databaseObject.getBoard());
        this.boardName = databaseObject.getFileName();
        this.puzzleType = databaseObject.getPuzzleType();
        gridView.setNumColumns(boardLogicController.getBoard()[0].length);
        this.board = Lodash.deepCopyIntMatrix(boardLogicController.getBoard());
        this.solutions = databaseObject.getSolutions();
        if(this.solutions.size()>0)
            Collections.reverse(this.solutions.get(0).boards);
        moves= new ArrayDeque<Move>();

    }
    public void resetBoard(){
       // boardLogic.setBoard(this.board);
        initializeBoard(databaseContextController.read(puzzleType, boardName));
        this.notifyDataSetChanged();


    }
    public void setNextBoard() {
        initializeBoard(databaseContextController.readNextPuzzle(puzzleType, boardName));
        this.notifyDataSetChanged();
    }
    public void setPreviousBoard(){
        initializeBoard(databaseContextController.readPreviousPuzzle(puzzleType,boardName));
        this.notifyDataSetChanged();
    }
    public void setHintsBackground(int position, boolean showHints, PieceType pieceType){

         Integer[] possiblePositions = moveLogicController.PossibleMoves(this.width,this.height,Vector.convertToVector(this.width, this.height, position),pieceType);
         for(int i =0;i<possiblePositions.length;i++){
             View view = getChildFromFrameLayout(possiblePositions[i], 2);
             int resource=0;
             if(showHints) {
                 resource= Lodash.getResource(pieceType);
             }
             setView(view,resource,-1);
         }
     }
    private View getChildFromFrameLayout(int frameLayoutPosition, int childResourceId){
        FrameLayout frameLayout = (FrameLayout)gridView.getChildAt(frameLayoutPosition);
        if(childResourceId==1)
        return  frameLayout.findViewById(R.id.grid_item_piece);
        else
            return frameLayout.findViewById(R.id.hint_image);
    }
    private void addChildToFrameLayout(int frameLayoutPosition, View view){
        FrameLayout frameLayout = (FrameLayout)gridView.getChildAt(frameLayoutPosition);
        frameLayout.addView(view);
    }
    private void checkIfWin() throws InterruptedException {
        if(boardLogicController.checkIfWinPosition()){
            databaseObject.setSolved();
            databaseContextController.update(databaseObject);
            showWinDialog(gridView);

            Intent mainMenu = new Intent(context, ChooseMapActivity.class);

            context.startActivity(mainMenu);
        }
    }
    private void setView(View view, int resource, int tag){
       if(view != null) {
           view.setBackgroundResource(resource);
           view.setTag(tag);
       }
    }
    private View popChildFromFrameLayout(int frameLayoutPosition,int childId){
        FrameLayout frameLayout = (FrameLayout)gridView.getChildAt(frameLayoutPosition);
        View viewToDelete = frameLayout.getChildAt(childId);
        frameLayout.removeViewAt(childId);
        return  viewToDelete;
    }

    public void undoMove(){
        if(this.moves.size()>0){
            Move lastMove = moves.pop();
            setView(lastMove.view, Lodash.getResource(lastMove.beatedPieceType), lastMove.beatedPieceType);
            addChildToFrameLayout(lastMove.sourPositon, lastMove.view);
            View view= getChildFromFrameLayout(lastMove.destinatioPosition,1);
            setView(view, Lodash.getResource(lastMove.pieceType), lastMove.pieceType);

            boardLogicController.setPieceAtPosition(lastMove.sourPositon, lastMove.beatedPieceType);
            boardLogicController.setPieceAtPosition(lastMove.destinatioPosition, lastMove.pieceType);
        }
    }
    public void performNextMove() throws InterruptedException {
        for(int i=0;i<this.solutions.size();i++){
            for(int j=0;j<this.solutions.get(i).boards.size();j++){
                if(Lodash.areBoardsEqual(this.solutions.get(i).boards.get(j), boardLogicController.getBoard())) {
                    if (j+ 1 < this.solutions.get(i).boards.size()) {
                         Move m = Move.extractMove(boardLogicController.getBoard(), this.solutions.get(i).boards.get(j + 1));
                         View viewToDelete = popChildFromFrameLayout(m.sourPositon, 2);
                        if(m.destinatioPosition>=0) {

                            View view = getChildFromFrameLayout(m.destinatioPosition,1);
                            setView(view,Lodash.getResource(m.pieceType),m.pieceType);
                            boardLogicController.setPieceAtPosition(m.destinatioPosition, m.pieceType);

                        }
                        boardLogicController.setPieceAtPosition(m.sourPositon, 0);
                        moves.push(new Move(viewToDelete,m.beatedPieceType, m.pieceType,m.sourPositon, m.destinatioPosition));
                        databaseObject.setHintsUsed();
                        checkIfWin();
                        return;
                    }
                }
            }
        }
        this.undoMove();
    }
    public void showWinDialog(View v) throws InterruptedException {
        View layout = boardLayoutInflater.inflate(R.layout.win_popup,(ViewGroup)v.findViewById(R.id.popup));
        final PopupWindow pwindo = new PopupWindow(layout, 300, 370, true);
        pwindo.showAtLocation(layout, Gravity.CENTER, 0, 0);
        Button cancelButton = (Button)layout.findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwindo.dismiss();
            }
        });
    }
    public PuzzleType getCurrentPuzzleType(){
        return  this.puzzleType;
    }


     class GameBoardDragListener implements View.OnDragListener {
         Drawable enterShape = context.getResources().getDrawable(R.drawable.shape_droptarget);
         Drawable normalShape = context.getResources().getDrawable(R.drawable.shape);

         @Override
         public boolean onDrag(View v, DragEvent event) {
             int action = event.getAction();
             switch (event.getAction()) {
                 case DragEvent.ACTION_DRAG_STARTED:
                     // do nothing
                     break;
                 case DragEvent.ACTION_DRAG_ENTERED:
                     v.setBackgroundDrawable(enterShape);
                     break;
                 case DragEvent.ACTION_DRAG_EXITED:
                     v.setBackgroundDrawable(normalShape);
                     break;
                 case DragEvent.ACTION_DROP:
                     // Dropped, reassign View to ViewGroup
                     View view = (View) event.getLocalState();
                     ViewGroup owner = (ViewGroup) view.getParent();
                   //  GridView gridView = (GridView)owner.getParent();
                     int position = gridView.getPositionForView(view);
                      owner.removeView(view);
                     int viewTag = (int) view.getTag();
                     if(viewTag>0) {
                         PieceType pieceType = Lodash.getPiecType(viewTag);
                         Integer[] possiblePositions = moveLogicController.PossibleMoves(width, height, Vector.convertToVector(width, height, position), pieceType);
                         if (sharedpreferences.getBoolean("HintsSwitched", true))
                             setHintsBackground(position, false, Lodash.getPiecType((int) view.getTag()));
                         FrameLayout container = (FrameLayout) v;
                         int destinationPosition = gridView.getPositionForView(container);
                         if (!Lodash.HasElement(possiblePositions, destinationPosition) || (boardLogicController.getPieceAtPosition(destinationPosition) <= 0)) {
                             owner.addView(view);
                         } else {
                             int tempValue = boardLogicController.getPieceAtPosition(destinationPosition);
                             boardLogicController.removePiece(position, destinationPosition, (int) view.getTag());
                             if (container.getChildCount() > 2) {
                                 View viewToDelete = container.getChildAt(2);
                                 container.removeViewAt(2);
                                 moves.push(new Move(viewToDelete, tempValue, boardLogicController.getPieceAtPosition(destinationPosition), position, destinationPosition));
                             }
                             container.addView(view);

                             try {
                                 checkIfWin();
                             } catch (InterruptedException e) {
                                 e.printStackTrace();
                             }
                         }
                         view.setVisibility(View.VISIBLE);
                     }
                     break;
                 case DragEvent.ACTION_DRAG_ENDED:
                     v.setBackgroundDrawable(normalShape);
                 default:
                     break;
             }
             return true;
         }


     }
    private final class MyTouchListener implements View.OnTouchListener {

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                if(sharedpreferences.getBoolean("HintsSwitched",true))
                    setHintsBackground(gridView.getPositionForView(view), true, Lodash.getPiecType((int) view.getTag()));
                return true;
            } else {
                return false;
            }
        }
    }
 }
