package ConfigurationGame.PlayerItems;

import Constants.ItemType;

public class Key extends GeneralItem {

	@Override
	public void setItemType(ItemType itemType) {
		this.itemType=ItemType.KEY;
	}

}
