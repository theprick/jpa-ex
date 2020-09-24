package home.adrpopescu.jpa.bean;

import home.adrpopescu.jpa.model.Product;
import home.adrpopescu.jpa.model.ProductDetail;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration("/test-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback=false)
public class ProductTest {

    public static final String PRODUCT_CODE = "TEST";

    @Autowired
    private ProductDao productDao;

    @Test
    public void test() {
        Product prod = new Product();
        String productCode = PRODUCT_CODE + System.currentTimeMillis();
        prod.setCode(productCode);
        prod.setName("test1");
        productDao.saveProduct(prod);

        ProductDetail det = new ProductDetail();
        det.setCode(productCode);
        det.setDescription("test description 1");
        det.setSpecifications("test specifications 1");
        det.setPrice(1000.00);
        det.setProduct(prod);
        productDao.saveDetail(det);

        prod.setDetail(det);
        productDao.saveProduct(prod);

        Product savedProd = productDao.findByCode(productCode);
        Assert.assertEquals(productCode, savedProd.getCode());
        Assert.assertEquals(productCode, savedProd.getDetail().getCode());
        Assert.assertEquals("test description 1", savedProd.getDetail().getDescription());
        Assert.assertEquals("test specifications 1", savedProd.getDetail().getSpecifications());
        Assert.assertEquals(1000.00, savedProd.getDetail().getPrice());
    }
}
