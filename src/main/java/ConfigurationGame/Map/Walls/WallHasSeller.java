package ConfigurationGame.Map.Walls;

import Constants.WallType;
import ConfigurationGame.WallObjects.GeneralObject;

public class WallHasSeller extends GeneralWall {

  @Override
  public WallType getWallType() {
    return WallType.SELLER;
  }

  @Override
  public void setObjectType(GeneralObject seller) {
    objectInWall=seller;
  }

}
