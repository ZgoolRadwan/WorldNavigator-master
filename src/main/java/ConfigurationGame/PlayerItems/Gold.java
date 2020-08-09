package ConfigurationGame.PlayerItems;

import Constants.ItemType;

public class Gold extends GeneralItem {
	@Override
	public String getName() {
		return "Golds";
	}

	@Override
	public void setItemType(ItemType itemType) {
		this.itemType=ItemType.GOLD;
	}
}
