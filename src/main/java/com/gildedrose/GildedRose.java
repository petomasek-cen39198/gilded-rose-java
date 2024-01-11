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
            if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                int increment;
                if (item.sellIn < 6)
                    increment = 3;
                else if (item.sellIn < 11)
                    increment = 2;
                else
                    increment = 1;

                item.quality = Math.min(item.quality + increment, QUALITY_GROWTH_THRESHOLD);
            } else if (item.name.equals("Aged Brie")) {
                if (item.quality < QUALITY_GROWTH_THRESHOLD) {
                    item.quality++;
                }
            } else {
                if (item.quality > QUALITY_DECREASE_THRESHOLD) {
                    if (!item.name.equals("Sulfuras, Hand of Ragnaros"))
                        item.quality--;
                }
            }

            if (!item.name.equals("Sulfuras, Hand of Ragnaros"))
                item.sellIn = item.sellIn - 1;

            if (item.sellIn < 0) {
                if (!item.name.equals("Aged Brie")) {
                    if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
                        if (item.quality > QUALITY_DECREASE_THRESHOLD)
                            if (!item.name.equals("Sulfuras, Hand of Ragnaros"))
                                item.quality--;
                    } else
                        item.quality = QUALITY_DECREASE_THRESHOLD;
                } else if (item.quality < QUALITY_GROWTH_THRESHOLD)
                    item.quality++;
            }
        }
    }
}
