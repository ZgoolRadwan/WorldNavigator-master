package com.company.ObjectsForWalls;

import com.company.Player.PlayerStatus;
import com.company.StrategyPattern.Lockable.NoLock;
import com.company.StrategyPattern.Passable.CannotPassed;

import java.util.ArrayList;

public class EmptyObject extends GameObject {


	public EmptyObject() {
		this.lockable=new NoLock();
		this.passableBehavior=new CannotPassed();
		this.itemsList=new ArrayList<>();
	}

	@Override
	public String getObjectName() {
		return "Empty";
	}


	@Override
	public String checkObject(PlayerStatus playerStatus) {
		return "Empty plan, turn left or right to continue.";
	}
}
