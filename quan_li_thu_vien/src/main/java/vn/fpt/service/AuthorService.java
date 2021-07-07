package vn.fpt.service;

import vn.fpt.model.Author;

import java.util.List;

public interface AuthorService {
    public List<Author> findAllAuthors();

    public Author findAuthorById(int id);

    public void createAuthor(Author author);

    public void updateAuthor(Author author);

    public void deleteAuthor(int id);

}
