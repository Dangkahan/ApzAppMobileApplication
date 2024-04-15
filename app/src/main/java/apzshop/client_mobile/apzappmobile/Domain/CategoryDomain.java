package apzshop.client_mobile.apzappmobile.Domain;

import java.io.Serializable;

public class CategoryDomain implements Serializable {

    private String type;
    private String catpicUrl;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCatpicUrl() {
        return catpicUrl;
    }

    public void setCatpicUrl(String catpicUrl) {
        this.catpicUrl = catpicUrl;
    }

    public CategoryDomain(String type, String catpicUrl) {
        this.type = type;
        this.catpicUrl = catpicUrl;
    }
}
