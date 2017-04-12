package com.vahan;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Created by TCE\vahany on 4/10/17.
 */

public class DatabaseProducer {
    @Produces
    @PersistenceContext(unitName = "book001")
    private EntityManager em;
}
