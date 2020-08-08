package com.company.CommandPattern;
import com.company.ObjectsForWalls.GameObject;
import com.company.Player.MazePlayer;

public class CheckCommand implements Command {
  MazePlayer mazePlayer;
  GameObject facingGameObject;
  public CheckCommand(MazePlayer player)
  {
    mazePlayer=player;
    facingGameObject =player.getFacingWall().getObjectInWall();
  }
  @Override
  public String execute(String optionalParameter) {
   return mazePlayer.getFacingWall().getObjectInWall().checkObject(mazePlayer.getPlayerStatus());
  }
}
