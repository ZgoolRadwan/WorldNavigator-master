package com.company.CommandPattern;

import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.Door;
import com.company.Player.MazePlayer;

public class OpenCommand implements Command {

  MazePlayer player;
  WallType typeOfWall;
  Door door;

  public OpenCommand(MazePlayer player) {
    this.player=player;
    typeOfWall=player.getFacingWall().getWallType();
  }

  @Override
  public String execute(String optionalParameter) {
    if(player.getFacingWall().getObjectInWall().getObjectName().equals("Door"))
    {
      door=(Door)player.getFacingWall().getObjectInWall();
      return door.openDoor();

    }return "Can't open Object";


  }
}
