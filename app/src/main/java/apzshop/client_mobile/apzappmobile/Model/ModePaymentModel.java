package apzshop.client_mobile.apzappmobile.Model;

public class ModePaymentModel {
 private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ModePaymentModel(String type) {
        this.type = type;
    }

    boolean isSelected;

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public ModePaymentModel() {
    }
}
