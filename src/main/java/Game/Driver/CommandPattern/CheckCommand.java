package Game.Driver.CommandPattern;
import Game.Driver.Player.MazePlayer;
import Game.Driver.WallObjects.GeneralObject;

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
