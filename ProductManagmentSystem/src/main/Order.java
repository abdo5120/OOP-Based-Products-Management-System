package main;

import java.util.HashSet;
import java.util.Set;

public class Order
{
    private double totalPrice = 0;
    Set<String> productSold = new HashSet<>();

    public void setTotalPrice(double Price)
    {
        totalPrice += Price;
    }
    public double getTotalPrice()
    {
        return totalPrice;
    }

    public String toString()
    {
        StringBuilder str = new StringBuilder();
        str.append("Total Price = $").append(totalPrice).append("\n");
        str.append("products sold : \n");

        int count = 1;
        for(String product : productSold)
            str.append(count++).append(" -> ").append(product).append("\n");

        return str.toString();
    }
}