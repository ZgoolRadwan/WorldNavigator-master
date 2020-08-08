package JAXB;

import com.company.Enumerations.ItemType;
import com.company.Items.Flash;
import com.company.Items.GameItem;
import com.company.Items.Key;
import com.company.Maps.Walls.*;
import com.company.ObjectsForWalls.Chest;
import com.company.ObjectsForWalls.Mirror;
import com.company.ObjectsForWalls.Painting;
import com.company.ObjectsForWalls.Seller;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.HashMap;

public class WallFactoryPojo {


   public static Wall createWall(String wallType, JAXB.POJOClases.Wall wallFromPojoObject)
   {
       switch (wallType) {
           case "chest":
               return createChestWall(wallFromPojoObject);
           case "seller":
               return createSellerWall(wallFromPojoObject);
           case "empty":
               return new EmptyWall();
           case "painting":
               return createPaintingWall(wallFromPojoObject);
           case "mirror":
               return createMirrorWall(wallFromPojoObject);
           default:
               return new EmptyWall();// door will be initialized after rooms
       }

   }

    private static Wall createPaintingWall(JAXB.POJOClases.Wall wall) {
        String paintingPassword=wall.getKey();
        ArrayList<GameItem>paintingItems=new ArrayList<>();
        for(int i=0;i<wall.getItems().size();i++)
        {
            GameItem gameItem=new GameItem();
            gameItem.setName(wall.getItems().get(i).getName());
            gameItem.setItemType(getItemType(wall.getItems().get(i).getName()));
            gameItem.setPrice(wall.getItems().get(i).getPrice());
            paintingItems.add(gameItem);
        }

        Wall paintingWall=new PaintingWall();
        Painting paintingObject=new Painting();
        paintingObject.setHiddenItems(paintingItems);
        Key key=new Key();
        key.setName(paintingPassword);
        paintingObject.setLockKey(key);
       paintingWall.setObjectType(paintingObject);
        return paintingWall;
    }

    private static Wall createMirrorWall(JAXB.POJOClases.Wall wall) {
        String mirrorPassword=wall.getKey();
        ArrayList<GameItem>mirrorItems=new ArrayList<>();
        for(int i=0;i<wall.getItems().size();i++)
        {
            GameItem gameItem=new GameItem();
            gameItem.setName(wall.getItems().get(i).getName());
            gameItem.setItemType(getItemType(wall.getItems().get(i).getName()));
            gameItem.setPrice(wall.getItems().get(i).getPrice());
            mirrorItems.add(gameItem);
        }
        //Wall mirror=new MirrorWall.MirrorBuilder().hideKey(password).setKeyBehindMirror(keyBehindMirror).build();
        Wall mirrorWall=new MirrorWall();
        Mirror mirrorObject=new Mirror();
        Key key=new Key();
        key.setName(mirrorPassword);
        mirrorObject.setItemsList(mirrorItems);
        mirrorObject.setLockKey(key);
        mirrorWall.setObjectType(mirrorObject);
        return mirrorWall;
    }




    private static Wall createChestWall(JAXB.POJOClases.Wall wall) {

        String chestKey=wall.getKey();
        ArrayList<GameItem>chestItems=new ArrayList<>();
        for(int i=0;i<wall.getItems().size();i++)
        {
            GameItem gameItem=new GameItem();
            gameItem.setName(wall.getItems().get(i).getName());
            gameItem.setItemType(getItemType(wall.getItems().get(i).getName()));
            gameItem.setPrice(wall.getItems().get(i).getPrice());
            chestItems.add(gameItem);
        }

        Wall chestWall = new ChestWall();
        Chest chestObject=new Chest();
        chestObject.setItemsList(chestItems);
        Key key=new Key();
        key.setName(chestKey);
        chestObject.setLockKey(key);
        chestWall.setObjectType(chestObject);
        return chestWall;
    }

    private static Wall createSellerWall(JAXB.POJOClases.Wall wall) {
        ArrayList<GameItem>sellerItems=new ArrayList<>();
        for(int i=0;i<wall.getItems().size();i++)
        {
            GameItem gameItem=new GameItem();
            gameItem.setName(wall.getItems().get(i).getName());
            gameItem.setItemType(getItemType(wall.getItems().get(i).getName()));
            gameItem.setPrice(wall.getItems().get(i).getPrice());
            sellerItems.add(gameItem);
        }

        Wall sellerWall = new WallHasSeller();
        Seller sellerMan=new Seller();
        sellerMan.setItemsList(sellerItems);
        sellerWall.setObjectType(sellerMan);
        return sellerWall;
    }

    private static ItemType getItemType(String type)
    {
        switch (type)
        {
            case "Gold":
                return ItemType.GOLD;
            case "flashlight":
                return ItemType.FLASHLIGHT;
            default:
                return ItemType.KEY;
        }
    }
}
