package com.company.Items;

import com.company.Enumerations.ItemType;



public class GameItem {
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
        GameItem gameItem = (GameItem) o;
        return price == gameItem.price &&
                itemType == gameItem.itemType;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
