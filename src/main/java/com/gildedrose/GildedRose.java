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
        int adjustment = 0;
        if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
            if (isExpired(item))
                adjustment = -item.quality;
            else if (item.sellIn < 6)
                adjustment = 3;
            else if (item.sellIn < 11)
                adjustment = 2;
            else
                adjustment = 1;

        } else if (item.name.equals("Aged Brie")) {
            adjustment = isExpired(item) ? 2 : 1;
        } else {
            adjustment = isExpired(item) ? -2 : -1;
        }
        return adjustment;
    }

    private static boolean isExpired(Item item) {
        return item.sellIn <= 0;
    }
}
