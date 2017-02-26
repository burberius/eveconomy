package net.troja.eve.eveconomy.content;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.troja.eve.eveconomy.data.beans.TypeTranslation;
import net.troja.eve.eveconomy.data.repositories.TypeTranslationRespository;

public class ContentParserServiceTest {
    private ContentParserService classToTest;

    @Mock
    private TypeTranslationRespository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        classToTest = new ContentParserService();
        classToTest.setTranslationsRepository(repository);
    }

    @Test
    public void parseNull() {
        final List<Content> list = classToTest.parse(null);

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(0));
    }

    @Test
    public void parseEmpty() {
        final List<Content> list = classToTest.parse(" ");

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(0));
    }

    @Test
    public void parseRubbish() {
        final List<Content> list = classToTest.parse("Nothing   in      here");

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(0));
    }

    @Test
    public void parseNotNumberQuantity() {
        final List<TypeTranslation> transList = new ArrayList<>();
        transList.add(new TypeTranslation());
        when(repository.findByText("Nanite Repair Paste")).thenReturn(transList);

        final String wrong = "Nanite Repair Paste\t174a\tNanite Repair Paste\t\t\t1,74 m3";
        final List<Content> list = classToTest.parse(wrong);

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(0));
    }

    @Test
    public void parseDifferentNumberFormats() {
        final List<TypeTranslation> transList = new ArrayList<>();
        transList.add(new TypeTranslation());
        when(repository.findByText("Nanite Repair Paste")).thenReturn(transList);

        final String content = "Nanite Repair Paste\t174,00\tNanite Repair Paste\t\t\t1,74 m3";
        final List<Content> list = classToTest.parse(content);

        assertThat(list, notNullValue());
        assertThat(list.size(), equalTo(1));
        final Content result = list.get(0);
        assertThat(result.getQuantity(), equalTo(17400));
    }
}
