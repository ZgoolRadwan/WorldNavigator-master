package com.company.Maps.Walls;

import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.GameObject;

public class MirrorWall extends Wall {


  @Override
  public WallType getWallType() {
    return WallType.MIRROR;
  }

  @Override
  public void setObjectType(GameObject mirror) {
    objectInWall=mirror;
  }





//  @Override
//  public String toString() {
//    return "WallwithMirror{" +
//            "mirrorPassword='" + mirrorPassword + '\'' +
//            ", isKeyHidden=" + isKeyHidden +
//            ", keyBehindMirror='" + keyBehindMirror + '\'' +
//            '}';
//  }
}
