package com.company.Items;

import com.company.Enumerations.ItemType;

public class Gold extends GameItem{
	@Override
	public String getName() {
		return "Golds";
	}

	@Override
	public void setItemType(ItemType itemType) {
		this.itemType=ItemType.GOLD;
	}
}
