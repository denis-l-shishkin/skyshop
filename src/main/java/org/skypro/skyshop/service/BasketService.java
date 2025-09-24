package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.product.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    @Autowired
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductToBasket(UUID productId) {
        /*if (!storageService.getProductById(productId).isPresent()) {
            throw new IllegalArgumentException("Товар не найден");
        } else {
            productBasket.addProduct(productId);
        }*/
        storageService.getProductById(productId)
                .orElseThrow(() -> new NoSuchProductException());
        productBasket.addProduct(productId);
    }
    public UserBasket getUserBasket() {
        Map<UUID, Integer> basketProducts = productBasket.getProducts();
        List<BasketItem> basketItems = basketProducts.entrySet().stream()
                .map(entry -> { UUID productId = entry.getKey();
                    Integer quantity = entry.getValue();
                    Product product = storageService.getProductById(productId)
                            .orElseThrow(() -> new NoSuchProductException());
                    return new BasketItem(product, quantity);
                })
                .collect(Collectors.toList());
        return new UserBasket(basketItems);
    }
}
