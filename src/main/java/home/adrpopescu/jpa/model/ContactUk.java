package home.adrpopescu.jpa.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created with IntelliJ IDEA.
 * User: Popescu Adrian-Dumitru
 * Date: 5/9/13
 * Time: 6:55 PM
 */
@Entity
@AttributeOverrides({
        @AttributeOverride(name="firstName", column=@Column(name="firstName")),
        @AttributeOverride(name="lastName", column=@Column(name="lastName"))
})
public class ContactUk extends Contact {

    private String tradeName;

    private int whoisOptOutEnabled;

    private String entityType;

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public int getWhoisOptOutEnabled() {
        return whoisOptOutEnabled;
    }

    public void setWhoisOptOutEnabled(int whoisOptOutEnabled) {
        this.whoisOptOutEnabled = whoisOptOutEnabled;
    }

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }
}
