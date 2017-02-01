package net.troja.eve.eveconomy.materials;

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

public class MaterialsServiceTest {
    private final MaterialsService classToTest = new MaterialsService();

    @Mock
    private MaterialsRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        classToTest.setRepository(repository);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
    public void initialize() {
        final ArgumentCaptor<List> captor = ArgumentCaptor.forClass(List.class);
        when(repository.count()).thenReturn(0l);

        classToTest.initialize();

        verify(repository).save((List<Materials>) captor.capture());
        assertThat(captor.getValue().size(), equalTo(370));
    }
}
