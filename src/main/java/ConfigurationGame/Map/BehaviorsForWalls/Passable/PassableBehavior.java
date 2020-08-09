package ConfigurationGame.Map.BehaviorsForWalls.Passable;

import ConfigurationGame.Map.Room;

public interface PassableBehavior {
  Room getNextRoom();
  void setNextRoom(Room nextRoom);

}
