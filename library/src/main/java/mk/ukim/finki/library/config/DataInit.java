package mk.ukim.finki.library.config;

import mk.ukim.finki.library.dto.BookDto;
import mk.ukim.finki.library.enumerations.Category;
import mk.ukim.finki.library.enumerations.Role;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Country;
import mk.ukim.finki.library.service.AuthorService;
import mk.ukim.finki.library.service.BookService;
import mk.ukim.finki.library.service.CountryService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


public class DataInit {
    private final BookService bookService;

    private final AuthorService authorService;
    private final CountryService countryService;


    public DataInit(BookService bookService, AuthorService authorService, CountryService countryService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.countryService = countryService;
    }


    @PostConstruct
    public void initData() {

        /*Countries*/
        this.countryService.create("England","Europe");
        this.countryService.create("France","Europe");
        this.countryService.create("Germany","Europe");
        this.countryService.create("USA","North America");


    }

}