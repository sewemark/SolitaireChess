package com.seweryn.schess.Controllers;

import com.seweryn.schess.Logic.MoveRulesLogic;

/**
 * Created by sew on 2016-01-24.
 */
public class SCMoveRulesController implements IMoveRulesControllerInjector {
    @Override
    public IMoveRulesController getMoveRulesController() {
        return  new MoveRulesController(new MoveRulesLogic());
    }
}
