package Game.Driver.Map.Walls;

import Constants.WallType;
import Game.Driver.WallObjects.GeneralObject;

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
