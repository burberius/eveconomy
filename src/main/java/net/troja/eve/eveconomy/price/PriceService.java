package net.troja.eve.eveconomy.price;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.troja.eve.eveconomy.evecentral.EveCentralData;
import net.troja.eve.eveconomy.evecentral.EveCentralService;

@Service
public class PriceService {
    private static final Logger LOGGER = LogManager.getLogger(PriceService.class);
    private static final int CACHE_TIME = 1000 * 60 * 60;
    @Autowired
    private EveCentralService eveCentral;
    @Autowired
    private PriceRepository repository;

    public List<Price> getPrices(final List<Integer> ids) {
        final List<Price> result = new ArrayList<>();
        final List<Integer> toQuery = new ArrayList<>();
        for (final int typeId : ids) {
            final Price price = repository.findOne(typeId);
            if ((price == null) || ((price.getGenerated() + CACHE_TIME) < System.currentTimeMillis())) {
                toQuery.add(typeId);
            } else {
                result.add(price);
            }
        }
        if (toQuery.size() > 0) {
            LOGGER.info("Updating " + toQuery.size() + " prices");
            final List<EveCentralData> prices = eveCentral.getPrices(toQuery);
            for (final EveCentralData priceNew : prices) {
                final Price newPrice = new Price(priceNew);
                repository.save(newPrice);
                result.add(newPrice);
            }
        }
        return result;
    }

    public void setEveCentral(final EveCentralService eveCentral) {
        this.eveCentral = eveCentral;
    }

    public void setRepository(final PriceRepository repository) {
        this.repository = repository;
    }
}
