package com.company.Maps;

import com.company.Enumerations.Fight;
import com.company.Items.GameItem;
import com.company.Items.Gold;
import com.company.Maps.Walls.Wall;
import com.company.Player.MazePlayer;
import com.company.Player.PlayerStatus;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Objects;

public class Room {
  private Wall NorthWall;
  private Wall southWall;
  private Wall eastWall;
  private Wall westWall;
  private int roomId;
  private boolean hasSwitchLight = false;
  private boolean isMazeRoom;
  private boolean isRoomLit = false;
  private ArrayList<MazePlayer>playersInRoom;
  private ArrayList<GameItem>roofItems;
  private boolean isFightModeActive;

  public Room() {
    playersInRoom=new ArrayList<>();
    roofItems=new ArrayList<>();
  }

  public ArrayList<GameItem>getRoofItems()
  {
    return roofItems;
  }
  /**Remove player and add his items to roof*/
  public void addItemsToRoof(ArrayList<GameItem>items)
  {
      this.roofItems.addAll(items);
  }

  public void putPlayerItemsOnTheRoof(PlayerStatus playerStatus)
  {
    Gold playerGolds=new Gold();
    playerGolds.setPrice(playerStatus.getPlayerGolds());
    playerStatus.getPlayerBag().add(playerGolds);
    addItemsToRoof(playerStatus.getPlayerBag());
    playerStatus.setPlayerGolds(0);
    playerStatus.getPlayerBag().clear();

  }

  public boolean isFightModeActive() {
    return isFightModeActive;
  }

  public void setFightModeActive(boolean fightModeActive) {
    isFightModeActive = fightModeActive;
  }

  public void removePlayer(MazePlayer playerToRemove)
  {
    for(int i=0;i<playersInRoom.size();i++)
    {
      if(playersInRoom.get(i).getPlayerName().equals(playerToRemove.getPlayerName()))
      {
        playersInRoom.remove(i);
        break;
      }
    }
  }

  public void madePlayerLoser(MazePlayer playerToMadeLoser)
  {
    for(int i=0;i<playersInRoom.size();i++)
    {
      if(playersInRoom.get(i).getPlayerName().equals(playerToMadeLoser.getPlayerName())) {
        putPlayerItemsOnTheRoof(playersInRoom.get(i).getPlayerStatus());
        playersInRoom.get(i).setAllowedToPlayGame(false);
        break;
      }
    }

  }
  public String fight()
  {
      if(playersInRoom.get(1).getPlayerPower()==playersInRoom.get(0).getPlayerPower())
      {
        isFightModeActive=true;
        return "fight";
      }else if(playersInRoom.get(1).getPlayerPower()>playersInRoom.get(0).getPlayerPower())
      {
        madePlayerLoser(playersInRoom.get(0));
        String playerLostName=playersInRoom.get(0).getPlayerName();
        String playerWinName=playersInRoom.get(1).getPlayerName();
        removePlayer(playersInRoom.get(0));
        return playerLostName+" lost. Player "+playerWinName+" stay in room.";
      }else
      {
        madePlayerLoser(playersInRoom.get(1));
        String playerLostName=playersInRoom.get(1).getPlayerName();
        String playerWinName=playersInRoom.get(0).getPlayerName();
        removePlayer(playersInRoom.get(1));
        return playerLostName+" lost. Player "+playerWinName+" stay in room.";
      }

  }

