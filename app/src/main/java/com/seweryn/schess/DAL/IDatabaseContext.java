package com.seweryn.schess.DAL;

import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

/**
 * Created by sew on 23/02/2016.
 */
public interface IDatabaseContext {
    void updatePuzzle(DatabaseObject objectToUpdate);
    void savePuzzle(PuzzleType puzleType, int[][] puzzleToSave );
    DatabaseObject readNextOrPreviousPuzzle(PuzzleType puzzleType, String fileName,int prevOrNext);
    PuzzleType getPuzzleType(PuzzleType puzzleType, int prevOrNext);
    DatabaseObject readPuzzle(PuzzleType puzzleType, String fileName );
    DatabaseObject[] getPuzzleObjectByType(PuzzleType puzzleType);
    String[] getPuzzleListByType(PuzzleType puzzleType);
}
