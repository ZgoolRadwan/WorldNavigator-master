package com.company.CommandPattern;

import com.company.Enumerations.ItemType;
import com.company.Items.GameItem;
import com.company.Player.MazePlayer;

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
        ArrayList<GameItem>itemsList=player.getPlayerStatus().getPlayerBag();
        for(GameItem gameItem:itemsList)
        {
          if(gameItem.getItemType()== ItemType.FLASHLIGHT)
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
