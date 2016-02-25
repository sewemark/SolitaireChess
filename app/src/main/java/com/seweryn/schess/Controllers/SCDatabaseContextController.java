package com.seweryn.schess.Controllers;

import android.content.Context;

/**
 * Created by sew on 2016-01-24.
 */
public class SCDatabaseContextController implements IDatabaseContextInjector {

    @Override
    public IDatabaseContextController getDatabaseContextContrller(Context context) {
       return new DatabaseContextController(context);
    }
}
