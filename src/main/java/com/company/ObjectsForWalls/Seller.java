package com.company.ObjectsForWalls;

import com.company.Items.GameItem;
import com.company.Player.PlayerStatus;
import com.company.StrategyPattern.Lockable.NoLock;
import com.company.StrategyPattern.Passable.CannotPassed;

import java.util.ArrayList;

public class Seller extends GameObject {

	public Seller() {
		passableBehavior=new CannotPassed();
		lockable=new NoLock();
		this.itemsList=new ArrayList<>();
	}

	public String sellItem(GameItem itemToSell, PlayerStatus playerStatus) {
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

	public ArrayList<GameItem> sellerItems() {
		return this.itemsList;
	}

	public boolean isSellerHasThisItem(GameItem item) {
		for(int i=0;i<sellerItems().size();i++)
		{
			if(itemsList.get(i).equals(item))
				return true;
		}
		return false;
	}
	public String buyItem(GameItem item,PlayerStatus playerStatus)
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

	public int itemPrice(GameItem item) {
		if (isSellerHasThisItem(item)) {
			for (int i=0;i<itemsList.size();i++)
				if(itemsList.get(i).equals(item))
					return itemsList.get(i).getPrice();
		}
		return 0;
	}

	public boolean canPlayerSellItem(GameItem item, PlayerStatus playerStatus)
	{
		for(GameItem playerItem:playerStatus.getPlayerBag())
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
			GameItem gameItem=mapSellerItem(item);
			if(gameItem!=null)
				return buyItem(gameItem,playerStatus);
			else return "you can't Buy this item";
		} else if (tradeType.equals("sell")) {
			GameItem gameItem=mapPlayerItem(playerStatus,item);
			if(gameItem!=null)
				return sellItem(gameItem, playerStatus);
			else return "you can't sell this item";
		}return "Choose valid trade command";

	}

	@Override
	public String toString() {
		return "Seller{" +
				"sellerItems="  +
				'}';
	}

	public GameItem mapPlayerItem(PlayerStatus playerStatus, String item)
	{
		for (GameItem gameItem: playerStatus.getPlayerBag())
			if(gameItem.getName().equals(item))
				return gameItem;
				return null;
	}

	public GameItem mapSellerItem(String item)
	{
		for (GameItem gameItem: itemsList)
			if(gameItem.getName().equals(item))
				return gameItem;
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
