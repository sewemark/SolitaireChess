package com.seweryn.schess.DAL;

/**
 * Created by sew on 23/02/2016.
 */
public interface IDatabaseManager {
    void createDatabaseIfNotExists();
    void resetDatabase();
    boolean wasCreated();
}
