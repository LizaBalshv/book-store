package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepositoryImpl implements BookRepository {

    private final SessionFactory sessionFactory;

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public BookRepositoryImpl(SessionFactory sessionFactory,
                              EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = sessionFactory;
        this.entityManagerFactory = entityManagerFactory;
    }

    @Override
    public Book save(Book book) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error saving book into DB" + book);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            Book book = entityManager.find(Book.class, id);
            return Optional.ofNullable(book);
        }
    }

    @Override
    public List<Book> findAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT b FROM Book b", Book.class).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find books", e);
        }
    }
}
