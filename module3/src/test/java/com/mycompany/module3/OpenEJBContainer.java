package com.mycompany.module3;

import java.util.Properties;
import javax.ejb.embeddable.EJBContainer;
import org.junit.BeforeClass;

/**
 *
 * @author saj
 */
public class OpenEJBContainer {

    protected static EJBContainer ejbContainer;

    @BeforeClass
    public static void setupClass() {
        final Properties p = new Properties();
        p.put("java:/MSSQL_DS", "new://Resource?type=DataSource");
        p.put("java:/MSSQL_DS.JdbcDriver", "org.hsqldb.jdbcDriver");
        p.put("java:/MSSQL_DS.JdbcUrl", "jdbc:hsqldb:mem:test");

//        p.put("module1-PU.hibernate.hbm2ddl.import_files", "sql/h2/import.sql");
        p.put("module1-PU.hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        p.put("module1-PU.hibernate.hbm2ddl.auto", "create-drop");
        p.put("module1-PU.hibernate.show_sql", "true");

        ejbContainer = EJBContainer.createEJBContainer(p);
    }
}
