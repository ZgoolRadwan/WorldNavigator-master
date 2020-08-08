package com.company.CommandPattern;

import com.company.Items.GameItem;
import com.company.Items.Key;
import com.company.Player.MazePlayer;

import java.util.ArrayList;
import java.util.Scanner;

public class UsingKeyCommand implements Command {

  MazePlayer player;

  public UsingKeyCommand(MazePlayer player) {
    this.player = player;
  }

  @Override
  public String execute(String optionalParameter) {

    String lock_key = optionalParameter;
    if (lock_key != null) {
      ArrayList<GameItem>playerKeys=player.getPlayerStatus().getPlayerBag();
      for(GameItem item:playerKeys)
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
