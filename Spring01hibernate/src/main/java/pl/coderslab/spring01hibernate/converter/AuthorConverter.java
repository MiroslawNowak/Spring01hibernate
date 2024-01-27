package pl.coderslab.spring01hibernate.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import pl.coderslab.spring01hibernate.dao.AuthorDao;
import pl.coderslab.spring01hibernate.entity.Author;

public class AuthorConverter implements Converter<String, Author> {
    @Autowired
    private AuthorDao dao;

    @Override
    public Author convert(String id) {
        return dao.findById(Long.parseLong(id));
    }
}
