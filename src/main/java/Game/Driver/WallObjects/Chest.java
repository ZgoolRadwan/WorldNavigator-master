package Game.Driver.WallObjects;

import Constants.ItemType;
import Game.Driver.PlayerItems.GeneralItem;
import Game.Driver.PlayerItems.Key;
import Game.Driver.Player.PlayerStatus;
import Game.Driver.Map.BehaviorsForWalls.Passable.CannotPassed;
import Game.Driver.Map.BehaviorsForWalls.Lockable.Lock;
import java.util.ArrayList;

public class Chest extends GeneralObject {


	public Chest() {
		passableBehavior = new CannotPassed();
		lockable = new Lock();
		itemsList=new ArrayList<GeneralItem>();
	}

	public void makeLocked(Key key)
	{
		lockable.makeLocked(key);
	}
	public void unLock(Key key)
	{
		lockable.unlock(key);
	}

	public boolean isChestLocked() {
		return lockable.isLocked();
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

	@Override
	public String getObjectName() {
		return "Chest";
	}

	@Override
	public String checkObject(PlayerStatus playerStatus) {

		if (!isChestLocked()) {
			StringBuilder messageToUser=new StringBuilder();
			if (itemsList.size() > 0 ) {
				messageToUser.append("Chest is open, those items added to your bags:");
				for (GeneralItem itemInChest : itemsList) {
					if(itemInChest.getItemType()== ItemType.GOLD){
						playerStatus.addGolds(itemInChest.getPrice());
						messageToUser.append(itemInChest.getName()+" "+itemInChest.getPrice() + " ");
					}
					else{
						playerStatus.addItemToPlayerBag(itemInChest);
						messageToUser.append(itemInChest.getName() + " ");
					}
				}
				clearItems();
			}

			messageToUser.append("\n");
			return messageToUser.toString();
		} else return "Chest is locked, use " + getLockKey().getName() + " key to open it.";
	}
}
