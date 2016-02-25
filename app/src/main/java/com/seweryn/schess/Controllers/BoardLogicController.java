package com.seweryn.schess.Controllers;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Logic.CollisionLogic;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.Static.Lodash;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sew on 2016-01-02.
 */

public class BoardLogicController implements  IBoardLogicController {
    private int[][]board;
    private int boardWidth;
    private int boardHeight;

    public BoardLogicController(){

    }
    /**
     * checks whether board contains only a single piece what mean
     * goal position
     * @return  boolean that indicates if the state is a goal state
     */
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
    /**
     * checks remove Piece ad
     * goal position
     * @param  position the position of the piece the remove
     * @param  destinationPosition the position of the piece to set a piece
     * @param  newPieceValue piece type value to set
     * @return  boolean that indicates if the state is a goal state
     */
    public void removePiece(int position, int destinationPosition, int newPieceValue){
        Vector destinationVector = Vector.convertToVector(boardWidth, boardHeight, destinationPosition);
        Vector basePostionVector = Vector.convertToVector(boardWidth, boardHeight, position);
        board[destinationVector.getY()][destinationVector.getX()]=newPieceValue;
        board[basePostionVector.getY()][basePostionVector.getX()] =0;
    }
    /**
     * returns piece value and at particular position
     * @param  position the position of the piece
     * @return  returns the piece value at position
     */
    public  int getPieceAtPosition(int position){
        Vector positionVector = Vector.convertToVector(boardWidth, boardHeight, position);
        return this.board[positionVector.getY()][positionVector.getX()];
    }
    /**
     * returns piece value and at particular position
     * @param  position the position of the piece
     * @return  returns the piece value at position
     */
    public void setPieceAtPosition(int position, int pieceValue){
        Vector positionVector = Vector.convertToVector(boardWidth, boardHeight, position);
         this.board[positionVector.getY()][positionVector.getX()]= pieceValue;
    }
    /**
     * check if particular piece form particular position can move to destination position
     * returns piece value and at particular position
     * @param  destinationPosition destination position of the piece
     * @param  currentPieceVector current position of the piece
     * @param  currentPieceType piece type
     * @return  returns the piece value at position
     */
    public boolean canMove(int destinationPosition,Vector currentPieceVector, PieceType currentPieceType){
        CollisionLogic logic = new CollisionLogic();

        Vector destinationVector = Vector.convertToVector(board[0].length, board.length, destinationPosition);
        if (destinationVector.equals(currentPieceVector)) {

            return false;
        }
        if(board[destinationVector.getY()][destinationVector.getX()] != 0) {
            if(currentPieceType!= PieceType.KNIGHT) {
                List<Vector> listOfPossibleCollistions = logic.checkIfCollision(currentPieceVector, destinationVector);
                for (int j = 0; j < listOfPossibleCollistions.size(); j++) {
                    Vector v = listOfPossibleCollistions.get(j);
                    if (board[v.getY()][v.getX()] != 0) {
                        return false;
                    }
                }
                return true;
            }
            else{
                Vector v = Vector.convertToVector(board[0].length, board.length, destinationPosition);
                if (board[v.getY()][v.getX()] != 0) {
                    return true;
                }
                else return false;
            }
        }
        else
            return false;
    }
    /**
     * metod that returns array of positions of all pieces on the board with
     * @return  returns the piece value at position
     */
    public Vector[] getPiecePositions(){

        List<Vector> pieces = new ArrayList<>();

        for(int i=0;i < board.length;i++){
            for(int j=0; j<board[0].length;j++){
                if(board[i][j] !=0){
                    pieces.add(new Vector(j,i));
                }
            }
        }
        return pieces.toArray(new Vector[pieces.size()]);
    }
    /**
     * metod that beats one piece with another
     * @param destinationPosition beat destination
     * @param currentPiecePosition current piece type value
     * @return  returns new board after beat
     */
    public int[][] performMove(int destinationPosition,Vector currentPiecePosition){
        Vector destinationVector = Vector.convertToVector(board[0].length, board.length, destinationPosition);
        int[][]temp = Lodash.deepCopyIntMatrix(board);
        int value = board[currentPiecePosition.getY()][currentPiecePosition.getX()];
        temp[destinationVector.getY()][destinationVector.getX()] =value;
        temp[currentPiecePosition.getY()][currentPiecePosition.getX()]=0;
        return  temp;
    }
    /**
     * metod that initializes board array
     */
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
