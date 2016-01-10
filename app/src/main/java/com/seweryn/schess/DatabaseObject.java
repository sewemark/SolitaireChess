package com.seweryn.schess;

import android.provider.ContactsContract;

import java.util.List;

/**
 * Created by sew on 2016-01-03.
 */
public class DatabaseObject  implements java.io.Serializable{
    private  int[][] object;
    private  List<Solution> solutions;
    public DatabaseObject(int[][] _object, List<Solution> _solutions){
        this.object = _object;
        this.solutions = _solutions;
    }
    public int[][]getBoard(){
        return this.object;
    }
    public List<Solution> getSolutions(){
        return  this.solutions;
    }
}
