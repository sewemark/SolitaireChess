package com.seweryn.schess.DAL;

import android.content.Context;

import com.seweryn.schess.Controllers.IDatabaseContextInjector;

import java.io.File;

/**
 * Created by sew on 23/02/2016.
 */
public class DatabaseManager implements IDatabaseManager {
    private IDatabaseInfo dbInfo;
    private boolean isCreated=false;
    public DatabaseManager(IDatabaseInfo _dbInfo){
        this.dbInfo = _dbInfo;
    }
    /**
     * method that creates database if it does not exist
     * */
    public  void createDatabaseIfNotExists() {
        File parrentDir = new File(dbInfo.getDatabasePath());
            if (!parrentDir.exists()) {
            parrentDir.mkdir();
            isCreated =true;
            for (int i = 0; i < dbInfo.getPuzzleDirectories().length; i++) {
                File childDir = new File(dbInfo.getDatabasePath() + "/" + dbInfo.getPuzzleDirectories()[i]);
                if (!childDir.exists()) {
                    childDir.mkdir();
                }
            }
        }
    }
    /**
     * method that deltes existing database and creates another one from scrach
     * */
    public  void resetDatabase(){
        File parrentDir = new File(dbInfo.getDatabasePath());
        if(parrentDir.exists()){
            deleteFolderRecursive(parrentDir);
        }
        createDatabaseIfNotExists();

    }
    public boolean wasCreated(){
        return isCreated;
    }
    /**
     * method that deletes folder recurviely
     * */
    private void deleteFolderRecursive(File fileOrDirectory) {

        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteFolderRecursive(child);

        fileOrDirectory.delete();

    }
}
