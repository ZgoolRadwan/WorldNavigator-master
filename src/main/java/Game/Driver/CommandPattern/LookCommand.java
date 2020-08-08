package Game.Driver.CommandPattern;

import Game.Driver.Player.MazePlayer;

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
