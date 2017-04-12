package com.vahan;

import javax.ejb.Remote;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by TCE\vahany on 4/10/17.
 */
@Remote
public interface BookEJBRemote {

    // ======================================
    // =           Public Methods           =
    // ======================================

    List findBooks();

    @NotNull
    Book createBook(@NotNull Book book);

    void deleteBook(@NotNull Book book);

    @NotNull Book updateBook(@NotNull Book book);
}