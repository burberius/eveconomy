package net.troja.eve.eveconomy.materials;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MaterialsService {
    private static final Logger LOGGER = LogManager.getLogger(MaterialsService.class);
    @Autowired
    private MaterialsRepository repository;

    @PostConstruct
    public void initialize() {
        if (repository.count() == 0) {
            final ObjectMapper mapper = new ObjectMapper();
            final File file = new File("src/main/resources/materials.json");
            try {
                final List<Materials> data = mapper.readValue(file, new TypeReference<List<Materials>>() {
                });
                repository.save(data);
            } catch (final IOException e) {
                LOGGER.warn("Could not load materials.json file: " + e.getMessage(), e);
            }
        }
    }

    public void setRepository(final MaterialsRepository repository) {
        this.repository = repository;
    }
}
