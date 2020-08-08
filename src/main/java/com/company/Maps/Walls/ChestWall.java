package com.company.Maps.Walls;

import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.GameObject;

public class ChestWall extends Wall {

  @Override
  public WallType getWallType() {
    return WallType.CHEST;
  }

  @Override
  public void setObjectType(GameObject chest) {
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
