package mk.ukim.finki.library.service;

import mk.ukim.finki.library.dto.BookDto;
import mk.ukim.finki.library.enumerations.Category;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> listBooks();

    //Book findById(Long id);
    Optional<Book> findById(Long id);
    Optional<Book> create(BookDto bookDto);

    Optional<Book> update(Long id,BookDto bookDto);

    void deleteById(Long id);

    Page<Book> findAllWithPagination(Pageable pageable);

    Optional<Book> markAsTaken(Long id);
}
