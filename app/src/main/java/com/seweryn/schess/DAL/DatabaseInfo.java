package com.seweryn.schess.DAL;

import android.content.Context;

import java.io.File;

/**
 * Created by sew on 23/02/2016.
 */
public  class DatabaseInfo implements IDatabaseInfo {
    public  DatabaseInfo(Context context) {
         setDatabasePath(context.getCacheDir());
    }

    private  String rootPath;
    private String databasePath;
    private final String[] puzzleDirectories ={"EASY","MEDIUM","HARD","VERYHARD"};
    private   static final String databaseName ="puzzles185";
    public String getDatabaseName(){
        return databaseName;
    }
    public  void setDatabasePath(File cachePath){
       databasePath= cachePath + "/" + databaseName;
    }
    public String getDatabasePath(){
        return  databasePath;
    }
    public  String[] getPuzzleDirectories(){
        return puzzleDirectories;
    }
}
