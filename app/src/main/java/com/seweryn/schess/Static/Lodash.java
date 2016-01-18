package com.seweryn.schess.Static;

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

    public static <T> boolean  HasElement(T[] array, T element){
        for(int i=0;i<array.length;i++){
            if(array[i].equals(element))
                return  true;
        }
        return  false;
    }
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
    public static PieceType getPiecType(int key){
        Map<Integer,PieceType> map =  new HashMap<Integer,PieceType>();
        map.put(1,PieceType.KING);
        map.put(2,PieceType.TOWER);
        map.put(3,PieceType.PAWN);
        map.put(4,PieceType.BISHOP);
        map.put(5,PieceType.HORSE);
        map.put(6, PieceType.QUEEN);
        return map.get(key);
    }
    public static int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null)
            return null;
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
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


    public static int getResource(PieceType pieceType){
        int resource = 0;

        if(pieceType ==PieceType.KING)
            resource = R.drawable.king_white;
        else if(pieceType == PieceType.TOWER)
            resource = R.drawable.rock_white;
        else if(pieceType == PieceType.PAWN)
            resource=R.drawable.pawn_white;
        else if(pieceType == PieceType.BISHOP)
            resource = R.drawable.bishop_white;
        else if(pieceType == PieceType.HORSE)
            resource = R.drawable.knight_white;
        else if(pieceType == PieceType.QUEEN)
            resource = R.drawable.queen_white;

        return  resource;
    }

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
}
