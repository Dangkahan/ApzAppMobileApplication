package apzshop.client_mobile.apzappmobile.Model;

public class AddressModel {

    String userAddress;

    public AddressModel() {
    }

    boolean isSelected;

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
