package java.ac.uk.tees.w9547666.retailstoreapplication.model.entities;

/**
 * @author w9547666
 */

public class Product {


    /**
     * The short description of the product
     */
    private String description = "";

    /**
     * The elaborated detail of the product.
     */
    private String longDescription = "";

    /**
     * The maximum retail price of the product.
     */
    private String mrp;

    /**
     * The discount offered on a product.
     */
    private String discount;

    /**
     * The selling price of the product.
     */
    private String salePrice;

    /**
     * The order quantity.
     */
    private String orderQty;

    /**
     * The image url of the product.
     */
    private String imageUrl = "";

    /**
     * The name of the product.
     */
    private String productName = "";

    /**
     * The unique ID of the product.
     */
    private String productId = "";

    /**
     * @param productName
     * @param description
     * @param details
     * @param MRP
     * @param discount
     * @param sellingMRP
     * @param orderquantity
     * @param imageURL
     */
    public Product(String productName, String description, String details,
                   String MRP, String discount, String sellingMRP, String orderquantity,
                   String imageURL, String productId) {
        this.productName = productName;
        this.description = description;
        this.longDescription = details;
        this.mrp = MRP;
        this.discount = discount;
        this.salePrice = sellingMRP;
        this.orderQty = orderquantity;
        this.imageUrl = imageURL;
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getItemName() {
        return productName;
    }

    public void setItemName(String itemName) {
        this.productName = itemName;
    }

    public String getItemShortDesc() {
        return description;
    }

    public void setItemShortDesc(String itemShortDesc) {
        this.description = itemShortDesc;
    }

    public String getItemDetail() {
        return longDescription;
    }

    public void setItemDetail(String itemDetail) {
        this.longDescription = itemDetail;
    }

    public String getMRP() {
        return this.mrp;
    }

    public void setMRP(String MRP) {
        this.mrp = MRP;
    }

    public String getDiscount() {
        return discount + "%";
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getDiscountNumeric() {
        return discount;
    }

    public String getSellMRP() {
        return salePrice;
    }

    public void setSellMRP(String sellMRP) {
        this.salePrice = sellMRP;
    }

    public String getQuantity() {
        return orderQty;
    }

    public void setQuantity(String quantity) {
        this.orderQty = quantity;
    }

    public String getImageURL() {
        return imageUrl;
    }

    public void setImageURL(String imageURL) {
        this.imageUrl = imageURL;
    }

}
