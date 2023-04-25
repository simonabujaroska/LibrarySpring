package mk.ukim.finki.library.service;

import mk.ukim.finki.library.dto.BookDto;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Country;

import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> listCountries();
    Optional<Country> create(String name, String continent);
    Optional<Country> createDataInit(Country country);
    Optional<Country> findById(Long id);

    Optional<Country> update(Long id,String name,String continent);

    void deleteById(Long id);


}
