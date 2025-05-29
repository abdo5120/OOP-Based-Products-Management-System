package main;


import java.util.HashMap;
import java.util.Map;

public class Category
{
    private String categoryName;
    public Map<String, Product> productOfCategory = new HashMap<String, Product>();


    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void addProduct(String nameProduct, int quantityProduct, double priceProduct)
    {
        Product product = new Product();
        product.setProductName(nameProduct);
        product.setProductQuantity(quantityProduct);
        product.setProductPrice(priceProduct);

        productOfCategory.put(nameProduct, product);
    }
    public void ModifyProductQuantity(String nameProduct, int quantityProduct)
    {
        Product product = productOfCategory.get(nameProduct);
        product.setProductQuantity(product.getProductQuantity() + quantityProduct);

        productOfCategory.put(nameProduct, product);
    }
    public void ModifyProductPrice(String nameProduct, double priceProduct)
    {
        Product product = productOfCategory.get(nameProduct);
        product.setProductPrice(priceProduct);

        productOfCategory.put(nameProduct, product);
    }

    public void deleteProduct(String nameProduct)
    {
        productOfCategory.remove(nameProduct);
    }

    public Product getProduct(String nameProduct)
    {
        Product product = productOfCategory.get(nameProduct);
        return product;
    }
}
