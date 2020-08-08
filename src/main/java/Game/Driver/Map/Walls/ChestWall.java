package Game.Driver.Map.Walls;

import Constants.WallType;
import Game.Driver.WallObjects.GeneralObject;

public class ChestWall extends GeneralWall {

  @Override
  public WallType getWallType() {
    return WallType.CHEST;
  }

  @Override
  public void setObjectType(GeneralObject chest) {
    objectInWall=chest;
  }



//    @Override
//    public String toString() {
//      return "ChestBuilder{" +
//              "chestItems=" + chestItems +
//              ", chestGolds=" + chestGolds +
//              ", chestKey='" + chestKey + '\'' +
//              '}';
//    }
 // }
}
