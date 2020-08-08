package JAXB.POJOClases;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class Room {
    private int id;
    private String maze, containLight, dark;
    private List<Wall>Walls;

    @XmlElement(name = "Walls")
    public List<Wall> getWalls() {
        return Walls;
    }

    public void setWalls(List<Wall> walls) {
        Walls = walls;
    }

    @XmlAttribute(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlAttribute(name = "maze")
    public String getMaze() {
        return maze;
    }

    public void setMaze(String maze) {
        this.maze = maze;
    }

    @XmlAttribute(name = "containLight")
    public String getContainLight() {
        return containLight;
    }

    public void setContainLight(String containLight) {
        this.containLight = containLight;
    }

    @XmlAttribute(name = "dark")
    public String getDark() {
        return dark;
    }

    public void setDark(String dark) {
        this.dark = dark;
    }
}
