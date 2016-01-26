package com.seweryn.schess.Controllers;

import com.seweryn.schess.Logic.CollisionLogic;

/**
 * Created by sew on 2016-01-24.
 */
public class SCCollisionLogicController  implements ICollisionLogicInjector{
    @Override
    public ICollisionLogicController getCollisionLogicController() {
        return new CollisionLogicController(new CollisionLogic());
    }
}
