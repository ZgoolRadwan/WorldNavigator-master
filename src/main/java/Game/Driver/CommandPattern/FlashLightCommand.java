package Game.Driver.CommandPattern;

import Constants.ItemType;
import Game.Driver.PlayerItems.GeneralItem;
import Game.Driver.Player.MazePlayer;

import java.util.ArrayList;

public class FlashLightCommand implements Command {

  MazePlayer player;
  public FlashLightCommand(MazePlayer player) {
    this.player=player;
  }

  @Override
  public String execute(String optionalParameter) {
    if (player.getPlayerStatus().getPlayerBag().size()>0 ) {
      if (!player.getPlayerStatus().getCurrentRoom().hasSwitchLight())
      {
        ArrayList<GeneralItem>itemsList=player.getPlayerStatus().getPlayerBag();
        for(GeneralItem generalItem :itemsList)
        {
          if(generalItem.getItemType()== ItemType.FLASHLIGHT)
          player.getPlayerStatus().getCurrentRoom().useFlashlight();

        }
      }
      else if (player.getPlayerStatus().getCurrentRoom().hasSwitchLight()
          && !player.getPlayerStatus().getCurrentRoom().isRoomLit())
        player.getPlayerStatus().getCurrentRoom().useFlashlight();
      return "Room has switch light use it.";
    } else {
      return "you have not flashlight";
    }
  }

}
