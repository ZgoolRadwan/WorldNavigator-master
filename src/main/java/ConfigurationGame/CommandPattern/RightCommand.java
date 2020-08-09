package ConfigurationGame.CommandPattern;

import ConfigurationGame.Player.MazePlayer;
import Constants.Directions;

public class RightCommand implements Command {

  MazePlayer player;

  public RightCommand(MazePlayer player) {
    this.player = player;
  }

  @Override
  public String execute(String optionalParameter) {//change player direction.
    switch (player.getPlayerStatus().getPlayerDirection()) {
      case NORTH:
        player.getPlayerStatus().setPlayerDirection(Directions.EAST);
        break;
      case EAST:
        player.getPlayerStatus().setPlayerDirection(Directions.SOUTH);
        break;
      case SOUTH:
        player.getPlayerStatus().setPlayerDirection(Directions.WEST);
        break;
      case WEST:
        player.getPlayerStatus().setPlayerDirection(Directions.NORTH);
        break;
    }
    return "player turned right";
  }
}
