package ConfigurationGame.CommandPattern;
import ConfigurationGame.Player.MazePlayer;
import ConfigurationGame.WallObjects.GeneralObject;

public class CheckCommand implements Command {
  MazePlayer mazePlayer;
  GeneralObject facingGeneralObject;
  public CheckCommand(MazePlayer player)
  {
    mazePlayer=player;
    facingGeneralObject =player.getFacingWall().getObjectInWall();
  }
  @Override
  public String execute(String optionalParameter) {
   return mazePlayer.getFacingWall().getObjectInWall().checkObject(mazePlayer.getPlayerStatus());
  }
}
