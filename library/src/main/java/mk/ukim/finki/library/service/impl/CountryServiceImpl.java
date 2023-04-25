package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.exceptions.BookNotFoundException;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.repository.CountryRepository;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> listCountries() {
        return this.countryRepository.findAll();
    }
    @Override
    public Optional<Country> findById(Long id) {
        return this.countryRepository.findById(id);
    }
    @Override
    public Optional<Country> create(String name, String continent) {
        return Optional.of(this.countryRepository.save(new Country(name,continent)));
    }

    @Override
    public Optional<Country> createDataInit(Country country) {
        return Optional.of(this.countryRepository.save(country));
    }

    @Override
    public Optional<Country> update(Long id, String name, String continent) {
        Country country = this.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        country.setName(name);
        country.setContinent(continent);
        return Optional.of(countryRepository.save(country));
    }

    @Override
    public void deleteById(Long id) {
        this.countryRepository.deleteById(id);
    }

}
