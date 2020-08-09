package ConfigurationGame.Map.BehaviorsForWalls.Passable;

import ConfigurationGame.Map.Room;

public class Passable implements PassableBehavior {

  private Room nextRoom;



  public Room getNextRoom() {
    return nextRoom;
  }


  public void setNextRoom(Room nextRoom) {
    this.nextRoom = nextRoom;
  }



  @Override
  public String toString() {
    return "WithDoor{" +
            "nextRoom=" + nextRoom +
            '}';
  }

}
