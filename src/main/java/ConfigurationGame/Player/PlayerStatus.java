package ConfigurationGame.Player;
import ConfigurationGame.Map.Room;
import Constants.Directions;
import ConfigurationGame.PlayerItems.GeneralItem;

import java.util.*;

public class PlayerStatus {

  private Directions playerDirection;
  private Room currentRoom;
  private ArrayList<GeneralItem>playerBag;

  public void setPlayerGolds(int playerGolds) {
    this.playerGolds = playerGolds;
  }

  private int playerGolds;

  public void setPlayerBag(ArrayList<GeneralItem>playerItems) {
   this.playerBag=playerItems;
  }

  public ArrayList<GeneralItem> getPlayerBag() {
    return playerBag;
  }
  public int getPlayerGolds()
  {
    return playerGolds;
  }

  public Directions getPlayerDirection() {
    return playerDirection;
  }

  public void setStartingRoom(Room startingRoom) {
    if (startingRoom == null) throw new NullPointerException("Starting room can't be null.");
    else this.currentRoom = startingRoom;

  }

  public void setPlayerDirection(Directions playerDirection) {
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

  public void removeItemFromPlayerBag(GeneralItem itemToRemove) {
    for(int i=0;i<playerBag.size();i++)
    {
      if (playerBag.get(i).equals(itemToRemove)) {
        playerBag.remove(i);
        break;
      }

    }
  }


  public void addItemToPlayerBag(GeneralItem item) {
    if(item!=null)
      playerBag.add(item);

  }
  public void addListOfItems(ArrayList<GeneralItem> items) {
    if(items!=null)
    {
      for(GeneralItem item:items)
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
