package com.seweryn.schess.Controllers;

import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

/**
 * Created by sew on 2016-01-10.
 */
public interface IDatabaseContextController {
     DatabaseObject read(PuzzleType puzzleType, String boardName);
     void save(PuzzleType puzzleType, int[][] t1);
     String[] getPuzzleList();
     String[] getPuzzleListByType(PuzzleType t1);
}