  public String finishTheFight()
  {
    if(playersInRoom.get(0).getFightModeChoice()!=null && playersInRoom.get(1).getFightModeChoice()!=null){
    if(playersInRoom.get(0).getFightModeChoice()== Fight.PAPER && playersInRoom.get(1).getFightModeChoice()==Fight.ROCK)
    {
      madePlayerLoser(playersInRoom.get(1));
      String playerLostName=playersInRoom.get(1).getPlayerName();
      String playerWinName=playersInRoom.get(0).getPlayerName();
      removePlayer(playersInRoom.get(1));
      isFightModeActive=false;
      return "finish fight mode,"+ playerLostName+" lost used rock.  Player "+playerWinName+" used paper stay in room.";

    }else if(playersInRoom.get(0).getFightModeChoice()== Fight.SCISSOR && playersInRoom.get(1).getFightModeChoice()==Fight.PAPER)
    {
      madePlayerLoser(playersInRoom.get(1));
      String playerLostName=playersInRoom.get(1).getPlayerName();
      String playerWinName=playersInRoom.get(0).getPlayerName();
      removePlayer(playersInRoom.get(1));
      isFightModeActive=false;
      return "finish fight mode,"+ playerLostName+" lost used Paper.  Player "+playerWinName+" used Scissor stay in room.";

    }
    else if(playersInRoom.get(0).getFightModeChoice()== Fight.ROCK && playersInRoom.get(1).getFightModeChoice()==Fight.SCISSOR)
    {
      madePlayerLoser(playersInRoom.get(1));
      String playerLostName=playersInRoom.get(1).getPlayerName();
      String playerWinName=playersInRoom.get(0).getPlayerName();
      removePlayer(playersInRoom.get(1));
      isFightModeActive=false;
      return "finish fight mode,"+ playerLostName+" lost used Scissor.  Player "+playerWinName+" used rock stay in room.";

    }
    else if(playersInRoom.get(1).getFightModeChoice()== Fight.PAPER && playersInRoom.get(0).getFightModeChoice()==Fight.ROCK)
    {
      madePlayerLoser(playersInRoom.get(0));
      String playerLostName=playersInRoom.get(0).getPlayerName();
      String playerWinName=playersInRoom.get(1).getPlayerName();
      removePlayer(playersInRoom.get(0));
      isFightModeActive=false;
      return "finish fight mode,"+ playerLostName+" lost used rock.  Player "+playerWinName+" used paper stay in room.";

    }else if(playersInRoom.get(1).getFightModeChoice()== Fight.SCISSOR && playersInRoom.get(0).getFightModeChoice()==Fight.PAPER)
  {
    madePlayerLoser(playersInRoom.get(0));
    String playerLostName=playersInRoom.get(0).getPlayerName();
    String playerWinName=playersInRoom.get(1).getPlayerName();
    removePlayer(playersInRoom.get(0));
    isFightModeActive=false;
    return "finish fight mode,"+ playerLostName+" lost used Paper.  Player "+playerWinName+" used Scissor stay in room.";

  }
   else if(playersInRoom.get(1).getFightModeChoice()== Fight.ROCK && playersInRoom.get(0).getFightModeChoice()==Fight.SCISSOR)
    {
      madePlayerLoser(playersInRoom.get(0));
      String playerLostName=playersInRoom.get(0).getPlayerName();
      String playerWinName=playersInRoom.get(1).getPlayerName();
      removePlayer(playersInRoom.get(0));
      isFightModeActive=false;
      return "finish fight mode,"+ playerLostName+" lost used Scissor.  Player "+playerWinName+" used rock stay in room.";

    }else return "equals";

    }else
      return "wait to other player play";

  }
  public void addPlayerToRoom(MazePlayer player)
  {
    playersInRoom.add(player);
  }
  public ArrayList<MazePlayer> getPlayersInRoom()
  {
    return playersInRoom;
  }

  public int getPlayersCount()
  {
    return playersInRoom.size();
  }

  public boolean isRoomLit() {
    return isRoomLit;
  }

  public String switchLight() {
    if (hasSwitchLight) {
      if (isRoomLit) {
        isRoomLit = false;
        return "Room is dark now.";
      } else {
        isRoomLit = true;
        return "Room is lit now.";
      }
    } else return "no light in this room, use flash light";
  }

  public void useFlashlight() {
    if (isRoomLit()) {
      isRoomLit = false;
      System.out.println("Room now Dark.");
    } else {
      isRoomLit = true;
      System.out.println("Room now Lit.");
    }
  }

  public void setRoomId(int id) {
    if (id > 0) this.roomId = id;
    else throw new NullPointerException("Invalid room ID");
  }

  public int getRoomId() {
    return this.roomId;
  }

  public Wall getNorthWall() {
    return this.NorthWall;
  }

  public void setNorthWall(Wall northWall) {
    if (northWall != null) this.NorthWall = northWall;
    else throw new NullPointerException("North wall it's Null.");
  }

  public Wall getSouthWall() {
    return southWall;
  }

  public void setSouthWall(Wall southWall) {
    if (southWall != null) this.southWall = southWall;
    else throw new NullPointerException("South wall it's Null.");
  }

  public Wall getEastWall() {
    return eastWall;
  }

  public void setEastWall(Wall eastWall) {
    if (eastWall != null) this.eastWall = eastWall;
    else throw new NullPointerException("East wall it's Null.");
  }

  public Wall getWestWall() {
    return westWall;
  }

  public void setWestWall(Wall westWall) {
    if (westWall != null) this.westWall = westWall;
    else throw new NullPointerException("West wall it's Null.");
  }

  public boolean hasSwitchLight() {
    return hasSwitchLight;
  }

  public void setRoomDark() {
    isRoomLit = false;
  }

  public void setRoomLit() {
    if (hasSwitchLight) isRoomLit = true;
  }

  public void addSwitchLight() {
    this.hasSwitchLight = true;
  }

  public void setRoom_MazeRoom() {
    this.isMazeRoom = true;
  }

  public boolean isMazeRoom() {
    return this.isMazeRoom;
  }

  @Override
  public String toString() {
    return "Room{" +
            "NorthWall=" + NorthWall.toString() +
            ", southWall=" + southWall.toString() +
            ", eastWall=" + eastWall.toString() +
            ", westWall=" + westWall.toString() +
            ", roomId=" + roomId +
            ", hasSwitchLight=" + hasSwitchLight +
            ", isMazeRoom=" + isMazeRoom +
            ", isRoomLit=" + isRoomLit +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Room room = (Room) o;
    return roomId == room.roomId &&
            hasSwitchLight == room.hasSwitchLight &&
            isMazeRoom == room.isMazeRoom &&
            isRoomLit == room.isRoomLit &&
            NorthWall.equals(room.NorthWall) &&
            southWall.equals(room.southWall) &&
            eastWall.equals(room.eastWall) &&
            westWall.equals(room.westWall) &&
            playersInRoom.equals(room.playersInRoom);
  }

  @Override
  public int hashCode() {
    return Objects.hash(NorthWall, southWall, eastWall, westWall, roomId, hasSwitchLight, isMazeRoom, isRoomLit, playersInRoom);
  }
}
