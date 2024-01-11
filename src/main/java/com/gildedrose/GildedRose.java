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
            } else if (!item.name.equals("Sulfuras, Hand of Ragnaros")) {
                adjustment = isExpired(item) ? -2 : -1;
            }

            if (adjustment > 0)
                item.quality = Math.min(item.quality + adjustment, QUALITY_GROWTH_THRESHOLD);
            else
                item.quality = Math.max(item.quality + adjustment, QUALITY_DECREASE_THRESHOLD);

            if (!item.name.equals("Sulfuras, Hand of Ragnaros"))
                item.sellIn = item.sellIn - 1;

        }
    }

    private static boolean isExpired(Item item) {
        return item.sellIn <= 0;
    }
}
