package net.troja.eve.eveconomy.evecentral;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class EveCentralServiceTest {
    private final EveCentralService classToTest = new EveCentralService();

    @Test
    public void getPrices() {
        final List<Integer> types = new ArrayList<>();
        types.add(37);
        types.add(35);
        final List<EveCentralData> prices = classToTest.getPrices(types);

        assertThat(prices, notNullValue());
        assertThat(prices.size(), equalTo(2));
        assertThat(prices.get(0).getBuy().getForQuery().getTypes()[0], equalTo(37));
    }

    @Test
    public void getPricesEmpty() {
        final List<Integer> types = new ArrayList<>();
        final List<EveCentralData> prices = classToTest.getPrices(types);
        assertThat(prices, notNullValue());
        assertThat(prices.size(), equalTo(0));
    }

    @Test
    public void getPricesNull() {
        final List<EveCentralData> prices = classToTest.getPrices(null);
        assertThat(prices, notNullValue());
        assertThat(prices.size(), equalTo(0));
    }

    @Test
    public void getPriceMap() {
        final List<EveCentralData> list = new ArrayList<>();
        final EveCentralData data = new EveCentralData();
        final EveCentralDataEntry entry = new EveCentralDataEntry();
        final EveCentralForQuery query = new EveCentralForQuery();
        query.setTypes(new int[] { 123 });
        entry.setForQuery(query);
        data.setAll(entry);
        data.setBuy(entry);
        data.setSell(entry);
        list.add(data);

        final Map<Integer, EveCentralData> priceMap = classToTest.getPriceMap(list);

        assertThat(priceMap.size(), equalTo(1));
        final EveCentralData centralData = priceMap.get(123);
        assertThat(centralData, notNullValue());
        assertThat(centralData.getAll().getForQuery(), nullValue());
    }
}
