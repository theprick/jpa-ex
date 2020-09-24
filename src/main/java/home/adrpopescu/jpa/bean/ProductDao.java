package home.adrpopescu.jpa.bean;

import home.adrpopescu.jpa.model.Product;
import home.adrpopescu.jpa.model.ProductDetail;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class ProductDao {

    @PersistenceContext
    private EntityManager em;

    public void saveProduct(Product product) {
        em.persist(product);
    }
    public void saveDetail(ProductDetail detail) {
        em.persist(detail);
    }

    public Product findByCode(String code) {
        return em.createQuery("from " + Product.class.getName() + " p inner join fetch p.detail where p.code = '" + code + "'", Product.class)
                        .getSingleResult();
    }
}
