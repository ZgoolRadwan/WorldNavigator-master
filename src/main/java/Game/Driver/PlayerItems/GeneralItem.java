package Game.Driver.PlayerItems;

import Constants.ItemType;



public class GeneralItem {
    protected int price;
    protected String name;
    protected ItemType itemType;
    public void setPrice(int price)
    {
        this.price=price;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getName()
    {
        return this.name;
    }

    public int getPrice()
    {
        return this.price;
    }

    public void setItemType(ItemType itemType)
    {
        this.itemType=itemType;

    }
    public ItemType getItemType()
    {
        return this.itemType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GeneralItem generalItem = (GeneralItem) o;
        return price == generalItem.price &&
                itemType == generalItem.itemType;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
