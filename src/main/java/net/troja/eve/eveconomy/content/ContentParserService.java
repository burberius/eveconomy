package net.troja.eve.eveconomy.content;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.troja.eve.eveconomy.data.beans.TypeTranslation;
import net.troja.eve.eveconomy.data.repositories.TypeTranslationRespository;

@Service
public class ContentParserService {
    private static final Logger LOGGER = LogManager.getLogger(ContentParserService.class);

    @Autowired
    private TypeTranslationRespository translationsRepository;

    public List<Content> parse(final String content) {
        final List<Content> list = new ArrayList<>();
        if (!StringUtils.isBlank(content)) {

            for (final String line : content.split("\n")) {
                try {
                    final String[] cols = line.split("\t");
                    if (cols.length < 2) {
                        LOGGER.warn("Wrong number of columns: " + line);
                    }
                    String name = cols[0].trim();
                    if (name.endsWith("*")) {
                        name = name.substring(0, name.length() - 1);
                    }
                    final List<TypeTranslation> result = translationsRepository.findByText(name);
                    if (result.size() == 0) {
                        continue;
                    }
                    final int quantity = Integer.parseInt(cols[1].replaceAll("[.,]", ""));
                    list.add(new Content(result.get(0).getTypeID(), name, quantity));
                } catch (final Exception e) {
                    LOGGER.warn("Could not parse pasted content: " + e.getMessage() + " of line '" + line + "'");
                }
            }

        }
        return list;
    }

    public void setTranslationsRepository(final TypeTranslationRespository translationsRepository) {
        this.translationsRepository = translationsRepository;
    }
}
