package ConfigurationGame.Map.Walls;

import Constants.WallType;
import ConfigurationGame.WallObjects.GeneralObject;

public class PaintingWall extends GeneralWall {



  @Override
  public WallType getWallType() {
    return WallType.PAINTING;
  }

  @Override
  public void setObjectType(GeneralObject painting) {
    objectInWall=painting;
  }




}
