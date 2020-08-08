package com.company.ObjectsForWalls;

import com.company.Items.Key;
import com.company.Maps.Room;
import com.company.Player.PlayerStatus;
import com.company.StrategyPattern.Passable.Passable;
import com.company.StrategyPattern.Lockable.Lock;

import java.util.ArrayList;

public class Door extends GameObject {
	public Door() {

		this.passableBehavior = new Passable();
		this.lockable = new Lock();
		this.itemsList=new ArrayList<>();
	}

	private boolean isDoorOpen;

	public String openDoor() {
		isDoorOpen = true;
		return "The door now open.";
	}


	public String closeDoor() {
		isDoorOpen = false;
		return "The door now closed.";
	}

	public Room getNextRoom() {
		return passableBehavior.getNextRoom();
	}

	public boolean isDoorOpen() {
		return isDoorOpen;
	}

	@Override
	public String getObjectName() {
		return "Door";
	}

	@Override
	public String checkObject(PlayerStatus playerStatus) {
		if (lockable.isLocked()) {
      return "Door is locked use " + lockable.getKey().getName() + " to unlock it.";
    } else {
      if (isDoorOpen()) {
        return "Door is open.";
      } else {
		  return "Door is closed, open it.";
      }
    }
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

	public void setNextRoom(Room nextRoom)
	{
		if(nextRoom!=null)
			passableBehavior.setNextRoom(nextRoom);
		else
			throw new NullPointerException("next room is null, exception for door");
	}
	public void unLock(Key key)
	{
		lockable.unlock(key);
	}
	public boolean isDoorLocked() {
		return lockable.isLocked();
	}
	public void makeLocked(Key key)
	{
		lockable.makeLocked(key);
	}

}
