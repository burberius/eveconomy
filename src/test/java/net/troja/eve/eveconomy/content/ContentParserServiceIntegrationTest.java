package net.troja.eve.eveconomy.content;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Files;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import net.troja.eve.eveconomy.data.EveDataService;
import net.troja.eve.eveconomy.data.repositories.TypeTranslationRespository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContentParserServiceIntegrationTest {
    private final ContentParserService classToTest = new ContentParserService();

    @Autowired
    private TypeTranslationRespository translationsRepository;
    private EveDataService dataService;
    private List<Content> reference;

    @Before
    public void setUp() {
        classToTest.setTranslationsRepository(translationsRepository);
        if (translationsRepository.count() == 0) {
            dataService = new EveDataService();
            dataService.setTranslationsRepository(translationsRepository);
            dataService.initialize();
        }
        if (reference == null) {
            generateReference();
        }
    }

    @Test
    public void parseEn() {
        final List<Content> list = parseLangFile("en");

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(8));
        assertThat(list.toString(), equalTo(reference.toString()));
    }

    @Test
    public void parseDe() {
        final List<Content> list = parseLangFile("de");

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(8));
        assertThat(list.toString(), equalTo(reference.toString()));
    }

    @Test
    public void parseFr() {
        final List<Content> list = parseLangFile("fr");

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(8));
    }

    @Test
    public void parseJa() {
        final List<Content> list = parseLangFile("ja");

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(8));
    }

    @Test
    public void parseRu() {
        final List<Content> list = parseLangFile("ru");

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(8));
    }

    private List<Content> parseLangFile(final String lang) {
        final Path path = Paths.get("src/test/resources/content_" + lang + ".txt");
        final String content = Files.contentOf(path.toFile(), "UTF-8");
        final List<Content> list = classToTest.parse(content);
        return list;
    }

    private void generateReference() {
        reference = new ArrayList<>();
        reference.add(new Content(27401, "Caldari Navy Nova Heavy Assault Missile", 16000));
        reference.add(new Content(28363, "Drone Cerebral Fragment", 47));
        reference.add(new Content(28366, "Drone Coronary Unit", 20));
        reference.add(new Content(28364, "Drone Tactical Limb", 23));
        reference.add(new Content(21815, "Elite Drone AI", 1));
        reference.add(new Content(28668, "Nanite Repair Paste", 174));
        reference.add(new Content(24533, "Scourge Fury Cruise Missile", 20743));
        reference.add(new Content(15466, "Standard Drop Booster", 1));
    }
}
