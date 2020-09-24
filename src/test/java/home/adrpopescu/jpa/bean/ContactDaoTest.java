package home.adrpopescu.jpa.bean;

import home.adrpopescu.jpa.model.Contact;
import home.adrpopescu.jpa.model.ContactFr;
import home.adrpopescu.jpa.model.ContactUk;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Popescu Adrian-Dumitru
 * Date: 5/9/13
 * Time: 7:15 PM
 */
@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class ContactDaoTest {

    @Autowired
    private ContactDao contactDao;

    @Test
    public void testCreateContact(){
        ContactFr contactFr = createContactFr("Pierre", "Jean", "2323/SXF/3243");
        contactDao.saveContact(contactFr);
        Assert.assertNotNull(contactFr.getId());

        ContactUk contactUk = createContactUk("Margaret", "Thatcher");
        contactDao.saveContact(contactUk);
        Assert.assertNotNull(contactUk.getId());
    }

    private ContactUk createContactUk(String firstName, String lastName) {
        ContactUk contactUk = new ContactUk();
        contactUk.setFirstName(firstName);
        contactUk.setLastName(lastName);
        contactUk.setTradeName("Demolitions");
        contactUk.setWhoisOptOutEnabled(0);
        contactUk.setEntityType("Female");
        return contactUk;
    }

    private ContactFr createContactFr(String firstName, String lastName, String vat) {
        ContactFr contactFr = new ContactFr();
        contactFr.setFirstName(firstName);
        contactFr.setLastName(lastName);
        contactFr.setBirthDate(new Date());
        contactFr.setSiren("32984987324");
        contactFr.setVat(vat);
        return contactFr;
    }

    @Test
    public void testFilterByFirstName(){
        long tmsp = System.currentTimeMillis();
        String firstName = "Anais_" + tmsp;

        ContactFr contactFr = createContactFr(firstName, "Amyot", "2323/SXF/3243");
        contactDao.saveContact(contactFr);
        ContactUk contactUk = createContactUk(firstName, "Smith");
        contactDao.saveContact(contactUk);

        List<Contact> result = contactDao.findByFirstName(firstName);
        Assert.assertEquals(2, result.size());
    }

    @Test
    public void testFilterByVAT(){
        long tmsp = System.currentTimeMillis();
        String vat = "2323/SXF/"+tmsp;

        ContactFr contactFr = createContactFr("Vasile", "Robert", vat);
        contactDao.saveContact(contactFr);

        List<Contact> result = contactDao.findByVAT(vat);
        Assert.assertEquals(1, result.size());
    }

    @Test
    public void testUpdateContact(){
        ContactFr contactFr = createContactFr("Vasile", "Amyot", null);
        contactDao.saveContact(contactFr);
        Assert.assertNotNull(contactFr.getId());

        contactFr = (ContactFr)contactDao.findById(contactFr.getId());
        contactFr.setFirstName("George");
        contactFr.setSiren("32984987333");
        contactFr.setVat("2323/SXF/3243");
        contactDao.updateContact(contactFr);
        Assert.assertNotNull(contactFr.getId());

        ContactFr actualContact = (ContactFr)contactDao.findById(contactFr.getId());
        Assert.assertEquals(contactFr.getFirstName(), actualContact.getFirstName());
        Assert.assertEquals(contactFr.getLastName(), actualContact.getLastName());
        Assert.assertEquals(contactFr.getVat(), actualContact.getVat());
    }

    @Test
    public void deleteContact(){
        ContactFr contactFr = createContactFr("Vasile", "Allaire", null);
        contactDao.saveContact(contactFr);
        Assert.assertNotNull(contactFr.getId());

        contactDao.deleteContact(contactFr);

        Contact actualContact = contactDao.findById(contactFr.getId());
        Assert.assertNull(actualContact);
    }
}