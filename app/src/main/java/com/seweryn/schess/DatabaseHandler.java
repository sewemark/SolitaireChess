package com.seweryn.schess;

import android.content.Context;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * Created by sew on 2015-11-23.
 */
public class DatabaseHandler {
    public DatabaseHandler(Context _context) {
        this.context = _context;
        rootPath = context.getCacheDir().toString();
    }
    private Context context;
    private String rootPath;
    private final String[] puzzleDirectories ={"easy","medium","hard","veryHard"};
    private final String rootDirectory ="puzzles";

    public  void CreateDatabaseIfNotExists() {

        File parrentDir = new File(context.getCacheDir(), rootDirectory);

        if (!parrentDir.exists()) {
            parrentDir.mkdir();
        }
        for (int i = 0; i < puzzleDirectories.length; i++) {
            File childDir = new File(context.getCacheDir() + "/" + rootDirectory, puzzleDirectories[i]);
            if (!childDir.exists()) {
                childDir.mkdir();
            }

        }
      }
     public  void savePuzzle(PuzzleType puzleType, int[][] puzzleToSave ) {
         try{
             File parrentDir = new File(context.getCacheDir(), rootDirectory);
             int filesSize = parrentDir.list().length;
             FileOutputStream fos = context.openFileOutput(rootDirectory + '/' + puzleType.toString() + '/'  + String.valueOf(filesSize + 1), Context.MODE_PRIVATE);
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             oos.writeObject(puzzleToSave);
             oos.close();
             fos.close();
         }
         catch (Exception ex){

         }
     }
    public  int [][] readPuzzle(PuzzleType puzzleType, int[][] puzzleToSave, String fileName ) {
        try{

            FileInputStream fis = context.openFileInput(rootDirectory + '/' + puzzleType.toString() + '/'  + fileName);
            ObjectInputStream iis = new ObjectInputStream(fis);
            int[][] board = (int[][])iis.readObject();
            return  board;
        }
        catch (Exception ex){

        }
        return  null;
    }
    public  String[] getPuzzleListByType(PuzzleType puzzleType){
        File parrentDir = new File(context.getCacheDir().toString() + '/' + rootDirectory + '/' + puzzleType.toString());
        if(parrentDir.exists())
            return  parrentDir.list();
        else
            return  new String[]{};
    }
}

