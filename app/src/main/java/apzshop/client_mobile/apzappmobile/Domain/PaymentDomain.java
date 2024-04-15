package apzshop.client_mobile.apzappmobile.Domain;

import java.io.Serializable;

public class PaymentDomain implements Serializable {

    String payment_add;

    public PaymentDomain(String payment_add) {
        this.payment_add = payment_add;
    }

    public String getPayment_add() {
        return payment_add;
    }

    public void setPayment_add(String payment_add) {
        this.payment_add = payment_add;
    }

    public  PaymentDomain(){

    }
    boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isSpecial() {
        return isSelected;
    }
}
