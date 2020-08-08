package Game.Driver.Map.Walls;
import Constants.WallType;
import Game.Driver.WallObjects.EmptyObject;
import Game.Driver.WallObjects.GeneralObject;

public class EmptyWall extends GeneralWall {

  @Override
  public WallType getWallType() {
    return WallType.PLAIN;
  }

  @Override
  public void setObjectType(GeneralObject empty) {
    objectInWall=new EmptyObject();
  }

  @Override
  public GeneralObject getObjectInWall() {
    return new EmptyObject();
  }

  //  @Override
//  public void checkWall(PlayerStatus playerStatus) {
//    System.out.println("Empty wall no object, change your direction and explore other wall");
//  }

  @Override
  public String toString() {
    return "Empty GeneralWall";
  }
}
