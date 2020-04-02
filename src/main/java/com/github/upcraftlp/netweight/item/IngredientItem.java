package com.github.upcraftlp.netweight.item;

import com.github.upcraftlp.netweight.NetWeight;
import net.minecraft.item.Item;

public class IngredientItem extends Item {

    public IngredientItem(int maxCount) {
        this(new Item.Settings(), maxCount);
    }

    public IngredientItem() {
        this(new Item.Settings());
    }

    public IngredientItem(Settings settings, int maxCount) {
        this(settings.maxCount(maxCount));
    }

    public IngredientItem(Settings settings) {
        super(settings.group(NetWeight.ITEM_GROUP));
    }
}
