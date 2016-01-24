package com.seweryn.schess.Adapters;
;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v4.app.FragmentStatePagerAdapter;

import com.seweryn.schess.Controllers.IDatabaseContextController;
import com.seweryn.schess.Enums.PuzzleType;
import com.seweryn.schess.Models.DatabaseObject;
import com.seweryn.schess.PuzzleTypeFragment;

import java.util.List;


public class PuzzleTabsAdapter extends FragmentStatePagerAdapter   {

    private int numberOfTabs;
    private IDatabaseContextController databaseContextController;
    public PuzzleTabsAdapter(IDatabaseContextController _databaseController, FragmentManager fragmentManager , int _numberOfTabs) {

        super(fragmentManager);
        this.databaseContextController = _databaseController;
        this.numberOfTabs  = _numberOfTabs;
    }

    @Override
    public Fragment getItem(int fragmentIndex) {
        Bundle bundle = new Bundle();
        DatabaseObject[] puzzleList=null;
        PuzzleType puzzleType = PuzzleType.values()[fragmentIndex];
        puzzleList = databaseContextController.getPuzzleObjectByType(puzzleType);

        bundle.putSerializable("list", puzzleList);
        bundle.putChar("puzzleType",puzzleType.toString().charAt(0));
        bundle.putString("type",puzzleType.toString());
        PuzzleTypeFragment fragment= new PuzzleTypeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {

        return this.numberOfTabs;
    }

}