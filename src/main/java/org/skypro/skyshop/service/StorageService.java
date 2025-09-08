package org.skypro.skyshop.service;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class StorageService {
    private final Map<UUID, Product> productMap;
    private final Map<UUID, Article> articleMap;

    public StorageService() {
        this.productMap = new HashMap<>();
        this.articleMap = new HashMap<>();
        this.initializeProductsAndArticles();
    }

    public Collection<Product> getAllProducts() {
        return productMap.values();
    }
    public Collection<Article> getAllArticles() {
        return articleMap.values();
    }

    public Collection<Searchable> getAllSearchableProductsAndArticles() {
        Collection<Searchable> searchableProductsAndArticles = new HashSet<>();
        searchableProductsAndArticles.addAll(productMap.values());
        searchableProductsAndArticles.addAll(articleMap.values());
        return searchableProductsAndArticles;
    }

    private void initializeProductsAndArticles() {
        SimpleProduct product1 = new SimpleProduct("Масло", 100);
        FixPriceProduct product2 = new FixPriceProduct("Хлеб");
        DiscountedProduct product3 = new DiscountedProduct("Молоко", 80, 15);
        DiscountedProduct product4 = new DiscountedProduct("Сыр", 180, 20);
        SimpleProduct product5 = new SimpleProduct("Торт", 400);
        SimpleProduct product6 = new SimpleProduct("Вино", 500);

        productMap.put(product1.getId(), product1);
        productMap.put(product2.getId(), product2);
        productMap.put(product3.getId(), product3);
        productMap.put(product4.getId(), product4);
        productMap.put(product5.getId(), product5);
        productMap.put(product6.getId(), product6);

        Article article1 = new Article("Java это круто!", "Написанную программу на Java можно запустить в любой ОС, поддерживающей JVM.");
        Article article2 = new Article("О пользе вина.", "Очень умеренное употребление алкоголя может оказывать защитное действие на сердце. В первую очередь это касается красного вина.");
        Article article3 = new Article("Искусственный интеллект Smart Engines.", "ИИ-помощника для проверки паспортов внедрили во Внуково.");
        Article article4 = new Article("Искусственный интеллект ChatGPT4.", "Нейросеть онлайн с искусственным интеллектом для текста и интеллектом для решения задач.");
        Article article5 = new Article("Искусственный интеллект ElevenLabs.", "Преобразование текста в реалистичную речь с помощью нейросети.");
        Article article6 = new Article("Искусственный интеллект ElavenLabs.", "Преобразование текста в реалистичную речь с помощью нейросети.");

        articleMap.put(article1.getId(), article1);
        articleMap.put(article2.getId(), article2);
        articleMap.put(article3.getId(), article3);
        articleMap.put(article4.getId(), article4);
        articleMap.put(article5.getId(), article5);
        articleMap.put(article6.getId(), article6);


    }

}
