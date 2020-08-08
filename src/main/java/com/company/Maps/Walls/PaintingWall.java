package com.company.Maps.Walls;

import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.GameObject;

public class PaintingWall extends Wall {



  @Override
  public WallType getWallType() {
    return WallType.PAINTING;
  }

  @Override
  public void setObjectType(GameObject painting) {
    objectInWall=painting;
  }




}
