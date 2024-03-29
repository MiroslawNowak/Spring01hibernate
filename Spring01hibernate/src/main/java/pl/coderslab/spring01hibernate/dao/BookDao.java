package pl.coderslab.spring01hibernate.dao;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import pl.coderslab.spring01hibernate.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Book create(Book entity) {
        entityManager.persist(entity);
        return entity;
    }

    public Book update(Book entity) {
        return entityManager.merge(entity);
    }

    public Book findById(long id) {
        Book book = entityManager.find(Book.class, id);
        Hibernate.initialize(book.getAuthors());
        return book;
//        Query q = entityManager.createQuery("SELECT DISTINCT b FROM Book b JOIN FETCH b.authors WHERE b.id = :id");
//        q.setParameter("id", id);
//        return (Book) q.getSingleResult();
    }

    public List<Book> findAll() {
        return entityManager.createQuery("SELECT DISTINCT b FROM Book b JOIN FETCH b.authors").getResultList();
    }

    public List<Book> findByRatingGt(int minRating) {
        Query q = entityManager.createQuery("SELECT DISTINCT b FROM Book b JOIN FETCH b.authors WHERE b.rating >= :rating");
        q.setParameter("rating", minRating);
        return q.getResultList();
    }
}
