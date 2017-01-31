package net.troja.eve.eveconomy.price;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.troja.eve.eveconomy.evecentral.EveCentralData;
import net.troja.eve.eveconomy.evecentral.EveCentralDataEntry;
import net.troja.eve.eveconomy.evecentral.EveCentralForQuery;
import net.troja.eve.eveconomy.evecentral.EveCentralService;

public class PriceServiceTest {
    private final PriceService classToTest = new PriceService();

    @Mock
    private EveCentralService eveCentral;
    @Mock
    private PriceRepository repository;

    private final Price upToDate = new Price();
    private final Price old = new Price();
    private final List<Integer> input = new ArrayList<>();
    final List<EveCentralData> evePrices = new ArrayList<>();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        classToTest.setEveCentral(eveCentral);
        classToTest.setRepository(repository);

        upToDate.setTypeId(37);
        upToDate.setGenerated(System.currentTimeMillis() - 15000);
        old.setGenerated(12345l);

        input.clear();
        input.add(37);

        final EveCentralData price = new EveCentralData();
        final EveCentralDataEntry entry = new EveCentralDataEntry();
        final EveCentralForQuery query = new EveCentralForQuery();
        query.setTypes(new int[] { 37 });
        entry.setForQuery(query);
        price.setAll(entry);
        price.setBuy(entry);
        price.setSell(entry);
        evePrices.add(price);
    }

    @Test
    public void getPricesUpToDate() {
        when(repository.findOne(37)).thenReturn(upToDate);

        final List<Price> prices = classToTest.getPrices(input);

        assertThat(prices, notNullValue());
        assertThat(prices.size(), equalTo(1));
        assertThat(prices.get(0).getTypeId(), equalTo(37));

        verify(eveCentral, never()).getPrices(any());
    }

    @Test
    public void getPricesOld() {
        when(repository.findOne(37)).thenReturn(old);
        when(eveCentral.getPrices(input)).thenReturn(evePrices);

        final List<Price> prices = classToTest.getPrices(input);

        assertThat(prices, notNullValue());
        assertThat(prices.size(), equalTo(1));
        assertThat(prices.get(0).getTypeId(), equalTo(37));

        verify(repository).save(any(Price.class));
    }

    @Test
    public void getPricesNotFound() {
        when(repository.findOne(37)).thenReturn(null);
        when(eveCentral.getPrices(input)).thenReturn(evePrices);

        final List<Price> prices = classToTest.getPrices(input);

        assertThat(prices, notNullValue());
        assertThat(prices.size(), equalTo(1));
        assertThat(prices.get(0).getTypeId(), equalTo(37));

        verify(repository).save(any(Price.class));
    }
}
