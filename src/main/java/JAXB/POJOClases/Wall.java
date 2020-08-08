package JAXB.POJOClases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Wall {
    private String direction, type, key, nextRoom;
    private List<Item>Items;

    @XmlElement(name="Items")
    public List<Item> getItems() {
        return Items;
    }

    public void setItems(List<Item> items) {
        this.Items = items;
    }

    @XmlAttribute(name="direction")
    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    @XmlAttribute(name="type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @XmlAttribute(name="key")
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @XmlAttribute(name="nextRoom")
    public String getNextRoom() {
        return nextRoom;
    }

    public void setNextRoom(String nextRoom) {
        this.nextRoom = nextRoom;
    }
}
