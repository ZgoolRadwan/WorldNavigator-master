package Game.Driver.PlayerItems;

import Constants.ItemType;

public class FlashLight extends GeneralItem {

	@Override
	public String getName() {
		return "FlashLight";
	}

	@Override
	public void setItemType(ItemType itemType) {
		this.itemType=ItemType.FLASHLIGHT;
	}

}
