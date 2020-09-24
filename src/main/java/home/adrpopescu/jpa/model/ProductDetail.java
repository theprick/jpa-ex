package home.adrpopescu.jpa.model;

import javax.persistence.*;

@Entity
public class ProductDetail {

    @Id
    private String code;

    @Column(name = "PRODUCT_DESCRIPTION")
    private String description;

    @Column(name = "SPECIFICATIONS")
    private String specifications;

    @Column(name = "PRODUCT_PRICE")
    private Double price;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "PRODUCT_CODE")
    private Product product;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
