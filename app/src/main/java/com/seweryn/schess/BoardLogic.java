package com.seweryn.schess;

/**
 * Created by sew on 2016-01-02.
 */
public class BoardLogic {
    private int[][]board;
    private int boardWidth;
    private int boardHeight;

    public BoardLogic(int[][] _board){
        this.board = _board;
        this.boardWidth = this.board[0].length;
        this.boardHeight = this.board.length;
    }

    public  boolean checkIfWin(){
        int numOfPieces=0;
        for(int i=0;i < board[0].length;i++){
            for(int j=0; j<board.length;j++){
                if(board[i][j] !=0){
                    numOfPieces++;
                }
            }
        }
        return  numOfPieces==1 ? true: false;
    }
    public boolean removePiece(int position, int destinationPosition, int newPieceValue){
        Vector destinationVector = Vector.convertToVecotr(boardWidth,boardHeight,destinationPosition);
        Vector basePostionVector = Vector.convertToVecotr(boardWidth, boardHeight,position);
        int destinationPieceValue = board[destinationVector.getX()][destinationVector.getY()];
        if(board[destinationVector.getX()][destinationVector.getY()]!=0){
            board[destinationVector.getX()][destinationVector.getY()]=newPieceValue;
            board[basePostionVector.getX()][basePostionVector.getY()] =0;
            return true;
        }
        return  false;
    }
    public  int getPieceAtPosition(int position){
        Vector positionVector = Vector.convertToVecotr(boardWidth,boardHeight,position);
        return this.board[positionVector.getX()][positionVector.getY()];
    }
    public void setPieceAtPosition(int position, int pieceValue){
        Vector positionVector = Vector.convertToVecotr(boardWidth,boardHeight,position);
         this.board[positionVector.getX()][positionVector.getY()]= pieceValue;
    }
    public int[][]getBoard(){
        return  this.board;
    }
}
