package com.seweryn.schess;

import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sew on 2015-12-07.
 */
public class LinkedListNode {
    public int [][]board;
    public LinkedListNode parent;
    public List<LinkedListNode> childes;
    public List<Integer> possibleMoves;
    private PieceType currentPiecType;
    Map<Integer,PieceType> map =  new HashMap<Integer,PieceType>();
    private boolean wasExpanded;
    private MoveLogic logic;
    private Vector currentPiece;
    private  boolean isRoot = false;
    public  LinkedListNode(int[][] _board, LinkedListNode _parent,boolean _isRoot, int destinationPosition ){
        this.board = _board;
        this.parent = _parent;
        this.isRoot = _isRoot;
        logic = new MoveLogic();
        this.childes = new ArrayList<LinkedListNode>();
        this.wasExpanded =false;
        map.put(1,PieceType.KING);
        map.put(2,PieceType.TOWER);
        map.put(3,PieceType.PAWN);
        map.put(4,PieceType.BISHOP);
        map.put(5,PieceType.HORSE);
        map.put(6, PieceType.QUEEN);
        if(destinationPosition>=0) {
            this.currentPiece = Vector.convertToVecotr(board[0].length, board.length, destinationPosition);
        }
        else currentPiece= null;
        }
    public void expandChild(){
        this.wasExpanded=true;
            if(this.getCurrentPiece() ==null){
                this.setPiece(this.getNextPiece());
            }
            this.possibleMoves = Arrays.asList(logic.PossibleMoves(board[0].length, board.length, getCurrentPiece(), getPiceType()));
            if(this.possibleMoves.size() <=0){

            }
            else{
                for(int i=0;i<possibleMoves.size();i++) {

                    Integer destinationPosition = possibleMoves.get(i);
                    if(canMove(destinationPosition)) {
                        LinkedListNode nextChild = new LinkedListNode(this.performMove(destinationPosition), this, false, destinationPosition);
                        this.childes.add(nextChild);
                    }
                }
            }
    }
    public boolean canMove(int destinationPosition){
        Vector destinationVector = Vector.convertToVecotr(board[0].length, board.length,destinationPosition);
        if (destinationVector.equals(this.currentPiece)) {

            return false;
        }
        if(board[destinationVector.getX()][destinationVector.getY()] != 0)
            return true;
        else
            return false;
    }
    public int[][] performMove(int destinationPosition){
        Vector destinationVector = Vector.convertToVecotr(board[0].length, board.length,destinationPosition);
        int[][]temp =Lodash.deepCopyIntMatrix(board);
        int value = board[getCurrentPiece().getX()][getCurrentPiece().getY()];
        temp[destinationVector.getX()][destinationVector.getY()] =value;
        temp[getCurrentPiece().getX()][getCurrentPiece().getY()]=0;
        return  temp;
    }
    public Vector getNextPiece(){

        for(int i=0;i < board.length;i++){
            for(int j=0; j<board[0].length;j++){
                if(board[i][j] !=0){
                    setPieceType(map.get(board[i][j]));
                    return new Vector(i,j);
                }
            }
        }
        return null;
    }
    public  boolean IsRoot(){return this.isRoot;}
    public Vector getCurrentPiece(){
        return  currentPiece;
    }
    public boolean WasExpanded(){
        return  this.wasExpanded;
    }
    public void setPiece(Vector val){
        this.currentPiece = val;
    }
    public PieceType getPiceType(){
        return this.currentPiecType;
    }
    public void setPieceType(PieceType val){
        this.currentPiecType = val;
    }
}
