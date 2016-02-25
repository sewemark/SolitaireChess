package com.seweryn.schess.DAL;

import java.io.File;

/**
 * Created by sew on 23/02/2016.
 */
public interface IDatabaseInfo {
    void setDatabasePath(File cachePath);
    String getDatabasePath();
    String[] getPuzzleDirectories();
    String getDatabaseName();
}
