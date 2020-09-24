package home.adrpopescu.jpa.model;

import javax.persistence.*;

@Entity
public class Product {

    @Id
    @Column(name = "PRODUCT_CODE")
    private String code;

    @Column(name = "PRODUCT_NAME")
    private String name;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private ProductDetail detail;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductDetail getDetail() {
        return detail;
    }

    public void setDetail(ProductDetail detail) {
        if(detail == null) {
            if(this.detail != null) {
                this.detail.setProduct(null);
            }
        } else {
            detail.setProduct(this);
        }
        this.detail = detail;
    }
}
