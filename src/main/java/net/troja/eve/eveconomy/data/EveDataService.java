package net.troja.eve.eveconomy.data;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.troja.eve.eveconomy.data.beans.Materials;
import net.troja.eve.eveconomy.data.beans.Type;
import net.troja.eve.eveconomy.data.beans.TypeTranslation;
import net.troja.eve.eveconomy.data.repositories.MaterialsRepository;
import net.troja.eve.eveconomy.data.repositories.TypeRepository;
import net.troja.eve.eveconomy.data.repositories.TypeTranslationRespository;

@Service
public class EveDataService {
    private static final Logger LOGGER = LogManager.getLogger(EveDataService.class);
    @Autowired
    private MaterialsRepository materialsRepository;
    @Autowired
    private TypeRepository typesRepository;
    @Autowired
    private TypeTranslationRespository translationsRepository;

    private final String[] file = new String[] { "materials.json", "types.json", "typetranslations.json" };
    private String resourcePath = "";
    private final ObjectMapper mapper = new ObjectMapper();

    @PostConstruct
    public void initialize() {
        readDataIfNeeded(materialsRepository, file[0], Materials.class);
        readDataIfNeeded(typesRepository, file[1], Type.class);
        readDataIfNeeded(translationsRepository, file[2], TypeTranslation.class);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private <T> void readDataIfNeeded(final CrudRepository repo, final String fileName, final Class<T> clazz) {
        if (repo != null && repo.count() == 0) {
            List<T> data = null;
            InputStream in = null;
            try {
                final Path path = Paths.get("src/main/resources/" + fileName);
                if (Files.exists(path)) {
                    in = Files.newInputStream(path);
                } else {
                    in = this.getClass().getClassLoader().getResourceAsStream(resourcePath + fileName);
                }
                final JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
                data = mapper.readValue(in, type);
                repo.save(data);
                LOGGER.info("Loaded " + data.size() + " entries from " + fileName);
            } catch (final IOException e) {
                LOGGER.warn("Could not load json file: " + e.getMessage(), e);
            } finally {
                try {
                    in.close();
                } catch (final IOException e) {
                    LOGGER.warn("Could not close inputstream", e);
                }
            }
        }
    }

    public void setMaterialsRepository(final MaterialsRepository materialsRepository) {
        this.materialsRepository = materialsRepository;
    }

    public void setTypesRepository(final TypeRepository typesRepository) {
        this.typesRepository = typesRepository;
    }

    public void setTranslationsRepository(final TypeTranslationRespository translationsRepository) {
        this.translationsRepository = translationsRepository;
    }

    public void setResourcePath(final String resourcePath) {
        this.resourcePath = resourcePath;
    }
}
