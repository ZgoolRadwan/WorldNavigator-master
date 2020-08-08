package com.company.CommandPattern;

import com.company.Player.MazePlayer;

public class PrintStatusCommand implements Command {
  MazePlayer player;

  public PrintStatusCommand(MazePlayer player) {
    this.player = player;
  }

  @Override
  public String execute(String optionalParameter) {
    return player.getPlayerStatus().printPlayerStatus();
  }
}
