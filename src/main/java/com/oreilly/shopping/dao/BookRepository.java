// BookRepository.java
package com.oreilly.shopping.dao;

import com.oreilly.shopping.entities.Book;
import com.oreilly.shopping.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}