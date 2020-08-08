package Game.Driver.WallObjects;

import Game.Driver.PlayerItems.GeneralItem;
import Game.Driver.Player.PlayerStatus;
import Game.Driver.Map.BehaviorsForWalls.Lockable.NoLock;
import Game.Driver.Map.BehaviorsForWalls.Passable.CannotPassed;

import java.util.ArrayList;

public class Seller extends GeneralObject {

	public Seller() {
		passableBehavior=new CannotPassed();
		lockable=new NoLock();
		this.itemsList=new ArrayList<>();
	}

	public String sellItem(GeneralItem itemToSell, PlayerStatus playerStatus) {
		if (isSellerHasThisItem(itemToSell)) {
			if (canPlayerSellItem(itemToSell,playerStatus)) {
				int itemPrice=itemPrice(itemToSell);
				playerStatus.removeItemFromPlayerBag(itemToSell);
				playerStatus.addGolds(itemPrice);
				return "Successful trade process, you sell "+itemToSell.getName();
			} else {
				return "you have't this item to sell.";
			}
		} else return "Seller can't buy this item.";
	}

	public ArrayList<GeneralItem> sellerItems() {
		return this.itemsList;
	}

	public boolean isSellerHasThisItem(GeneralItem item) {
		for(int i=0;i<sellerItems().size();i++)
		{
			if(itemsList.get(i).equals(item))
				return true;
		}
		return false;
	}
	public String buyItem(GeneralItem item, PlayerStatus playerStatus)
	{
		int playerGolds=playerStatus.getPlayerGolds();
		int itemPrice=itemPrice(item);
		if(playerGolds>=itemPrice){
			playerStatus.addItemToPlayerBag(item);
			playerStatus.decreaseGolds(item.getPrice());
			return "Successful trade process, you bought "+item.getName();
		}else
			return "you haven't enough money.";

	}

	public int itemPrice(GeneralItem item) {
		if (isSellerHasThisItem(item)) {
			for (int i=0;i<itemsList.size();i++)
				if(itemsList.get(i).equals(item))
					return itemsList.get(i).getPrice();
		}
		return 0;
	}

	public boolean canPlayerSellItem(GeneralItem item, PlayerStatus playerStatus)
	{
		for(GeneralItem playerItem:playerStatus.getPlayerBag())
			if(playerItem.equals(item))
				return true;
			return false;
	}

	public String handleTradeCommand(PlayerStatus playerStatus,String playerOption)
	{

		String[] command=playerOption.split("\\s");
		String tradeType=command[0];
		String item="";
		if(command.length>1)
			item=command[1];

		if (tradeType.equals("buy")) {
			GeneralItem generalItem =mapSellerItem(item);
			if(generalItem !=null)
				return buyItem(generalItem,playerStatus);
			else return "you can't Buy this item";
		} else if (tradeType.equals("sell")) {
			GeneralItem generalItem =mapPlayerItem(playerStatus,item);
			if(generalItem !=null)
				return sellItem(generalItem, playerStatus);
			else return "you can't sell this item";
		}return "Choose valid trade command";

	}

	@Override
	public String toString() {
		return "Seller{" +
				"sellerItems="  +
				'}';
	}

	public GeneralItem mapPlayerItem(PlayerStatus playerStatus, String item)
	{
		for (GeneralItem generalItem : playerStatus.getPlayerBag())
			if(generalItem.getName().equals(item))
				return generalItem;
				return null;
	}

	public GeneralItem mapSellerItem(String item)
	{
		for (GeneralItem generalItem : itemsList)
			if(generalItem.getName().equals(item))
				return generalItem;
		return null;
	}

	@Override
	public String getObjectName() {
		return "Seller";
	}

	@Override
	public String checkObject(PlayerStatus playerStatus) {
		return "Seller person, to start trade mode enter trade.";
	}
}
