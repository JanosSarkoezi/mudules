package com.mycompany.module3;

import com.mycompany.module1.jpa.Address;
import com.mycompany.module2.AddresService;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author saj
 */
public class AddressServiceTest extends OpenEJBContainer {

    private AddresService cut;

    @Before
    public void bootContainer() throws Exception {
        Object object = ejbContainer.getContext().lookup("java:global/module3/AddressServiceBean");

        Assert.assertTrue(object instanceof AddresService);

        cut = (AddresService) object;
    }

    @Test
    public void findOnEmptyDB() {
        Address find = cut.find(1);
        Assert.assertNull(find);
    }

    @Test
    public void findOnNotEmptyDB() {
        cut.persist(new Address("Germany", "Berlin", "13456", "Bla Street", "15A"));
        cut.persist(new Address("Germany", "Berlin", "13456", "Bla Street", "16A"));

        List<Address> addresses = cut.findAll();
        Assert.assertEquals(2, addresses.size());
    }
}
