package com.oreilly.shopping.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testFindByAuthor() {
        // given
        Book book = new Book("Title 1", "Author 1", "1111111111", LocalDate.of(2020, 1, 1));
        bookRepository.save(book);

        // when
        Iterable<Book> found = bookRepository.findByAuthor("Author 1");

        // then
        assertThat(found).extracting(Book::getAuthor).containsOnly("Author 1");
    }

    @Test
    public void testFindByIsbn() {
        // given
        Book book = new Book("Title 2", "Author 2", "2222222222", LocalDate.of(2022, 2, 2));
        bookRepository.save(book);

        // when
        Book found = bookRepository.findByIsbn("2222222222").orElse(null);

        // then
        assertThat(found.getIsbn()).isEqualTo("2222222222");
    }

    @Test
    public void testUpdateTitle() {
        // given
        Book book = new Book("Old Title", "Author 1", "3333333333", LocalDate.now());
        bookRepository.save(book);

        // when
        Book savedBook = bookRepository.findById(book.getId()).get();
        savedBook.setTitle("New Title");
        bookRepository.save(savedBook);

        // then
        assertThat(savedBook.getTitle()).isEqualTo("New Title");
    }
}