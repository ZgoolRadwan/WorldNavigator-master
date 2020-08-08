package com.company.StrategyPattern.Passable;

import com.company.Maps.Room;

public interface PassableBehavior {
  Room getNextRoom();
  void setNextRoom(Room nextRoom);

}
