package JAXB.POJOClases;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name="Map")
public class Map {
    private List<Room>Rooms;

    @XmlElement(name = "Rooms")
    public List<Room> getRooms() {
        return Rooms;
    }

    public void setRooms(List<Room> rooms) {
        Rooms = rooms;
    }

}
