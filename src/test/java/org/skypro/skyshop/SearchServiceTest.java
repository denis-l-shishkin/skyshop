package org.skypro.skyshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {
    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    private SimpleProduct product1;
    private FixPriceProduct product2;
    private Article article1;
    private Article article2;
    private Collection<Searchable> searchables;

    @BeforeEach
    void setUp() {
        product1 = new SimpleProduct("Масло", 100);
        product2 = new FixPriceProduct("Хлеб");
        article1 = new Article("Искусственный интеллект ChatGPT4.", "Нейросеть онлайн с искусственным интеллектом для текста и интеллектом для решения задач.");
        article2 = new Article("Искусственный интеллект Smart Engines.", "ИИ-помощника для проверки паспортов внедрили во Внуково.");

        searchables = Arrays.asList(product1, product2, article1, article2);
    }

    @Test
    void whenStorageServiceIsEmpty_thenSearchServiceReturnEmptyList() {
        when(storageService.getAllSearchableProductsAndArticles()).thenReturn(Collections.emptyList());

        Collection<SearchResult> result = searchService.search("Молоко");

        assertTrue(result.isEmpty(), "Список должен быть пустым");
    }
    @Test
    void whenNoMatchingObjectsInStorageService_thenSearchServiceReturnEmptyList() {
        when(storageService.getAllSearchableProductsAndArticles()).thenReturn(searchables);

        Collection<SearchResult> result = searchService.search("Молоко");

        assertTrue(result.isEmpty(), "Список должен быть пустым");
    }

    @Test
    void whenOneMatchingObjectFoundInStorageService_thenSearchServiceReturnObjectsListWithOneObject() {
        when(storageService.getAllSearchableProductsAndArticles()).thenReturn(searchables);

        Collection<SearchResult> result = searchService.search("Масло");

        assertEquals(1, result.size(), "Должен быть один результат");

        SearchResult foundResult = result.iterator().next();
        assertEquals("Масло", foundResult.getName());
}

    @Test
    void whenTwoMatchingObjectsFoundInStorageService_thenSearchServiceReturnObjectsListWithTwoObjects() {
        when(storageService.getAllSearchableProductsAndArticles()).thenReturn(searchables);

        Collection<SearchResult> result = searchService.search("интеллект");

        //assertEquals(2, result.size(), "Должно быть две статьи");
        assertThat(result)
                .hasSize(2)
                .extracting(SearchResult::getName)
                .containsExactlyInAnyOrder("Искусственный интеллект ChatGPT4.", "Искусственный интеллект Smart Engines.");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"", " ", "   "})
    void whenPatternIsNullOrIsEmpty_thenSearchServiceReturnEmptyList(String pattern) {
        //when(storageService.getAllSearchableProductsAndArticles()).thenReturn(searchables);

        /*Collection<SearchResult> result1 = searchService.search("");
        Collection<SearchResult> result2 = searchService.search(" ");
        Collection<SearchResult> result3 = searchService.search(null);

        assertTrue(result1.isEmpty(), "Список должен быть пустым.");
        assertTrue(result2.isEmpty(), "Список должен быть пустым.");
        assertTrue(result3.isEmpty(), "Список должен быть пустым.");*/
        Collection<SearchResult> result = searchService.search(pattern);

        assertTrue(result.isEmpty(), "Список должен быть пустым.");
    }

    // Тест на перспективу при реализации регистронезависимого поиска.
    @Test
    void whenSearchShouldBeNoCaseSensitive_thenSearchServiceReturnOneObject() {

        when(storageService.getAllSearchableProductsAndArticles()).thenReturn(searchables);

        Collection<SearchResult> result = searchService.search("масло"); // с маленькой буквы

        assertTrue(result.isEmpty(), "Поиск регистрозависим, поэтому результат пустой.");
    }

}




