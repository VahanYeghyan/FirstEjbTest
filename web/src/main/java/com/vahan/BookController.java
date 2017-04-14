package com.vahan;

/**
 * Created by TCE\vahany on 4/12/17.
 */

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Date;

@Named
@RequestScoped
public class BookController {


    /*private Context ctx = new InitialContext();
    private BookEJB bookEJB = (BookEJB) ctx.lookup("java:global/FirstEjbTest-services-1.0-SNAPSHOT/BookEJB");
*/
    @Inject
    private BookEJB bookEJB;



    /*@Inject
    private BookEJB bookEJB;*/

    private Book book = new Book();
    private Date currentDate = new Date();

    public BookController() throws NamingException {

    }

    // ======================================
    // =           Public Methods           =
    // ======================================

    public String doCreateBook() {
        book = bookEJB.createBook(book);
        return "result.xhtml";
    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }
}