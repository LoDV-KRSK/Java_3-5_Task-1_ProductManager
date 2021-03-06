package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();
    private Product book1 = new Book(1, "Сказки", 100, "Иванов");
    private Product book2 = new Book(2, "Детектив", 150, "Петров");
    private Product phone1 = new Smartphone(11, "A110", 5000, "Elektronika");
    private Product phone2 = new Smartphone(22, "B150", 7000, "Soyuz");

    @Test
    void shouldAddAllProducts() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book1, book2, phone1, phone2};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotSaveAnything() {
        Product[] expected = new Product[]{};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldDeletedByIDOneProduct() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book2, phone1, phone2};
        repository.removeById(1);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldDeletedByIDTwoProduct() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{book2, phone1,};
        repository.removeById(1);
        repository.removeById(22);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldStayEmpty() {
        repository.save(book1);
        repository.save(book2);
        repository.save(phone1);
        repository.save(phone2);

        Product[] expected = new Product[]{};
        repository.removeById(1);
        repository.removeById(2);
        repository.removeById(11);
        repository.removeById(22);
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }
}