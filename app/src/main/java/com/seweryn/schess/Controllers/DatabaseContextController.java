package com.seweryn.schess.Controllers;

import android.content.Context;
import android.provider.ContactsContract;

import com.seweryn.schess.DAL.DatabaseHandler;
import com.seweryn.schess.Enums.PieceType;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;

/**
 * Created by sew on 2016-01-10.
 */
public class DatabaseContextController implements IDatabaseContextController {
    @Override
    public void update(DatabaseObject databaseObject) {
        handler.updatePuzzle(databaseObject);
    }

    @Override
    public PuzzleType getNextPuzzleType(PuzzleType t1) {
        return PuzzleType.EASY;
    }

    @Override
    public void resetDatabase() {
        handler.resetDatabase();
    }

    @Override
    public String getNextBoardName(String t1) {
        return "2E";
    }

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

    @Override
    public DatabaseObject[] getPuzzleObjectByType(PuzzleType puzzleType) {
        return handler.getPuzzleObjecyByType(puzzleType);
    }

    @Override
    public DatabaseObject readNextPuzzle(PuzzleType puzzleType, String boardName) {
        return handler.readNextOrPreviousPuzzle(puzzleType, boardName, 1);
    }
    @Override
    public DatabaseObject readPreviousPuzzle(PuzzleType puzzleType, String boardName) {
        return handler.readNextOrPreviousPuzzle(puzzleType, boardName, -1);
    }
}
