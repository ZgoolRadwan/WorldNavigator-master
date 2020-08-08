package Game.Driver.Map.Walls;

import Constants.WallType;
import Game.Driver.WallObjects.GeneralObject;

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
