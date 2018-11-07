package com.spbu.math.booksdatabase;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface GenreRepository extends CrudRepository<Genre, Long> {
    public List<Genre> findByLabel(String label);
}
