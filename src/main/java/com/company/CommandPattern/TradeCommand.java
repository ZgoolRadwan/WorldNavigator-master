package com.company.CommandPattern;

import com.company.Enumerations.WallType;
import com.company.ObjectsForWalls.Door;
import com.company.ObjectsForWalls.Seller;
import com.company.Player.MazePlayer;

public class TradeCommand implements Command {
  MazePlayer player;
  Seller seller;

  public TradeCommand(MazePlayer player) {
    this.player = player;
  }

  @Override
  public String execute(String optionalParameter) {
    if(player.getFacingWall().getWallType()== WallType.SELLER)
    {
      this.seller=(Seller) player.getFacingWall().getObjectInWall();
     return seller.handleTradeCommand(player.getPlayerStatus(),optionalParameter);

    }return "can't execute commands";

  }
}
