/**
 * Created by alanflood on 18/04/15.
 */

public class product
{   //Product attributes declared
    private String name;
    private double price;
    private int quantity;

    public product (String productName, double productPrice, int numPurchased)
    {
        name = productName; //Name of the product
        price = productPrice; //Price of the product
        quantity = numPurchased; //Thr quantity of the product
    }
    //Next methods take the information from the array
    public String getName()
    {
        return this.name;
    }

    public double getPrice()
    {
        return this.price;
    }
    public int getQuantity()
    {
        return this.quantity;
    }
}