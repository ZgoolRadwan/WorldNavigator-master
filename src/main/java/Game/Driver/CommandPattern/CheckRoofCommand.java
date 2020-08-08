package Game.Driver.CommandPattern;

import Constants.ItemType;
import Game.Driver.PlayerItems.GeneralItem;
import Game.Driver.Player.MazePlayer;

import java.util.ArrayList;

public class CheckRoofCommand implements Command {
    private MazePlayer mazePlayer;
    private ArrayList<GeneralItem>roofItems;
    public CheckRoofCommand(MazePlayer player) {
        this.mazePlayer=player;

    }

    @Override
    public String execute(String optionalParameter) {
        this.roofItems=mazePlayer.getPlayerStatus().getCurrentRoom().getRoofItems();
        //mazePlayer.getPlayerStatus().addListOfItems(roofItems);
        if(roofItems.size()>0){
        for(GeneralItem generalItem :this.roofItems)
            if(generalItem.getItemType()!= ItemType.GOLD)
                mazePlayer.getPlayerStatus().addItemToPlayerBag(generalItem);
            else
                mazePlayer.getPlayerStatus().addGolds(generalItem.getPrice());
            return "Roof items added to your bag.";
        }
        else
            return "no items on roof.";
    }
}
