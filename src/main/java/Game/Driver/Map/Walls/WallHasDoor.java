package Game.Driver.Map.Walls;

import Constants.WallType;
import Game.Driver.WallObjects.GeneralObject;


public class WallHasDoor extends GeneralWall {


  @Override
  public WallType getWallType() {
    return WallType.DOOR;
  }

    @Override
    public void setObjectType(GeneralObject door) {
        objectInWall=door;
    }

}
