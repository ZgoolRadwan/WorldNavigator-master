package com.company.Items;

import com.company.Enumerations.ItemType;

public class Key extends GameItem {

	@Override
	public void setItemType(ItemType itemType) {
		this.itemType=ItemType.KEY;
	}

}
