package com.mycompany.module1.jpa.dao;

import com.mycompany.module1.jpa.Address;
import java.util.List;

/**
 *
 * @author saj
 */
public interface AddressDao {
    public Address find(long id);
    public List<Address> findAll();
    public void persist(Address address);
}
