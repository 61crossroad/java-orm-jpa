package org.jetlag;

import javax.persistence.EntityManager;

public class JpqlRepository {
    private final EntityManager em;

    public JpqlRepository(EntityManager em) {
        this.em = em;
    }

    public void select() {

    }
}
