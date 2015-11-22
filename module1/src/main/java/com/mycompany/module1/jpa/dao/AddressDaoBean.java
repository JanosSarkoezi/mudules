package com.mycompany.module1.jpa.dao;

import com.mycompany.module1.jpa.Address;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author saj
 */
@Stateless
@Local(AddressDao.class)
@SuppressWarnings("unchecked")
public class AddressDaoBean implements AddressDao {

    @PersistenceContext(unitName = "module1-PU")
    private EntityManager em;

    @Override
    public Address find(long id) {
        return em.find(Address.class, id);
    }
}
