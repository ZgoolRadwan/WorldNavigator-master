package com.company.Maps.Walls;
import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.EmptyObject;
import com.company.ObjectsForWalls.GameObject;

public class EmptyWall extends Wall {

  @Override
  public WallType getWallType() {
    return WallType.PLAIN;
  }

  @Override
  public void setObjectType(GameObject empty) {
    objectInWall=new EmptyObject();
  }

  @Override
  public GameObject getObjectInWall() {
    return new EmptyObject();
  }

  //  @Override
//  public void checkWall(PlayerStatus playerStatus) {
//    System.out.println("Empty wall no object, change your direction and explore other wall");
//  }

  @Override
  public String toString() {
    return "Empty Wall";
  }
}
