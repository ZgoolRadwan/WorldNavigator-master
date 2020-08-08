package com.company.CommandPattern;

import com.company.Player.MazePlayer;

public class LookCommand implements Command {

  MazePlayer player;
  public LookCommand(MazePlayer player) {
    this.player = player;
  }
  @Override
  public String execute(String optionalParameter)
  {
    return player.getFacingWall().getWallType().toString();
  }
}
