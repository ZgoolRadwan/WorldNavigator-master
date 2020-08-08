package com.company.StrategyPattern.Passable;

import com.company.Maps.Room;

public class CannotPassed implements PassableBehavior {


  public Room getNextRoom() {
    return null;
  }


  public void setNextRoom(Room nextRoom) {}



}
