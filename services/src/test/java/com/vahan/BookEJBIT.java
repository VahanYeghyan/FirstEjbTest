package com.vahan;


import org.junit.Test;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class BookEJBIT {

    // ======================================
    // =              Unit tests            =
    // ======================================

    @Test
    public void shouldCreateABook() throws Exception {

        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));

        try (EJBContainer ec = EJBContainer.createEJBContainer(properties)) {
            Context ctx = ec.getContext();

            // Check JNDI dependencies

            assertNotNull(ctx.lookup("java:global/jdbc/bookDS"));
            assertNotNull(ctx.lookup("java:global/classes/BookEJB!com.vahan.BookEJBRemote"));
            assertNotNull(ctx.lookup("java:global/classes/BookEJB!com.vahan.BookEJB"));


            // Looks up the EJB
            BookEJB bookEJB = (BookEJB) ctx.lookup("java:global/classes/BookEJB!com.vahan.BookEJB");

            // Finds all the books and makes sure there are two (inserted by the DBPopulator Singleton)
            assertEquals(2, bookEJB.findBooks().size());

            // Creates an instance of book
            Book book = new Book("The Hitchhiker's Guide to the Galaxy", 12.5F, "Science fiction by Douglas Adams.", "1-24561-799-0", 354, false);

            // Persists the book to the database
            book = bookEJB.createBook(book);
            assertNotNull("ID should not be null", book.getId());

            // Finds all the books and makes sure there is an extra one
            assertEquals(3, bookEJB.findBooks().size());

            // Removes the created book
            bookEJB.deleteBook(book);

            // Finds all the books and makes sure there is one less
            assertEquals(2, bookEJB.findBooks().size());
        }
    }
}