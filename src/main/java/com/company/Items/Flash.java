package com.company.Items;

import com.company.Enumerations.ItemType;

import java.util.Objects;

public class Flash extends GameItem{

	@Override
	public String getName() {
		return "FlashLight";
	}

	@Override
	public void setItemType(ItemType itemType) {
		this.itemType=ItemType.FLASHLIGHT;
	}

}
