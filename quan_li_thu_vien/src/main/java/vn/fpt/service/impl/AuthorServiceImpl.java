package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.model.Author;
import vn.fpt.repository.AuthorRepository;
import vn.fpt.service.AuthorService;

import java.util.List;
@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    AuthorRepository authorRepository;
    @Override
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author findAuthorById(int id) {
        return authorRepository.findById(id).orElse(null);
    }

    @Override
    public void createAuthor(Author author) {
    authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Author author) {
    authorRepository.save(author);
    }

    @Override
    public void deleteAuthor(int id) {
    authorRepository.deleteById(id);
    }
}
