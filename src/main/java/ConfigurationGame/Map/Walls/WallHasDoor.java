package ConfigurationGame.Map.Walls;

import Constants.WallType;
import ConfigurationGame.WallObjects.GeneralObject;


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
