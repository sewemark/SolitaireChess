package com.seweryn.schess.Controllers;

import com.seweryn.schess.Models.Vector;

/**
 * Created by sew on 2016-01-02.
 */

public class BoardLogicController implements  IBoardLogicController {
    private int[][]board;
    private int boardWidth;
    private int boardHeight;

    public BoardLogicController(){

    }

    public  boolean checkIfWinPosition(){
        int numOfPieces=0;
        for(int i=0;i < board[0].length;i++){
            for(int j=0; j<board.length;j++){
                if(board[j][i] !=0){
                    numOfPieces++;
                }
            }
        }
        return  numOfPieces==1 ? true: false;
    }
    public void removePiece(int position, int destinationPosition, int newPieceValue){
        Vector destinationVector = Vector.convertToVector(boardWidth, boardHeight, destinationPosition);
        Vector basePostionVector = Vector.convertToVector(boardWidth, boardHeight, position);
        board[destinationVector.getY()][destinationVector.getX()]=newPieceValue;
        board[basePostionVector.getY()][basePostionVector.getX()] =0;
    }
    public  int getPieceAtPosition(int position){
        Vector positionVector = Vector.convertToVector(boardWidth, boardHeight, position);
        return this.board[positionVector.getY()][positionVector.getX()];
    }
    public void setPieceAtPosition(int position, int pieceValue){
        Vector positionVector = Vector.convertToVector(boardWidth, boardHeight, position);
         this.board[positionVector.getY()][positionVector.getX()]= pieceValue;
    }

    @Override
    public void initializeBoard() {
        for(int i=0;i < board[0].length;i++){
            for(int j=0; j<board.length;j++){
                board[j][i] =0;
            }
        }
    }

    public int[][]getBoard(){
        return  this.board;
    }
    public  void setBoard(int[][] _board){
        this.board = _board;
        this.boardWidth = this.board[0].length;
        this.boardHeight = this.board.length;
    }
}
