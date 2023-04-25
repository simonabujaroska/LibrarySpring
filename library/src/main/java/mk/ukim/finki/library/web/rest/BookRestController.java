package mk.ukim.finki.library.web.rest;

import mk.ukim.finki.library.dto.BookDto;
import mk.ukim.finki.library.enumerations.Category;
import mk.ukim.finki.library.model.Author;
import mk.ukim.finki.library.model.Book;
import mk.ukim.finki.library.repository.AuthorRepository;
import mk.ukim.finki.library.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@CrossOrigin(origins = {"http://localhost:3000"})
public class BookRestController {

    private final BookService bookService;
    private final AuthorRepository authorRepository;

    public BookRestController(BookService bookService,
                              AuthorRepository authorRepository) {
        this.bookService = bookService;
        this.authorRepository = authorRepository;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.listBooks();
    }

    @GetMapping("/categories")
    public List<Category> listCategories(){
        return Arrays.stream(Category.values()).toList();

    }
    @GetMapping("/authors")
    public List<Author> listAuthors(){
        return authorRepository.findAll();

    }

 /*   @GetMapping("/paginationContent")
    public List<Book> findAllWithPaginationContent(Pageable pageable) {

        return bookService.findAllWithPagination(pageable).getContent();
    }*/
    @GetMapping("/pagination")
    public Page<Book> findAllWithPagination(Pageable pageable) {
        //Pageable pageWithFiveElements = PageRequest.of(0,5);
        return bookService.findAllWithPagination(pageable);
    }

    /*Response Entity represents the whole HTTP response:
    status code, headers, and body*/
    //used for fine-tuning the HTTP req
    //se koristi za da ne se vrakjaat null vrednosti, raboti ako findById vrakja Optional<Book>,
    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        return bookService.findById(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(()->ResponseEntity.notFound().build());
    }
    @PostMapping("/add")
    //Request Body namesto prakjanje vrednosti vo query
    // maps the HttpRequest body to a dto,Spring would convert the incoming JSON to a bookDto from the post body
    public ResponseEntity<Book> save(@RequestBody BookDto bookDto){
        return this.bookService.create(bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
    //Put or Patch
    @PutMapping("/edit/{id}")
    public ResponseEntity<Book> edit(@PathVariable Long id, @RequestBody BookDto bookDto) {
        return this.bookService.update(id, bookDto)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        this.bookService.deleteById(id);
        if(this.bookService.findById(id).isEmpty())
            return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/mark/{id}")
    public ResponseEntity<Book> markAsTaken(@PathVariable Long id){
        return this.bookService.markAsTaken(id)
                .map(book -> ResponseEntity.ok().body(book))
                .orElseGet(() -> ResponseEntity.badRequest().build());

    }

}
//{
//        "id": 1,
//        "name": "Book1",
//        "category": "DRAMA",
//        "author": {
//        "id": 1,
//        "name": "Name 1",
//        "surname": "Surname 1",
//        "country": {
//        "id": 1,
//        "name": "England",
//        "continent": "Europe"
//        }
//        },
//        "availableCopies": 10
//        }