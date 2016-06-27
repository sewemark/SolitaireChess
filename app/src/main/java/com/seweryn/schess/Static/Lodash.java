package com.seweryn.schess.Static;

import android.content.Context;
import android.util.DisplayMetrics;

import com.seweryn.schess.Enums.Directions;
import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Models.Vector;
import com.seweryn.schess.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sew on 2015-12-03.
 */
public class Lodash {

    /**
     * generic method that checks if element of generic type is array
     * @param  array generic array to look for element
     * @param  element element to find
     * @return  boolean value that indicates if element is in array
     * */
    public static <T> boolean  HasElement(T[] array, T element){
        for(int i=0;i<array.length;i++){
            if(array[i].equals(element))
                return  true;
        }
        return  false;
    }
    /**
     *  method that returns vecotr that lead to particular direction in cartesian
     * @param  direction
     * @return  vector object for particular direction
     * */
    public static Vector getVectorForDirection(Directions direction){

        Map<Directions,Vector>  map =  new HashMap<Directions,Vector>();
        map.put(Directions.E, new Vector(1,0));
        map.put(Directions.W, new Vector(-1,0));
        map.put(Directions.S, new Vector(0,1));
        map.put(Directions.N, new Vector(0,-1));
        map.put(Directions.SE, new Vector(1,1));
        map.put(Directions.SW, new Vector(-1,1));
        map.put(Directions.NE, new Vector(1,-1));
        map.put(Directions.NW, new Vector(-1,-1));
        return  map.get(direction);
    }
    /**
     *  method that return piece type for particular integer value
     * @param  key integer value  represents some piece type
     * @return  piece type for particular key value
     * */
    public static PieceType getPiecType(int key){
        Map<Integer,PieceType> map =  new HashMap<Integer,PieceType>();
        map.put(1,PieceType.KING);
        map.put(2,PieceType.ROOK);
        map.put(3,PieceType.PAWN);
        map.put(4,PieceType.BISHOP);
        map.put(5,PieceType.KNIGHT);
        map.put(6, PieceType.QUEEN);
        map.put(0, PieceType.NOPIECE);
        map.put(null, PieceType.NOPIECE);
        return map.get(key);
    }
    /**
     *  method that returns deep copy of two dimensionl integer array
     * @param  input source integer array
     * @return  deep copy of input array
     * */
    public static int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null)
            return null;
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
    /**
     *  method that checks if two two dimensional integer arrays are equal(store the same values)
     * @param  board  integer array
     * @param  nextboard  integer array
     * @return  boolean value that indicates if input arrays are equal
     * */
    public static boolean areBoardsEqual(int[][] board, int[][]nextboard){
        if(board.length != nextboard.length)
            return false;
        if(board[0].length != nextboard[0].length)
            return  false;
        for(int i=0;i<board.length ;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]!=nextboard[i][j]){
                    return false;
                }
            }
        }
        return  true;
    }

    /**
     *  method that returns drawable resource for particular piece type
     * @param  pieceType  piece type
     * @return  drawable resource
     * */
    public static int getResource(PieceType pieceType){
        int resource = 0;

        if(pieceType ==PieceType.KING)
            resource = R.drawable.king_white;
        else if(pieceType == PieceType.ROOK)
            resource = R.drawable.rock_white;
        else if(pieceType == PieceType.PAWN)
            resource=R.drawable.pawn_white;
        else if(pieceType == PieceType.BISHOP)
            resource = R.drawable.bishop_white;
        else if(pieceType == PieceType.KNIGHT)
            resource = R.drawable.knight_white;
        else if(pieceType == PieceType.QUEEN)
            resource = R.drawable.queen_white;

        return  resource;
    }
    /**
     *  method that returns drawable resource for particular piece type value
     * @param  tabValue  piece type value
     * @return  drawable resource
     * */
    public static int getResource(int tabValue){
        int resource = 0;
        switch (tabValue){
            case 1: resource =R.drawable.kingpiece;
                break;
            case 2: resource =R.drawable.rockpiece;
                break;
            case 3: resource=R.drawable.pawnpiece;
                break;
            case 4: resource=R.drawable.bishoppiece;
                break;
            case 5: resource =R.drawable.knightpiece;
                break;
            case 6: resource=R.drawable.queenpiece;
            default:
                break;
        }
        return  resource;
    }
    /**
     *  method that dp value to px value
     * @param  dp  value in density independent unit
     * @param context
     * @return  input dp value converted to px value
     * */
    public static int dpToPx(int dp,Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int px = Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }
    /**
     *  method that px value to dp vale
     * @param  px  value in pixel unit
     * @param context
     * @return  input px value converted to dp value
     * */
    public static int pxToDp(int px,Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int dp = Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
        return dp;
    }
    /**
     *  method convert two dimensional integer array to Integer array
     * @param  intArray  integer array
     * @return  two dimensional Integer array
     * */
    public static Integer[][] intToIntegerArray(int[][] intArray){
        Integer[][] newArray = new Integer[intArray.length][intArray[0].length];
        for (int i=0;i<intArray.length;i++) {
            for(int j =0;j<intArray[0].length;j++) {
                newArray[i][j] = Integer.valueOf(intArray[i][j]);

            }
        }
        return  newArray;
    }
    /**
     *  method convert two dimensional Integer array to integer array
     * @param  intArray  Integer array
     * @return  two dimensional integer array
     * */
    public static int[][] integerToIntArray(Integer[][] intArray){
        int[][] newArray = new int[intArray.length][intArray[0].length];
        for (int i=0;i<intArray.length;i++) {
            for(int j =0;j<intArray[0].length;j++) {
                newArray[i][j] = Integer.valueOf(intArray[i][j]);

            }
        }
        return  newArray;
    }
    /**
     *  method convert string to int
     * @param  String value
     * @return  Interger value
     * */
    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
