package ConfigurationGame.Map.Walls;

import Constants.WallType;
import ConfigurationGame.WallObjects.GeneralObject;

public class MirrorWall extends GeneralWall {


  @Override
  public WallType getWallType() {
    return WallType.MIRROR;
  }

  @Override
  public void setObjectType(GeneralObject mirror) {
    objectInWall=mirror;
  }

}
