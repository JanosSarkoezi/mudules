package com.mycompany.module3;

import com.mycompany.module1.jpa.Address;
import com.mycompany.module1.jpa.dao.AddressDao;
import com.mycompany.module2.AddresServiceLocal;
import com.mycompany.module2.AddresServiceRemote;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;

/**
 *
 * @author saj
 */
@Stateless(mappedName = "module/AddressService")
@Local(AddresServiceLocal.class)
@Remote(AddresServiceRemote.class)
public class AddressServiceBean implements AddresServiceLocal, AddresServiceRemote {

    @EJB
    private AddressDao addressDao;

    @Override
    public Address find(long id) {
        return addressDao.find(id);
    }

    @Override
    public List<Address> findAll() {
        return addressDao.findAll();
    }

    @Override
    public void persist(Address address) {
        addressDao.persist(address);
    }
}
