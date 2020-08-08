package com.company.CommandPattern;

import com.company.Enumerations.ItemType;
import com.company.Items.GameItem;
import com.company.Player.MazePlayer;

import java.util.ArrayList;

public class CheckRoofCommand implements Command {
    private MazePlayer mazePlayer;
    private ArrayList<GameItem>roofItems;
    public CheckRoofCommand(MazePlayer player) {
        this.mazePlayer=player;

    }

    @Override
    public String execute(String optionalParameter) {
        this.roofItems=mazePlayer.getPlayerStatus().getCurrentRoom().getRoofItems();
        //mazePlayer.getPlayerStatus().addListOfItems(roofItems);
        if(roofItems.size()>0){
        for(GameItem gameItem:this.roofItems)
            if(gameItem.getItemType()!= ItemType.GOLD)
                mazePlayer.getPlayerStatus().addItemToPlayerBag(gameItem);
            else
                mazePlayer.getPlayerStatus().addGolds(gameItem.getPrice());
            return "Roof items added to your bag.";
        }
        else
            return "no items on roof.";
    }
}
