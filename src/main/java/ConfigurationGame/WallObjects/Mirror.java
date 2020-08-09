package ConfigurationGame.WallObjects;

import ConfigurationGame.Player.PlayerStatus;
import ConfigurationGame.PlayerItems.GeneralItem;
import ConfigurationGame.PlayerItems.Key;
import ConfigurationGame.Map.BehaviorsForWalls.Lockable.Lock;
import ConfigurationGame.Map.BehaviorsForWalls.Passable.CannotPassed;

import java.util.ArrayList;


public class Mirror extends GeneralObject {

	public Mirror() {
		this.passableBehavior = new CannotPassed();
		this.lockable = new Lock();
		this.itemsList=new ArrayList<>();
	}

	public void setLockKey(Key key)
	{
		if(key!=null)
			lockable.setLockKey(key);
		else
			throw new NullPointerException("key for door is null, exception for door.");
	}

	public Key getLockKey()
	{
		return lockable.getKey();
	}

	public ArrayList<GeneralItem> getHiddenItems() {
		return itemsList;
	}

	public boolean isKeyHidden() {
		return lockable.isLocked();
	}

	public void setHiddenItems(ArrayList<GeneralItem>hiddenItems) {
		if (hiddenItems != null)
			this.itemsList = hiddenItems;
	}

	@Override
	public String getObjectName() {
		return "Mirror";
	}



	@Override
	public String checkObject(PlayerStatus playerStatus) {
		if (isKeyHidden()) {
			if(lockable.getKey().getName() != null && !lockable.getKey().getName().isEmpty())
			{
				String password=lockable.getKey().getName();
				return "Mirror key hidden use " + password + " key to show hidden key.";
			} else return "no password for Mirror.";
		}else {
			if (itemsList != null) {
				return moveItemsToPlayerBag(playerStatus);
			} else return "No key behind mirror.";
		}
	}

	public void showHiddenKey(Key key)
	{
		lockable.unlock(key);
	}
}
