package com.company.StrategyPattern.Passable;

import com.company.Maps.Room;

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
