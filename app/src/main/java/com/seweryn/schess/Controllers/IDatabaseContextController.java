package com.seweryn.schess.Controllers;

import android.provider.ContactsContract;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

/**
 * Created by sew on 2016-01-10.
 */
public interface IDatabaseContextController {
     DatabaseObject read(PuzzleType puzzleType, String boardName);
     void save(PuzzleType puzzleType, int[][] t1);
     void update(DatabaseObject databaseObject);
     String[] getPuzzleList();
     String[] getPuzzleListByType(PuzzleType t1);
     DatabaseObject[] getPuzzleObjectByType(PuzzleType t1);
     DatabaseObject readNextPuzzle(PuzzleType puzzleType, String boardName);
     DatabaseObject readPreviousPuzzle(PuzzleType puzzleType, String boardName);
     PuzzleType getNextPuzzleType(PuzzleType t1);
     void resetDatabase();

     String getNextBoardName(String t1);
}
