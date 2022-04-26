package java.ac.uk.tees.w9547666.retailstoreapplication.model.entities;

/**
 * @author w9547666
 */

public class ProductCategory {

    private String prodCategoryName;
    private String prodCategoryDescription;
    private String prodCategoryDiscount;
    private String prodCategoryImageUrl;

    public ProductCategory(String prodCategoryName, String prodCategoryDescription, String prodCategoryDiscount, String prodCategoryImageUrl) {
        this.prodCategoryName = prodCategoryName;
        this.prodCategoryDescription = prodCategoryDescription;
        this.prodCategoryDiscount = prodCategoryDiscount;
        this.prodCategoryImageUrl = prodCategoryImageUrl;
    }

    public String getProdCategoryName() {
        return prodCategoryName;
    }

    public String getProdCategoryDescription() {
        return prodCategoryDescription;
    }

    public void setProdCategoryName(String prodCategoryName) {
        this.prodCategoryName = prodCategoryName;
    }

    public void setProdCategoryDescription(String prodCategoryDescription) {
        this.prodCategoryDescription = prodCategoryDescription;
    }

    public void setProdCategoryDiscount(String prodCategoryDiscount) {
        this.prodCategoryDiscount = prodCategoryDiscount;
    }

    public void setProdCategoryImageUrl(String prodCategoryImageUrl) {
        this.prodCategoryImageUrl = prodCategoryImageUrl;
    }

    public String getProdCategoryDiscount() {
        return prodCategoryDiscount;
    }

    public String getProdCategoryImageUrl() {
        return prodCategoryImageUrl;
    }
}
