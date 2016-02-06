package com.seweryn.schess;
import android.content.Context;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.io.FileOutputStream;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import android.content.Context;

import com.seweryn.schess.DAL.DatabaseHandler;
import com.seweryn.schess.Enums.PuzzleType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by sew on 06/02/2016.
 */
public class DALTest {
    @Mock
    Context context;

    @Mock
    File database;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void creataGettingListOfEasyPuzzleShouldReturnListOfEasyPuzzles() {
        try {
            DatabaseHandler dbHandler = new DatabaseHandler(context);
            dbHandler.CreateDatabaseIfNotExists();
            when(dbHandler.getPuzzleListByType(PuzzleType.EASY)).thenReturn(new String[]{"1E", "2E", "3E", "4E", "5E"});
            verify(database, atLeast(4)).mkdir();
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}
