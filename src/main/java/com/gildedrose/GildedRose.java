package com.gildedrose;

class GildedRose {

    final static int QUALITY_GROWTH_THRESHOLD = 50;
    final static int QUALITY_DECREASE_THRESHOLD = 0;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.name.equals("Sulfuras, Hand of Ragnaros")) continue;

            int adjustment = getAdjustment(item);

            if (adjustment > 0)
                item.quality = Math.min(item.quality + adjustment, QUALITY_GROWTH_THRESHOLD);
            else
                item.quality = Math.max(item.quality + adjustment, QUALITY_DECREASE_THRESHOLD);

            item.sellIn = item.sellIn - 1;
        }
    }

    private static int getAdjustment(Item item) {
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert"))
            if (isExpired(item))
                return -item.quality;
            else if (item.sellIn < 6)
                return 3;
            else if (item.sellIn < 11)
                return 2;
            else
                return 1;

        else if (item.name.equals("Aged Brie"))
            return isExpired(item) ? 2 : 1;
        else
            return isExpired(item) ? -2 : -1;
    }

    private static boolean isExpired(Item item) {
        return item.sellIn <= 0;
    }
}
