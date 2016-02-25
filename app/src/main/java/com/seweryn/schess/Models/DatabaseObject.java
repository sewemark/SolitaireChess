package com.seweryn.schess.Models;

import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Static.Lodash;

import java.util.List;

/**
 * Created by sew on 2016-01-03.
 */
public class DatabaseObject  implements java.io.Serializable{
    private int[][] object;
    private List<Solution> solutions;
    private PuzzleType puzzleType;
    private String fileName;
    private boolean isSolved;
    private boolean wasHintUses;

    public DatabaseObject(int[][] _object, List<Solution> _solutions, PuzzleType _puzzleType, String _fileName){
        this.object = Lodash.deepCopyIntMatrix(_object);
        this.solutions = _solutions;
        this.puzzleType = _puzzleType;
        this.fileName = _fileName;
        this.isSolved =false;
        this.wasHintUses =false;
    }
    public void setSolved(){
        this.isSolved=true;
    }
    public void setHintsUsed(){
        this.wasHintUses=true;
    }
    public boolean isPuzzleSolved() {return this.isSolved;}
    public boolean wasHintsUsed() {return this.wasHintUses;}
    public int[][]getBoard(){
        return this.object;
    }
    public String getFileName(){
       return this.fileName;
    }
    public PuzzleType getPuzzleType(){
        return this.puzzleType;
    }
    public List<Solution> getSolutions(){
        return  this.solutions;
    }
}
