package ConfigurationMapFromXmlFile;

import ConfigurationGame.Map.Walls.*;
import Constants.ItemType;
import ConfigurationGame.PlayerItems.GeneralItem;
import ConfigurationGame.PlayerItems.Key;
import ConfigurationGame.WallObjects.Chest;
import ConfigurationGame.WallObjects.Mirror;
import ConfigurationGame.WallObjects.Painting;
import ConfigurationGame.WallObjects.Seller;

import java.util.ArrayList;

public class WallFactory {


   public static GeneralWall createWall(String wallType, ConfigurationMapFromXmlFile.POJOClases.Wall wallFromPojoObject)
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

    private static GeneralWall createPaintingWall(ConfigurationMapFromXmlFile.POJOClases.Wall wall) {
        String paintingPassword=wall.getKey();
        ArrayList<GeneralItem>paintingItems=new ArrayList<>();
        for(int i=0;i<wall.getItems().size();i++)
        {
            GeneralItem generalItem =new GeneralItem();
            generalItem.setName(wall.getItems().get(i).getName());
            generalItem.setItemType(getItemType(wall.getItems().get(i).getName()));
            generalItem.setPrice(wall.getItems().get(i).getPrice());
            paintingItems.add(generalItem);
        }

        GeneralWall paintingWall=new PaintingWall();
        Painting paintingObject=new Painting();
        paintingObject.setHiddenItems(paintingItems);
        Key key=new Key();
        key.setName(paintingPassword);
        paintingObject.setLockKey(key);
       paintingWall.setObjectType(paintingObject);
        return paintingWall;
    }

    private static GeneralWall createMirrorWall(ConfigurationMapFromXmlFile.POJOClases.Wall wall) {
        String mirrorPassword=wall.getKey();
        ArrayList<GeneralItem>mirrorItems=new ArrayList<>();
        for(int i=0;i<wall.getItems().size();i++)
        {
            GeneralItem generalItem =new GeneralItem();
            generalItem.setName(wall.getItems().get(i).getName());
            generalItem.setItemType(getItemType(wall.getItems().get(i).getName()));
            generalItem.setPrice(wall.getItems().get(i).getPrice());
            mirrorItems.add(generalItem);
        }
        //GeneralWall mirror=new MirrorWall.MirrorBuilder().hideKey(password).setKeyBehindMirror(keyBehindMirror).build();
        GeneralWall mirrorWall=new MirrorWall();
        Mirror mirrorObject=new Mirror();
        Key key=new Key();
        key.setName(mirrorPassword);
        mirrorObject.setItemsList(mirrorItems);
        mirrorObject.setLockKey(key);
        mirrorWall.setObjectType(mirrorObject);
        return mirrorWall;
    }




    private static GeneralWall createChestWall(ConfigurationMapFromXmlFile.POJOClases.Wall wall) {

        String chestKey=wall.getKey();
        ArrayList<GeneralItem>chestItems=new ArrayList<>();
        for(int i=0;i<wall.getItems().size();i++)
        {
            GeneralItem generalItem =new GeneralItem();
            generalItem.setName(wall.getItems().get(i).getName());
            generalItem.setItemType(getItemType(wall.getItems().get(i).getName()));
            generalItem.setPrice(wall.getItems().get(i).getPrice());
            chestItems.add(generalItem);
        }

        GeneralWall chestWall = new ChestWall();
        Chest chestObject=new Chest();
        chestObject.setItemsList(chestItems);
        Key key=new Key();
        key.setName(chestKey);
        chestObject.setLockKey(key);
        chestWall.setObjectType(chestObject);
        return chestWall;
    }

    private static GeneralWall createSellerWall(ConfigurationMapFromXmlFile.POJOClases.Wall wall) {
        ArrayList<GeneralItem>sellerItems=new ArrayList<>();
        for(int i=0;i<wall.getItems().size();i++)
        {
            GeneralItem generalItem =new GeneralItem();
            generalItem.setName(wall.getItems().get(i).getName());
            generalItem.setItemType(getItemType(wall.getItems().get(i).getName()));
            generalItem.setPrice(wall.getItems().get(i).getPrice());
            sellerItems.add(generalItem);
        }

        GeneralWall sellerWall = new WallHasSeller();
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
