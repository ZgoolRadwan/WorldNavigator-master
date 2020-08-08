package com.company.Player;
import com.company.Enumerations.Direction;
import com.company.Enumerations.ItemType;
import com.company.Items.Flash;
import com.company.Items.GameItem;
import com.company.Maps.Room;

import java.util.*;

public class PlayerStatus {

  private Direction playerDirection;
  private Room currentRoom;
  private ArrayList<GameItem>playerBag;

  public void setPlayerGolds(int playerGolds) {
    this.playerGolds = playerGolds;
  }

  private int playerGolds;

  public void setPlayerBag(ArrayList<GameItem>playerItems) {
   this.playerBag=playerItems;
  }

  public ArrayList<GameItem> getPlayerBag() {
    return playerBag;
  }
  public int getPlayerGolds()
  {
    return playerGolds;
  }

  public Direction getPlayerDirection() {
    return playerDirection;
  }

  public void setStartingRoom(Room startingRoom) {
    if (startingRoom == null) throw new NullPointerException("Starting room can't be null.");
    else this.currentRoom = startingRoom;

  }

  public void setPlayerDirection(Direction playerDirection) {
    if (playerDirection == null) throw new NullPointerException("invalid player direction value");
    else this.playerDirection = playerDirection;
  }

  public Room getCurrentRoom() {
    return currentRoom;
  }

  public void decreaseGolds(int golds)
  {
    if(golds>0)
      playerGolds-=golds;
  }

  public void addGolds(int golds)
  {
    if(golds>0)
    playerGolds+=golds;
  }

  public void removeItemFromPlayerBag(GameItem itemToRemove) {
    for(int i=0;i<playerBag.size();i++)
    {
      if (playerBag.get(i).equals(itemToRemove)) {
        playerBag.remove(i);
        break;
      }

    }
  }


  public void addItemToPlayerBag(GameItem item) {
    if(item!=null)
      playerBag.add(item);

  }
  public void addListOfItems(ArrayList<GameItem> items) {
    if(items!=null)
    {
      for(GameItem item:items)
        playerBag.add(item);
    }
  }

  public String printPlayerStatus() {
    StringBuilder statusMessage = new StringBuilder();
    if (playerDirection != null) {
      statusMessage.append(
          "Golds:"
              + getPlayerGolds()
              + "\nPlayer direction:"
              + getPlayerDirection()
              + "\nyour items:");
      playerBag.forEach((v) -> statusMessage.append(v.getName() + "\n"));
      return statusMessage.toString();
    }
    return "Null pLayer direction";

  }

  @Override
  public String toString() {
    return printPlayerStatus();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    PlayerStatus that = (PlayerStatus) o;
    return playerGolds == that.playerGolds &&
            playerDirection == that.playerDirection &&
            currentRoom.equals(that.currentRoom) &&
            playerBag.equals(that.playerBag);
  }

  @Override
  public int hashCode() {
    return Objects.hash(playerDirection, currentRoom, playerBag, playerGolds);
  }
}
