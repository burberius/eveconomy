package net.troja.eve.eveconomy.data;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import net.troja.eve.eveconomy.data.beans.Materials;
import net.troja.eve.eveconomy.data.beans.Type;
import net.troja.eve.eveconomy.data.beans.TypeTranslation;
import net.troja.eve.eveconomy.data.repositories.MaterialsRepository;
import net.troja.eve.eveconomy.data.repositories.TypeRepository;
import net.troja.eve.eveconomy.data.repositories.TypeTranslationRespository;

public class EveDataServiceTest {
    private final EveDataService classToTest = new EveDataService();

    @Mock
    private MaterialsRepository materialsRepository;
    @Mock
    private TypeRepository typesRepository;
    @Mock
    private TypeTranslationRespository translationsRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        classToTest.setMaterialsRepository(materialsRepository);
        classToTest.setTypesRepository(typesRepository);
        classToTest.setTranslationsRepository(translationsRepository);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void initialize() {
        final ArgumentCaptor<List> materialsCaptor = ArgumentCaptor.forClass(List.class);
        final ArgumentCaptor<List> typeCaptor = ArgumentCaptor.forClass(List.class);
        final ArgumentCaptor<List> translationCaptor = ArgumentCaptor.forClass(List.class);
        when(materialsRepository.count()).thenReturn(0l);
        when(typesRepository.count()).thenReturn(0l);
        when(translationsRepository.count()).thenReturn(0l);

        classToTest.initialize();

        verify(materialsRepository).save((List<Materials>) materialsCaptor.capture());
        assertThat(materialsCaptor.getValue().size(), equalTo(370));

        verify(typesRepository).save((List<Type>) typeCaptor.capture());
        assertThat(typeCaptor.getValue().size(), equalTo(15910));

        verify(translationsRepository).save((List<TypeTranslation>) translationCaptor.capture());
        assertThat(translationCaptor.getValue().size(), equalTo(79550));
    }
}
