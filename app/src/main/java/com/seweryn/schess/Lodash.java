package com.seweryn.schess;

import java.util.HashMap;
import java.util.List;
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
    public static int[][] deepCopyIntMatrix(int[][] input) {
        if (input == null)
            return null;
        int[][] result = new int[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }
}
