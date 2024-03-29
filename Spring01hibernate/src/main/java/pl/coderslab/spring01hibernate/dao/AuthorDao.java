package pl.coderslab.spring01hibernate.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.spring01hibernate.entity.Author;
import pl.coderslab.spring01hibernate.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Author create(Author entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Author findById(long id) {
        return entityManager.find(Author.class, id);
    }

    public Author findByIdWithBooks(long id) {
        Author result = entityManager.find(Author.class, id);
        Hibernate.initialize(result.getBooks());

        return result;
    }

    public List<Author> findAll() {
        return entityManager.createQuery("SELECT e FROM Author e").getResultList();
    }
}
