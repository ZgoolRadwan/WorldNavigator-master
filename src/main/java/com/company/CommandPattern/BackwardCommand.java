package com.company.CommandPattern;

import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.Door;
import com.company.Player.MazePlayer;

public class BackwardCommand implements Command {

  MazePlayer player;
  WallType typeOfWall;
  Door door;

  public BackwardCommand(MazePlayer player) {
    this.player = player;
    //System.out.println(player.getBehindWall().getObjectInWall().getObjectName());
    typeOfWall=player.getBehindWall().getWallType();
    //facingWall=player.getFacingWall().getObjectInWall();

  }
  @Override
  public String execute(String optionalParameter) {
    if(typeOfWall==WallType.DOOR)
    {
      door=(Door)player.getFacingWall().getObjectInWall();
      if(isPlayerFacingOpenDoor(door)){
        player.getPlayerStatus().setStartingRoom(door.getNextRoom());
        return "you entered next room.";
      }
      else if(isPlayerFacingClosedDoor(door))
       return door.checkObject(player.getPlayerStatus());
      else{
       return "No rooms next, The wall in the behind is " + player.getBehindWall().getWallType() + " not door.";
      }

    }
    return "Can't backward";
  }

  private boolean isPlayerFacingOpenDoor(Door door)
  {
    return (door.isDoorOpen());
  }

  private boolean isPlayerFacingClosedDoor(Door door)
  {
    return (!door.isDoorOpen());
  }
}
