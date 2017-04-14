package com.vahan;

import javax.ejb.LocalBean;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * Created by TCE\vahany on 4/10/17.
 */
@Stateless
@LocalBean
public class BookEJB /*implements BookEJBRemote*/ {

    // ======================================
    // =             Attributes             =
    // ======================================

    /*@Inject*/
    @PersistenceContext(unitName = "book001")
    private EntityManager em;

    // ======================================
    // =           Public Methods           =
    // ======================================

    public List findBooks() {
        Query query = em.createNativeQuery("SELECT * FROM BOOK");
        return query.getResultList();
    }

    public
    @NotNull
    Book createBook(@NotNull Book book) {
        em.persist(book);
        return book;
    }

    public
    @NotNull
    Book updateBook(@NotNull Book book) {
        return em.merge(book);
    }

    public void deleteBook(@NotNull Book book) {
        em.remove(em.merge(book));
    }
}