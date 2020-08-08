package Game.Driver.WallObjects;

import Game.Driver.PlayerItems.GeneralItem;
import Game.Driver.PlayerItems.Key;
import Game.Driver.Map.Room;
import Game.Driver.Player.PlayerStatus;
import Game.Driver.Map.BehaviorsForWalls.Passable.PassableBehavior;
import Game.Driver.Map.BehaviorsForWalls.Lockable.Lockable;

import java.util.ArrayList;
import java.util.Objects;

public abstract class GeneralObject {

	protected Lockable lockable;
	protected PassableBehavior passableBehavior;
	protected ArrayList<GeneralItem>itemsList;
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

	public ArrayList<GeneralItem> getItemsList()
	{
		return itemsList;
	}
	public void setItemsList(ArrayList<GeneralItem>itemsList)
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
		for(GeneralItem generalItem :itemsList)
		{
			playerStatus.addItemToPlayerBag(generalItem);
			stringBuilder.append(generalItem.getName());

		}
		return stringBuilder.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GeneralObject that = (GeneralObject) o;
		return lockable.equals(that.lockable) &&
				passableBehavior.equals(that.passableBehavior) &&
				itemsList.equals(that.itemsList);
	}

	@Override
	public int hashCode() {
		return Objects.hash(lockable, passableBehavior, itemsList);
	}
}
