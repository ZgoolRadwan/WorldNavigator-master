package com.company.ObjectsForWalls;

import com.company.Items.GameItem;
import com.company.Items.Key;
import com.company.Maps.Room;
import com.company.Player.PlayerStatus;
import com.company.StrategyPattern.Passable.PassableBehavior;
import com.company.StrategyPattern.Lockable.Lockable;

import java.util.ArrayList;
import java.util.Objects;

public abstract class GameObject {

	protected Lockable lockable;
	protected PassableBehavior passableBehavior;
	protected ArrayList<GameItem>itemsList;
	public abstract String getObjectName();
	public abstract String checkObject(PlayerStatus playerStatus);
	public boolean hasLock()
	{
		if(lockable.lockType().equals("hasLock"))
			return true;
		return false;
	}
	public String useKey(Key key)
	{
		    if (lockable.isLocked())
		    	return lockable.unlock(key);
            else return lockable.makeLocked(key);
	}
	public Room getNextRoom()
	{
		return passableBehavior.getNextRoom();
	}

	public void setNextRoom(Room room)
	{
		passableBehavior.setNextRoom(room);
	}

	public ArrayList<GameItem> getItemsList()
	{
		return itemsList;
	}
	public void setItemsList(ArrayList<GameItem>itemsList)
	{
		this.itemsList=itemsList;
	}
	public void clearItems()
	{
		itemsList.clear();
	}
	public String moveItemsToPlayerBag(PlayerStatus playerStatus)
	{
		StringBuilder stringBuilder=new StringBuilder("Item founded:");
		for(GameItem gameItem :itemsList)
		{
			playerStatus.addItemToPlayerBag(gameItem);
			stringBuilder.append(gameItem.getName());

		}
		return stringBuilder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GameObject that = (GameObject) o;
		return lockable.equals(that.lockable) &&
				passableBehavior.equals(that.passableBehavior) &&
				itemsList.equals(that.itemsList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lockable, passableBehavior, itemsList);
	}
}
