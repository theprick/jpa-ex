package home.adrpopescu.jpa.bean;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import home.adrpopescu.jpa.model.Contact;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Popescu Adrian-Dumitru
 * Date: 5/9/13
 * Time: 7:17 PM
 */
@Component
public class ContactDao {

    @PersistenceContext
    private EntityManager em;

    public void saveContact(Contact contact) {
        em.persist(contact);
    }

    public Contact findById(long id){
        return em.find(Contact.class, id);
    }

    public List<Contact> findByFirstName(String firstName){
        List<Contact> result = em.createQuery("from Contact c where c.firstName = :firstName", Contact.class)
                .setParameter("firstName", firstName)
                .getResultList();

        return result;
    }

    public List<Contact> findByVAT(String vat){
        List<Contact> result = em.createQuery("from Contact c where c.vat = :vat", Contact.class)
                .setParameter("vat", vat)
                .getResultList();

        return result;
    }

    public void updateContact(Contact contact){
        em.persist(contact);
    }

    public void deleteContact(Contact contact){
        em.remove(contact);
    }
}
