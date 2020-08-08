package com.company.CommandPattern;

import com.company.Enumerations.Direction;
import com.company.Player.MazePlayer;

public class LeftCommand implements Command {

  MazePlayer player;

  public LeftCommand(MazePlayer player) {
    this.player = player;
  }

  @Override
  public String execute(String optionalParameter) {//change player direction.

    switch (player.getPlayerStatus().getPlayerDirection()) {
      case NORTH:
        player.getPlayerStatus().setPlayerDirection(Direction.WEST);
        break;
      case WEST:
        player.getPlayerStatus().setPlayerDirection(Direction.SOUTH);
        break;
      case SOUTH:
        player.getPlayerStatus().setPlayerDirection(Direction.EAST);
        break;
      case EAST:
        player.getPlayerStatus().setPlayerDirection(Direction.NORTH);
        break;
    }
    return "player turned left";
  }
}
