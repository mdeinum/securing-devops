package biz.deinum.invoicer;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Charge extends AbstractPersistable<Long> {

    @ManyToOne
    private Invoice invoice;
    private String type;
    private BigDecimal amount;
    private String description;

    public Invoice getInvoice() {
        return invoice;
    }

    void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
