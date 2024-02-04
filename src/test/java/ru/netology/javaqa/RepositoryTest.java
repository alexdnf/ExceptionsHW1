package ru.netology.javaqa;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RepositoryTest {
    Product product1 = new Product(1, "Хлеб", 50);
    Product product2 = new Product(2, "Молоко", 60);
    Product product3 = new Product(3, "Масло", 80);
    Product product4 = new Product(1, "Шоколад", 100);

    @Test
    public void shouldDeleteExistsElement() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.remove(product2.getId());

        Product[] expected = {product1, product3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);


    }

    @Test
    public void shouldNotDeleteDoesntExistElement() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(4);
        });
    }

    @Test
    public void shouldAddElement() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Product[] expected = {product1, product2, product3};
        Product[] actual = repo.findAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddExistsIdElement() {
        ShopRepository repo = new ShopRepository();

        repo.add(product1);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(product4);
        });
    }
}

