package Game.Driver.Map.BehaviorsForWalls.Lockable;

import Game.Driver.PlayerItems.Key;

public interface Lockable {

  Key getKey();

  String unlock(Key key);

  void setLockKey(Key key);

  String makeLocked(Key key);

  boolean isLocked();
  public String lockType();

}
