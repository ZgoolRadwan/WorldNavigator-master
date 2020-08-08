package Game.Driver.Map.BehaviorsForWalls.Passable;

import Game.Driver.Map.Room;

public interface PassableBehavior {
  Room getNextRoom();
  void setNextRoom(Room nextRoom);

}
