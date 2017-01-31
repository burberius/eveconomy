package net.troja.eve.eveconomy.evecentral;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EveCentralService {
    private static final Logger LOGGER = LogManager.getLogger(EveCentralService.class);

    private static final String ADDRESS = "http://api.eve-central.com/api/marketstat/json?regionlimit=10000002";

    private final RestTemplate restTemplate = new RestTemplate();

    public List<EveCentralData> getPrices(final List<Integer> typeIds) {
        final List<EveCentralData> data = new ArrayList<>();
        if ((typeIds != null) && (typeIds.size() > 0)) {

            final StringBuilder builder = new StringBuilder();
            for (final Integer id : typeIds) {
                builder.append("&typeid=").append(id);
            }
            final ResponseEntity<EveCentralData[]> entity = restTemplate.getForEntity(ADDRESS + builder.toString(),
                    EveCentralData[].class);
            if (entity.getStatusCode() == HttpStatus.OK) {
                data.addAll(Arrays.asList(entity.getBody()));
            } else {
                LOGGER.warn("Could not get prices for " + typeIds + ": " + entity.getStatusCode());
            }
        }
        return data;
    }

    public Map<Integer, EveCentralData> getPriceMap(final List<EveCentralData> list) {
        final Map<Integer, EveCentralData> result = new HashMap<>();
        for (final EveCentralData entry : list) {
            final int type = entry.getBuy().getForQuery().getTypes()[0];
            result.put(type, entry);
        }
        return result;
    }
}
