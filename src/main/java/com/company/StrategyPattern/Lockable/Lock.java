package com.company.StrategyPattern.Lockable;

import com.company.Items.Key;

public class Lock implements Lockable {

  private boolean isLocked;
  private Key key;


  public Key getKey() {
    return key;
  }


  public String unlock(Key key) {
    if (this.key.getName().equals(key.getName())) {
      isLocked = false;
      return "unlocked.";
    } else {
      return "Not valid key";
    }
  }


  public void setLockKey(Key key) {
    if (!key.getName().isEmpty()) {
      this.key = key;
      this.isLocked = true; // if you set key, by default will be locked.
    }
  }


  public String makeLocked(Key key) {
    if (!key.getName().isEmpty()) {
      if (this.key.getName().equals(key.getName())) {
        isLocked = true;
        return "Locked.";
      } else {
        return "Not valid key";
      }
    } else return "Enter valid key";
  }


  public boolean isLocked() {
    return isLocked;
  }


  public String lockType() {
    return "hasLock";
  }

  @Override
  public String toString() {
    return "WithLock{" +
            "isLocked=" + isLocked +
            ", key='" + key + '\'' +
            '}';
  }
}
