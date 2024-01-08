package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GildedRoseSteps {
    private GildedRose app;
    private int originalSellIn;
    private int originalQuality;

    private void initItem(String name, int sellIn, int quality) {
        Item[] items = new Item[] { new Item(name, sellIn, quality) };
        app = new GildedRose(items);
        originalSellIn = sellIn;
        originalQuality = quality;
    }

    @Given("^an? (.+) with quality (\\d+) to be sold by (\\d+) days$")
    public void itemBeforeSellBy(String name, int quality, int sellIn) {
        initItem(name, sellIn, quality);
    }

    @Given("^an? (.+) with quality (\\d+) overdue by (\\d+) days$")
    public void itemOverdue(String name, int quality, int overdue) {
        initItem(name, -overdue, quality);
    }

    @When("^a day passes$")
    public void dayPasses() {
        app.updateQuality();
    }

    @Then("^the quality should be decreased by (\\d+)$")
    public void qualityShouldBeDecreasedBy(int decrease) {
        assertEquals(originalQuality - decrease, app.items[0].quality);
    }

    @Then("^the quality should be increased by (\\d+)$")
    public void qualityShouldBeIncreasedBy(int increase) {
        assertEquals(originalQuality + increase, app.items[0].quality);
    }

    @Then("^the quality should be (\\d+)$")
    public void qualityShouldBe(int quality) {
        assertEquals(quality, app.items[0].quality);
    }

    @Then("^the sell-by should be decreased by (\\d+)$")
    public void bellByShouldBeDecreasedBy(int decrease) {
        assertEquals(originalSellIn - decrease, app.items[0].sellIn);
    }
}
