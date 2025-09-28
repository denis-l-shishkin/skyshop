package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class BasketServiceTest {

    @Mock
    private ProductBasket productBasket;

    @Mock
    private StorageService storageService;

    @InjectMocks
    private BasketService basketService;

    /*@BeforeEach
    void setUp() {
        basketService = new BasketService(productBasket, storageService);
    }*/

    @Test
    void whenProductExist_addProductToBasket_thenProductShouldAddToBasket() {
        UUID productId = UUID.randomUUID();
        SimpleProduct product = new SimpleProduct("Масло", 100);

        when(storageService.getProductById(productId)).thenReturn(Optional.of(product));

        basketService.addProductToBasket(productId);

        verify(productBasket).addProduct(productId);
        verify(storageService).getProductById(productId);
    }

    @Test
    void whenProductNotExist_addProductToBasket_thenShouldThrowException() {
        UUID notExistProductId = UUID.randomUUID();

        when(storageService.getProductById(notExistProductId)).thenReturn(Optional.empty());

        assertThrows(NoSuchProductException.class,
                () -> basketService.addProductToBasket(notExistProductId));

        verify(productBasket, never()).addProduct(any());
    }

    @Test
    void whenBasketIsEmpty_getUserBasket_thenShouldReturnEmptyBasket() {
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket result = basketService.getUserBasket();

        assertTrue(result.getItems().isEmpty());
    }

    @Test
    void whenBasketWithProducts_getUserBasket_thenShouldReturnThisBasket() {
        UUID productId1 = UUID.randomUUID();
        UUID productId2 = UUID.randomUUID();

        SimpleProduct product1 = new SimpleProduct("Молоко", 100);
        SimpleProduct product2 = new SimpleProduct("Булка", 50);

        Map<UUID, Integer> basketProducts = new HashMap<>();
        basketProducts.put(productId1, 2);
        basketProducts.put(productId2, 3);

        when(productBasket.getProducts()).thenReturn(basketProducts);
        when(storageService.getProductById(productId1)).thenReturn(Optional.of(product1));
        when(storageService.getProductById(productId2)).thenReturn(Optional.of(product2));

        UserBasket result = basketService.getUserBasket();

        assertEquals(2, result.getItems().size());

        List<BasketItem> items = result.getItems();

        Optional<BasketItem> item1 = items.stream()
                .filter(item -> item.getProduct().getName().equals("Молоко"))
                .findFirst();
        assertTrue(item1.isPresent());
        assertEquals(2, item1.get().getQuantity());
        assertEquals(100, item1.get().getProduct().getPrice());

        Optional<BasketItem> item2 = items.stream()
                .filter(item -> item.getProduct().getName().equals("Булка"))
                .findFirst();
        assertTrue(item2.isPresent());
        assertEquals(3, item2.get().getQuantity());
        assertEquals(50, item2.get().getProduct().getPrice());
    }
}
