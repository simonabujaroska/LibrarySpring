package mk.ukim.finki.library.service;

import mk.ukim.finki.library.dto.BookDto;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Country;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    List<Author> findAll();
    Author findById(Long id);
    Optional<Author> create(String name, String surname, Country country);
    Optional<Author> update(Long id, String name, String surname, Country country);
    void deleteById(Long id);

}
