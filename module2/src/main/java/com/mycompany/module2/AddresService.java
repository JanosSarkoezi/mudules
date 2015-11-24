package com.mycompany.module2;

import com.mycompany.module1.jpa.Address;
import java.util.List;

/**
 *
 * @author saj
 */
public interface AddresService {
    public Address find(long id);
    public List<Address> findAll();
    public void persist(Address address);
}
