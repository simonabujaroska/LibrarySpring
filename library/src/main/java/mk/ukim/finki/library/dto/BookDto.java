package mk.ukim.finki.library.dto;

import lombok.Data;
import mk.ukim.finki.library.enumerations.Category;
import mk.ukim.finki.library.model.Author;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
public class BookDto {
    private String name;
    @Enumerated(value = EnumType.STRING)
    private Category category;
    private Long authorId;
    private Integer availableCopies;

    public BookDto() {}

    public BookDto(String name, Category category,Long authorId,Integer availableCopies) {
        this.name = name;
        this.category = category;
        this.authorId=authorId;
        this.availableCopies=availableCopies;
    }
}
