package com.gildedrose;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    @DisplayName("Items update independently of other items")
    void test() {
        Item dexterityVest = new Item("+5 Dexterity Vest", 10, 20);
        Item agedBrie = new Item("Aged Brie", 2, 0);
        Item elixir = new Item("Elixir of the Mongoose", 5, 7);
        Item sulfuras1 = new Item("Sulfuras, Hand of Ragnaros", 0, 80);
        Item sulfuras2 = new Item("Sulfuras, Hand of Ragnaros", -1, 80);
        Item backstagePass1 = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        Item backstagePass2 = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        Item backstagePass3 = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        Item conjuredManaCake = new Item("Conjured Mana Cake", 3, 6);

        GildedRose app = new GildedRose(new Item[] {
            dexterityVest, agedBrie, elixir, sulfuras1, sulfuras2, backstagePass1, backstagePass2, backstagePass3, conjuredManaCake
        });

        app.updateQuality();

        assertEquals(9, dexterityVest.sellIn);
        assertEquals(19, dexterityVest.quality);

        assertEquals(1, agedBrie.sellIn);
        assertEquals(1, agedBrie.quality);

        assertEquals(4, elixir.sellIn);
        assertEquals(6, elixir.quality);

        assertEquals(0, sulfuras1.sellIn);
        assertEquals(80, sulfuras1.quality);

        assertEquals(-1, sulfuras2.sellIn);
        assertEquals(80, sulfuras2.quality);

        assertEquals(14, backstagePass1.sellIn);
        assertEquals(21, backstagePass1.quality);

        assertEquals(9, backstagePass2.sellIn);
        assertEquals(50, backstagePass2.quality);

        assertEquals(4, backstagePass3.sellIn);
        assertEquals(50, backstagePass3.quality);

        assertEquals(2, conjuredManaCake.sellIn);
        // TODO: Fails
        // assertEquals(4, conjuredManaCake.quality);
    }
}
