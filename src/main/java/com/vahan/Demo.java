package com.vahan;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

/**
 * Created by TCE\vahany on 4/10/17.
 */
public class Demo {

    public static void main(String[] args) throws NamingException {

        // Looks up the EJB
        Context ctx = new InitialContext();
        BookEJBRemote bookEJB = (BookEJBRemote) ctx.lookup("java:global/test/BookEJB!com.vahan.BookEJBRemote");

        // Gets and displays all the books from the database
        List<Book> books = bookEJB.findBooks();
        for (Book aBook : books) {
            System.out.println("--- " + aBook);
        }

        // Creates an instance of book
        Book book = new Book("The Hitchhiker's Guide to the Galaxy", 12.5F,
                "Science fiction by Douglas Adams.", "1-24561-799-0",
                354, false);

        book = bookEJB.createBook(book);
        System.out.println("### Book created : " + book);

        book.setTitle("H2G2");
        book = bookEJB.updateBook(book);
        System.out.println("### Book updated : " + book);

        bookEJB.deleteBook(book);
        System.out.println("### Book deleted");
    }
}
