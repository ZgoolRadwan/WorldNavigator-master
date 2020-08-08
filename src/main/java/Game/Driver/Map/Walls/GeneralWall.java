package Game.Driver.Map.Walls;

import Constants.WallType;
import Game.Driver.WallObjects.GeneralObject;

import java.util.Objects;


public abstract class GeneralWall {

  protected GeneralObject objectInWall;
  public abstract WallType getWallType();
  public abstract void setObjectType(GeneralObject objectInWall);
  public GeneralObject getObjectInWall()
  {
    return this.objectInWall;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    GeneralWall wall = (GeneralWall) o;
    return objectInWall.equals(wall.objectInWall);
  }

  @Override
  public int hashCode() {
    return Objects.hash(objectInWall);
  }

}
