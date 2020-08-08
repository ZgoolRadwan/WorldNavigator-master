package com.company.StrategyPattern.Lockable;

import com.company.Items.Key;

public class NoLock implements Lockable {


  public Key getKey() {
    return null;
  }


  public String unlock(Key key) {return "Object have not key";}

  public void setLockKey(Key key) {}


  public String makeLocked(Key key) {return "Object haven't lock";}


  public boolean isLocked() {
    return false;
  }

  public String lockType() {
    return "hasNotLock";
  }
}
