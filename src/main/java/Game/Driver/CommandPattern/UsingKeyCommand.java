package Game.Driver.CommandPattern;

import Game.Driver.PlayerItems.GeneralItem;
import Game.Driver.PlayerItems.Key;
import Game.Driver.Player.MazePlayer;

import java.util.ArrayList;

public class UsingKeyCommand implements Command {

  MazePlayer player;

  public UsingKeyCommand(MazePlayer player) {
    this.player = player;
  }

  @Override
  public String execute(String optionalParameter) {

    String lock_key = optionalParameter;
    if (lock_key != null) {
      ArrayList<GeneralItem>playerKeys=player.getPlayerStatus().getPlayerBag();
      for(GeneralItem item:playerKeys)
        if(item.getName().equals(lock_key))
        {
          if(player.getFacingWall().getObjectInWall().hasLock()){
            Key key=(Key) item;
            return  player.getFacingWall().getObjectInWall().useKey(key);

          }
        }
    }
    return "You have not this key";
  }
}
