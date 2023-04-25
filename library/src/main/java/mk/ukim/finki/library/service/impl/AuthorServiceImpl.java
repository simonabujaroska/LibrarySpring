package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.dto.BookDto;
import mk.ukim.finki.library.exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.exceptions.BookNotFoundException;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(Long id) {
        return this.authorRepository.findById(id).orElseThrow(()->new AuthorNotFoundException(id));
    }
    @Override
    public Optional<Author> create(String name,String surname, Country country) {
        return Optional.of(this.authorRepository.save(new Author(name,surname,country)));
    }

    @Override
    public Optional<Author> update(Long id, String name, String surname, Country country) {

        Author author=this.authorRepository.findById(id).orElseThrow(()-> new AuthorNotFoundException(id));
        author.setName(name);
        author.setSurname(surname);
        author.setCountry(country);
        return Optional.of(authorRepository.save(author));

    }

    @Override
    public void deleteById(Long id) {
        this.authorRepository.deleteById(id);
    }
}
