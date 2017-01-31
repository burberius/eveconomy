package net.troja.eve.eveconomy.price;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.troja.eve.eveconomy.evecentral.EveCentralService;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PriceServiceIntegrationTest {
    private static final int TYPE_ID = 37;

    private final PriceService classToTest = new PriceService();

    @Autowired
    private PriceRepository repository;

    @Before
    public void setUp() {
        classToTest.setRepository(repository);
        classToTest.setEveCentral(new EveCentralService());
    }

    @Test
    public void getPrices() {
        final Price old = new Price();
        old.setTypeId(TYPE_ID);
        old.setGenerated(2345l);
        repository.save(old);

        final List<Integer> input = new ArrayList<>();
        input.add(TYPE_ID);

        final List<Price> prices = classToTest.getPrices(input);

        assertThat(prices.size(), equalTo(1));
        final Price priceReturn = prices.get(0);
        assertThat(priceReturn.getSellVolume(), greaterThan(100l));

        final Price price = repository.findOne(TYPE_ID);
        assertThat(price.getSellVolume(), equalTo(priceReturn.getSellVolume()));
    }
}
