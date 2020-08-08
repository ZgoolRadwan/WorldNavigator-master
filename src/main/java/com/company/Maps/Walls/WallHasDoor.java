package com.company.Maps.Walls;

import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.GameObject;


public class WallHasDoor extends Wall {


  @Override
  public WallType getWallType() {
    return WallType.DOOR;
  }

    @Override
    public void setObjectType(GameObject door) {
        objectInWall=door;
    }

}
