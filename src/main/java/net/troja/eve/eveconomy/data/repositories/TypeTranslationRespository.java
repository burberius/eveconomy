package net.troja.eve.eveconomy.data.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import net.troja.eve.eveconomy.data.beans.TypeTranslation;

public interface TypeTranslationRespository extends CrudRepository<TypeTranslation, Integer> {
    List<TypeTranslation> findByText(String text);
}
