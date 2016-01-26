package com.seweryn.schess.SearchAlgoritm;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Logic.CollisionLogic;
import com.seweryn.schess.Logic.IMoveRulesLogic;
import com.seweryn.schess.Logic.MoveRulesLogic;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.Static.Lodash;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sew on 2015-12-07.
 */
public class LinkedListNode {
    public int [][]board;
    public LinkedListNode parent;
    public List<LinkedListNode> childes;
    public List<Integer> possibleMoves;
    private PieceType currentPiecType;
    private boolean wasExpanded;
    private IMoveRulesLogic logic;
    private Vector currentPiece;
    private  boolean isRoot = false;
    public  LinkedListNode(int[][] _board,IMoveRulesLogic _logic, LinkedListNode _parent,boolean _isRoot, int destinationPosition ){
        this.board = _board;
        this.parent = _parent;
        this.isRoot = _isRoot;
        this.logic = new MoveRulesLogic();
        this.childes = new ArrayList<LinkedListNode>();
        this.wasExpanded =false;
        if(destinationPosition>=0) {
            this.currentPiece = Vector.convertToVector(board[0].length, board.length, destinationPosition);
        }
        else currentPiece= null;
        }
    public void expandChild(){
        this.wasExpanded=true;

            Vector[] allPieces = this.getPieces();
        for(int j=0;j<allPieces.length;j++) {
            this.setPiece(allPieces[j]);
            this.setPieceType(Lodash.getPiecType(board[allPieces[j].getY()][allPieces[j].getX()]));
            this.possibleMoves = Arrays.asList(logic.getMoveStrategy(board[0].length, board.length, getPiceType()).getMoveForPiece(getCurrentPiece()));
            if (this.possibleMoves.size() <= 0) {

            } else {
                for (int i = 0; i < possibleMoves.size(); i++) {

                    Integer destinationPosition = possibleMoves.get(i);
                    if (canMove(destinationPosition)) {
                        LinkedListNode nextChild = new LinkedListNode(this.performMove(destinationPosition),logic, this, false, destinationPosition);
                        this.childes.add(nextChild);
                    }
                }
            }
        }

    }
    public boolean canMove(int destinationPosition){
        CollisionLogic logic = new CollisionLogic();

        Vector destinationVector = Vector.convertToVector(board[0].length, board.length, destinationPosition);
        if (destinationVector.equals(this.currentPiece)) {

            return false;
        }
        if(board[destinationVector.getY()][destinationVector.getX()] != 0) {
            if(this.getPiceType()!=PieceType.HORSE) {
                List<Vector> listOfpossibleCollistions = logic.checkIfCollision(getCurrentPiece(), destinationVector);
                for (int j = 0; j < listOfpossibleCollistions.size(); j++) {
                    Vector v = listOfpossibleCollistions.get(j);
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
    public int[][] performMove(int destinationPosition){
        Vector destinationVector = Vector.convertToVector(board[0].length, board.length, destinationPosition);
        int[][]temp =Lodash.deepCopyIntMatrix(board);
        int value = board[getCurrentPiece().getY()][getCurrentPiece().getX()];
        temp[destinationVector.getY()][destinationVector.getX()] =value;
        temp[getCurrentPiece().getY()][getCurrentPiece().getX()]=0;
        return  temp;
    }
    public Vector[] getPieces(){

        List<Vector> pieces = new ArrayList<>();

        for(int i=0;i < board.length;i++){
            for(int j=0; j<board[0].length;j++){
                if(board[i][j] !=0){
                 //   setPieceType(map.get(board[i][j]));
                    pieces.add(new Vector(j,i));
                }
            }
        }
        return pieces.toArray(new Vector[pieces.size()]);
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
