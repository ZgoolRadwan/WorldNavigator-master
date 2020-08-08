package JAXB;
import JAXB.POJOClases.Map;
import com.company.Items.Key;
import com.company.Maps.Room;
import com.company.Maps.Walls.Wall;

import com.company.Maps.Walls.WallHasDoor;
import com.company.ObjectsForWalls.Door;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class XmlToObject {
    private ArrayList<Room> roomList=new ArrayList<>();

    public ArrayList<Room>getRoomList()
    {
        return roomList;
    }
    private static XmlToObject instance=null;
    public static XmlToObject getInstance()
    {
        if(instance==null){
            instance=new XmlToObject();
            return instance;
        }
        else return instance;
    }
    private XmlToObject() {
        createRooms();
        createDoors();
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
           setRoomAccessories(roomObject,getMapFromXml().getRooms().get(i));
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
           this.roomList.add(roomObject);
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
        for (Room room : roomList) {
            if (room.getRoomId() == id) return room;
        }
        return null;
    }

    public WallHasDoor createDoor(JAXB.POJOClases.Wall wall) {
        String doorKey=wall.getKey();
        int nextRoomId=Integer.valueOf(wall.getNextRoom());
        Room nextRoom=null;
        for(Room room: roomList){//get the room next to the door.
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

    //public List<>







}
