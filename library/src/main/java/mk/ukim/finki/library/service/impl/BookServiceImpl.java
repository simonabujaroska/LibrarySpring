package mk.ukim.finki.library.service.impl;

import mk.ukim.finki.library.dto.BookDto;
import mk.ukim.finki.library.enumerations.Category;
import mk.ukim.finki.library.exceptions.AuthorNotFoundException;
import mk.ukim.finki.library.exceptions.BookNotFoundException;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.repository.BookRepository;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;

    }

    @Override
    public List<Book> listBooks() {
        return bookRepository.findAll();
    }

    /*@Override
    public Book findById(Long id) {
        return this.bookRepository.findById(id).orElseThrow(()->new BookNotFoundException(id));
    }*/
    @Override
    public Optional<Book> findById(Long id) {
        return this.bookRepository.findById(id);
    }

    @Override
    public Optional<Book> create(BookDto bookDto) {
        Author author=authorRepository.findById(bookDto.getAuthorId()).orElseThrow(()->new AuthorNotFoundException(bookDto.getAuthorId()));
        return Optional.of(this.bookRepository.save(new Book(
                bookDto.getName(),
                bookDto.getCategory(),
                author,
                bookDto.getAvailableCopies()
        )));
    }

    @Override
    public Optional<Book> update(Long id, BookDto bookDto) {
        Book book = this.findById(id).orElseThrow(()-> new BookNotFoundException(id));
        book.setName(bookDto.getName());
        book.setCategory(bookDto.getCategory());
        Author author = this.authorRepository.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new AuthorNotFoundException(bookDto.getAuthorId()));
        book.setAuthor(author);

        book.setAvailableCopies(bookDto.getAvailableCopies());
        return Optional.of(bookRepository.save(book));
    }

    @Override
    public void deleteById(Long id) {
        this.bookRepository.deleteById(id);
    }

    @Override
    public Page<Book> findAllWithPagination(Pageable pageable) {
        return this.bookRepository.findAll(pageable);
    }

    @Override
    public Optional<Book> markAsTaken(Long id) {
        Book book = this.findById(id).orElseThrow(()-> new BookNotFoundException(id));

        if(book.getAvailableCopies()>=1) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            this.bookRepository.save(book);
        }

        return Optional.of(bookRepository.save(book));
    }
}
