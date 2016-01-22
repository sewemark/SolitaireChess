package com.seweryn.schess.Models;

/**
 * Created by sew on 2016-01-19.
 */
public class DataContainer{
    public DataContainer(String _boardName, boolean _wasSolved){
        this.boardname = _boardName;
        this.wasSolved =_wasSolved;
    }
    public String boardname;
    public boolean wasSolved;
}
