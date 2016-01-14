package com.seweryn.schess.Controllers;

import android.content.Context;

import com.seweryn.schess.DAL.DatabaseHandler;
import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

/**
 * Created by sew on 2016-01-10.
 */
public class DatabaseContextController implements IDatabaseContextController {

    private DatabaseHandler handler;
    public DatabaseContextController(Context context){
        handler = new DatabaseHandler(context);
    }
    @Override
    public DatabaseObject read(PuzzleType puzzleType, String fileName) {

       return handler.readPuzzle(puzzleType,fileName);
    }

    @Override
    public void save(PuzzleType puzzleType, int[][] board) {
            handler.savePuzzle(puzzleType,board);
    }

    @Override
    public String[] getPuzzleList() {
        return new String[0];
    }

    @Override
    public String[] getPuzzleListByType(PuzzleType puzzleType) {
        return handler.getPuzzleListByType(puzzleType);
    }
}
