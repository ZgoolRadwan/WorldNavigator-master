package com.company.Maps.Walls;

import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.GameObject;
import com.company.StrategyPattern.Passable.PassableBehavior;
import com.company.StrategyPattern.Lockable.Lockable;
import com.company.Player.PlayerStatus;
import com.company.Maps.Room;

import java.util.Objects;


public abstract class Wall {

  protected GameObject objectInWall;
  public abstract WallType getWallType();
  public abstract void setObjectType(GameObject objectInWall);
  public GameObject getObjectInWall()
  {
    return this.objectInWall;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Wall wall = (Wall) o;
    return objectInWall.equals(wall.objectInWall);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectInWall);
  }

}
