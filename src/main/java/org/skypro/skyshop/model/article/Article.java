package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String title;
    private final String text;
    private final UUID id;

    public Article(String title, String text) {
        this.title = title;
        this.text = text;
        this.id = UUID.randomUUID();
    }
    public String getTitle() {
        return title;
    }
    public String getText() {
        return text;
    }
    @Override
    public UUID getId() {
        return id;
    }
    @Override
    public String toString() {
        return title + "\n" + text;
    }

    @JsonIgnore
    @Override
    public String getSearchTerm() {
        return title + " " + text;
    }

    @JsonIgnore
    @Override
    public String getTypeContent() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(title, article.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }
}
