package com.seweryn.schess;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.seweryn.schess.Activities.MainMenuActivity;

/**
 * Created by sew on 06/02/2016.
 */
public class SCMainMenuActivityTest extends
        ActivityInstrumentationTestCase2<MainMenuActivity> {
    private MainMenuActivity testActivity;
    private Button buttonQuickChallenge;
    private Button buttonSelectChallene;
    private Button buttonSetting;
    private Button buttonCreateMap;

    public SCMainMenuActivityTest() {
        super(MainMenuActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        testActivity = getActivity();
        buttonQuickChallenge = (Button) testActivity
                .findViewById(R.id.Button01);
        testActivity = getActivity();
        buttonSelectChallene = (Button) testActivity
                .findViewById(R.id.Button02);
        testActivity = getActivity();
        buttonSetting = (Button) testActivity
                .findViewById(R.id.Button03);
        testActivity = getActivity();
        buttonCreateMap = (Button) testActivity
                .findViewById(R.id.Button04);

    }

    public void testPreconditions() {
        assertNotNull("buttonQuickChallenge is null", buttonQuickChallenge);
        assertNotNull("buttonSelectChallene is null", buttonSelectChallene);
        assertNotNull("buttonSetting is null", buttonSetting);
        assertNotNull("buttonCreateMap is null", buttonCreateMap);
    }
}
