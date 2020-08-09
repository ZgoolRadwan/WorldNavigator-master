package ConfigurationGame.WallObjects;

import ConfigurationGame.Player.PlayerStatus;
import ConfigurationGame.Map.BehaviorsForWalls.Lockable.NoLock;
import ConfigurationGame.Map.BehaviorsForWalls.Passable.CannotPassed;

import java.util.ArrayList;

public class EmptyObject extends GeneralObject {


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
