package com.seweryn.schess;

import java.util.List;

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
}
