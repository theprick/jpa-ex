package home.adrpopescu.jpa.model;

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Popescu Adrian-Dumitru
 * Date: 5/9/13
 * Time: 6:53 PM
 */


@Entity
@AttributeOverrides({
        @AttributeOverride(name="firstName", column=@Column(name="firstName")),
        @AttributeOverride(name="lastName", column=@Column(name="lastName"))
})
public class ContactFr extends Contact {

    private Date birthDate;

    private String vat;

    private String siren;

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getVat() {
        return vat;
    }

    public void setVat(String vat) {
        this.vat = vat;
    }

    public String getSiren() {
        return siren;
    }

    public void setSiren(String siren) {
        this.siren = siren;
    }
}
