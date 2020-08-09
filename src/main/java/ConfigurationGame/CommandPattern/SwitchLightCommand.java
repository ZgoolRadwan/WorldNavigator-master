package ConfigurationGame.CommandPattern;

import ConfigurationGame.Player.MazePlayer;

public class SwitchLightCommand implements Command {

  MazePlayer player;

  public SwitchLightCommand(MazePlayer player) {
    this.player = player;
  }


  @Override
  public String execute(String optionalParameter) {
    return player.getPlayerStatus().getCurrentRoom().switchLight();
  }
}
