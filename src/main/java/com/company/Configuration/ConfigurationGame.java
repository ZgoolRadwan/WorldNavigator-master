package com.company.Configuration;

import JAXB.POJOClases.Map;
import JAXB.WallFactoryPojo;
import JAXB.XmlToObject;
import com.company.Enumerations.Direction;
import com.company.Items.Flash;
import com.company.Items.GameItem;
import com.company.Items.Key;
import com.company.Maps.Walls.Wall;
import com.company.Maps.Walls.WallHasDoor;
import com.company.ObjectsForWalls.Door;
import com.company.Player.MazePlayer;
import com.company.Maps.Room;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class ConfigurationGame extends Thread{

  private boolean isGameActive;
  private int gameId;
  private static ConfigurationGame firstInstance = null;
  private int gameTimeInMinutes;
  private long endTime;
  private ArrayList<MazePlayer>players;
  private ArrayList<Room>gameRooms;
  private long startGameAtTime;


  public void clearPlayerByName(String playerName)
  {
    for(int i=0;i<players.size();i++)
      if(players.get(i).getPlayerName().equals(playerName)){
        players.remove(i);
        break;
      }
  }

  public int getGameId() {
    return gameId;
  }

  public void setGameId(int gameId) {
    this.gameId = gameId;
  }

  public ConfigurationGame() {
    players=new ArrayList<>();
    gameRooms=new ArrayList<>();
    createRooms();
    createDoors();
    //gameRooms=XmlToObject.getInstance().getRoomList();
  }

 /* public static ConfigurationGame getInstance() {//Singelton pattern
    if (firstInstance == null) firstInstance = new ConfigurationGame();
    return firstInstance;
  }*/

//////////////////////////////////*****

  public void addPlayer(String name,int gameId)
  {
    ArrayList<GameItem>playerBag=new ArrayList<>();
    Key key=new Key();
    key.setName("key1");playerBag.add(key);
    Key key1=new Key();
    key1.setName("key2");playerBag.add(key1);


    Flash flash=new Flash();
    playerBag.add(flash);
    //make player in random room - random range: 1 and 24  //
   /* Random rand = new Random();

    int randomNumRoom = ((int) (Math.random()*(24 - 1))) + 1;

    while(getRoomById(randomNumRoom).getPlayersCount()>0)
      randomNumRoom=((int) (Math.random()*(24 - 1))) + 1;
*/
    MazePlayer mazePlayer=new MazePlayer.PlayerBuilder(Direction.NORTH,name,getRoomById(1))
            .setPlayerBag(playerBag).setPlayerGolds(52).build();
    mazePlayer.setGameId(gameId);
    players.add(mazePlayer);
    setPlayerInRoom(mazePlayer,1);



  }

  public void setPlayerInRoom(MazePlayer playerInRoom,int roomID)
  {
    for(Room room:gameRooms)
      if(room.getRoomId()==roomID)
        room.addPlayerToRoom(playerInRoom);

  }

  public ArrayList<MazePlayer> getPlayers() {
    return players;
  }


  public MazePlayer getPlayerByName(String name)
  {
    for (MazePlayer player : players)
      if(player.getPlayerName().equals(name))
        return player;
      return null;
  }

  public void stopGameAfterTime(int minutes) {
    this.gameTimeInMinutes = minutes;
    StartGameTimer();
  }

  public void StartGameTimer()
  {
    long startTime = System.currentTimeMillis();
    this.endTime = startTime + minutesToSeconds(gameTimeInMinutes);
  }

  @Override
  public void run()//Thread to keep program running until Time over.
  {
    while (true) {
      try {
        sleep(10000);
        if (System.currentTimeMillis() >= getEndTime()) {
          isGameActive=false;
          players.clear();
          gameRooms.clear();
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void setGameActive(boolean gameActive) {
    isGameActive = gameActive;
  }

  public boolean isGameActive()
  {
    return isGameActive;
  }
  private long minutesToSeconds(int TimeInMinutes)
  {
    return TimeInMinutes*60*1000;
  }

  public long getEndTime() {
    return this.endTime;
  }

  public Map getMapFromXml() {

    try {
      File file = new File("C:\\Users\\User\\Documents\\PracticeMVC\\web\\resources\\ma.xml");
      JAXBContext jaxbContext = JAXBContext.newInstance(Map.class);

      Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
      Map map= (Map) jaxbUnmarshaller.unmarshal(file);

      return map;
    } catch (JAXBException e) {
      e.printStackTrace();
      throw new NullPointerException("can't read file, file not exist");
    }
  }
  public void createRooms()
  {
    for(int i=0;i<getMapFromXml().getRooms().size();i++)
    {
      Room roomObject=new Room();
      int roomID = getMapFromXml().getRooms().get(i).getId();
      String hasLight = getMapFromXml().getRooms().get(i).getContainLight();
      String isDark = getMapFromXml().getRooms().get(i).getDark();
      String isMaze = getMapFromXml().getRooms().get(i).getMaze();
      roomObject.setRoomId(roomID);
      //setRoomAccessories(roomObject,getMapFromXml().getRooms().get(i));
      for(int j=0;j<getMapFromXml().getRooms().get(i).getWalls().size();j++)
      {
        try {
          String wallTypeName =
                  getMapFromXml().getRooms().get(i).getWalls().get(j).getType();
          String wallDirection =
                  getMapFromXml().getRooms().get(i).getWalls().get(j).getDirection();
          JAXB.POJOClases.Wall wall=getMapFromXml().getRooms().get(i).getWalls().get(j);
          Wall wallTypeObject = WallFactoryPojo.createWall(wallTypeName,wall);
          if (wallTypeObject != null) setWallsByDirection(wallDirection, roomObject, wallTypeObject);
          else throw new NullPointerException("Wall missed tags in XML file.");
        } catch (NullPointerException e) {
          throw new NullPointerException("Wall <type> tag missed in XML file.");
        }
      }
      if (hasLight.equals("yes")) roomObject.addSwitchLight();
      if (isDark.equals("yes")) roomObject.setRoomDark();
      else if (isDark.equals("no")) roomObject.setRoomLit();
      if (isMaze.equals("yes")) roomObject.setRoom_MazeRoom();
      this.gameRooms.add(roomObject);
    }
  }

  public void setWallsByDirection(String wallDirection, Room room, Wall wallType) {
    if (wallDirection == null)
      throw new NullPointerException("Wall direction tag it's missed in XML file");
    switch (wallDirection) {
      case "North":
        room.setNorthWall(wallType);
        break;
      case "West":
        room.setWestWall(wallType);
        break;
      case "South":
        room.setSouthWall(wallType);
        break;
      case "East":
        room.setEastWall(wallType);
        break;
      default:
        throw new IllegalArgumentException("Wall direction in XML file not correct");
    }
  }

  public Room getRoomById(int id) {
    for (Room room : gameRooms) {
      if (room.getRoomId() == id) return room;
    }
    return null;
  }

  public WallHasDoor createDoor(JAXB.POJOClases.Wall wall) {
    String doorKey=wall.getKey();
    int nextRoomId=Integer.valueOf(wall.getNextRoom());
    Room nextRoom=null;
    for(Room room: gameRooms){//get the room next to the door.
      if(room.getRoomId()==nextRoomId)
        nextRoom=room;
    }
    if(nextRoom!=null)
    {
      WallHasDoor wallHasDoor=new WallHasDoor();
      Door door=new Door();
      door.setNextRoom(nextRoom);
      Key key=new Key();
      key.setName(doorKey);
      door.setLockKey(key);
      wallHasDoor.setObjectType(door);
      return wallHasDoor;
    }
//       return new WallHasDoor.DoorBuilder(nextRoom).setDoorKey(doorKey).build();
    else  throw new NullPointerException("Next room it's not exist in XML file.");

  }

  public long getStartGameAtTime() {
    return startGameAtTime;
  }

  public void setStartGameAtTime(int minutes) {
    this.startGameAtTime = System.currentTimeMillis()+minutesToSeconds(minutes);
  }

  public void setRoomAccessories(Room roomObject, JAXB.POJOClases.Room room)
  {
    try {
      int roomID = room.getId();
      String hasLight = room.getContainLight();
      String isDark = room.getDark();
      String isMaze = room.getMaze();
      roomObject.setRoomId(roomID);
      if (hasLight.equals("yes")) roomObject.addSwitchLight();
      if (isDark.equals("yes")) roomObject.setRoomDark();
      else if (isDark.equals("no")) roomObject.setRoomLit();
      if (isMaze.equals("yes")) roomObject.setRoom_MazeRoom();
    } catch (NullPointerException e) {
      throw new NullPointerException("Room accessories tags null, Check XML File.");
    } catch (NumberFormatException e) {
      throw new NullPointerException("Room accessories tags null, Check XML File.");
    }
  }

  public ArrayList<Room> getGameRooms() {
    return gameRooms;
  }

  public void createDoors() {
    for(int i=0;i<getMapFromXml().getRooms().size();i++)
    {
      for(int j=0;j<getMapFromXml().getRooms().get(i).getWalls().size();j++)
      {
        try {
          String wallTypeName =
                  getMapFromXml().getRooms().get(i).getWalls().get(j).getType();
          String wallDirection =
                  getMapFromXml().getRooms().get(i).getWalls().get(j).getDirection();
          JAXB.POJOClases.Wall wall=getMapFromXml().getRooms().get(i).getWalls().get(j);
          if(wallTypeName.equals("door"))
          {
            int roomID=getMapFromXml().getRooms().get(i).getId();
            WallHasDoor door = createDoor(wall);
            setWallsByDirection(wallDirection, getRoomById(roomID), door);
          }

        } catch (NullPointerException e) {
          System.out.println("Can't create Doors");
        }
      }

    }

  }



}
