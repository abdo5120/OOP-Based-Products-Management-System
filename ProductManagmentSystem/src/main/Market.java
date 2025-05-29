package main;

import java.util.HashMap;
import java.util.Map;


public class Market
{
    Map<String,Category> marketCategory = new HashMap<>();

    public void printSpecificCategory(String categoryName)
    {
        // print categories
        System.out.println("Category : "+categoryName);
        System.out.print(".........\n");
        Category category = marketCategory.get(categoryName);
        // print products
        if(category.productOfCategory.isEmpty())
        {
            System.out.println("\tNo Products in This Category\n");
        }
        else
        {
            for (Map.Entry<String, Product> entry1 : category.productOfCategory.entrySet())
            {
                Product product = entry1.getValue();
                if(product.getProductQuantity()==0)
                {
                    System.out.print("\tProduct : ");
                    TerminalUtils.setColor(Colors.RED);
                    System.out.print(product.getProductName());
                    TerminalUtils.setColor(Colors.BRIGHT_WHITE);
                    System.out.print("  there is no quantity now\n");
                }
                else
                    System.out.println("\tProduct : "+product.getProductName()
                            +"\tQuantity : "+product.getProductQuantity()
                            +"\tPrice : "+product.getProductPrice());
            }
        }
        System.out.println();
    }
    public void printCategoryAndProduct ()
    {
        System.out.print("\t\tThis is our categories and Products\n");
        for(Map.Entry<String,Category> entry : marketCategory.entrySet())
        {
            String categoryName = entry.getKey();
            printSpecificCategory(categoryName);
        }
    }
    public void addNewCategory()
    {
        while(true)
        {
            TerminalUtils.setColor(Colors.BRIGHT_WHITE);

            System.out.print("PLZ Enter Category Name That You Want To Add : ");
            String categoryName = TerminalUtils.getInput(Colors.LIGHT_GREEN, String.class);

            if (categoryFounded(categoryName))
            {
                System.out.println("\nThis Category Already Found");
            }
            else
            {
                TerminalUtils.setColor(Colors.BRIGHT_WHITE);

                Category newCategory = new Category();
                newCategory.setCategoryName(categoryName);
                marketCategory.put(categoryName,newCategory);
                TerminalUtils.setColor(Colors.BLUE);
                System.out.println("          Successful Add \n");
                TerminalUtils.setColor(Colors.BRIGHT_WHITE);

                if (!TerminalUtils.tryAgain())
                    break;
            }
        }
    }
    public void deleteCategory()
    {
        while(true)
        {
            TerminalUtils.setColor(Colors.BRIGHT_WHITE);

            System.out.print("PLZ Enter Category Name that You want to Delete : ");
            String categoryName = TerminalUtils.getInput(Colors.RED, String.class);
            System.out.println();

            if (categoryFounded(categoryName))
            {
                marketCategory.remove(categoryName);
                TerminalUtils.setColor(Colors.BLUE);
                System.out.println("          Successful Delete \n");
                TerminalUtils.setColor(Colors.BRIGHT_WHITE);
                break;
            } else
            {
                printCategoryNotFounded(categoryName);

                if (!TerminalUtils.tryAgain())
                    break;
            }

        }
    }

    public void addOrModifyNewProduct()
    {
        while(true)
        {
            System.out.print("PLZ Enter Category Name : ");
            String categoryName = TerminalUtils.getInput(Colors.RED, String.class);
            System.out.println();

            if (categoryFounded(categoryName))
            {

                System.out.print("PLZ Enter Product Name : ");
                String productName = TerminalUtils.getInput(Colors.RED, String.class);
                //System.out.println();

                if (productFounded(categoryName, productName))
                {
                    System.out.print("This Product Already Exists \n");
                    // modify quantity
                    System.out.print(" Do You Want To Add To It ? YES:NO => ");
                    String checkingModify = TerminalUtils.getInput(Colors.RED, String.class);
                    if (TerminalUtils.convertStringToLower(checkingModify).equals("yes"))
                    {
                        System.out.print("PLZ Enter The Quantity You Want To Add It To The Current Quantity => ");
                        int productQuantityToAdd = TerminalUtils.getInput(Colors.RED, Integer.class);
                        marketCategory.get(categoryName).ModifyProductQuantity(productName, productQuantityToAdd);
                    }

                    // modify Price
                    System.out.print(" Do You Want To Modify Price ? YES:NO => ");
                    checkingModify = TerminalUtils.getInput(Colors.RED, String.class);
                    if (TerminalUtils.convertStringToLower(checkingModify).equals("yes"))
                    {
                        System.out.print("PLZ Enter The New Price Of The This Product => ");
                        double newProductPrice = TerminalUtils.getInput(Colors.RED, Double.class);
                        marketCategory.get(categoryName).ModifyProductPrice(productName, newProductPrice);
                    }
                    return;
                } else
                {
                    int productQuantity;
                    // add quantity
                    while (true)
                    {
                        System.out.print("PLZ Enter Quantity You Want To Add => ");
                        productQuantity = TerminalUtils.getInput(Colors.RED, Integer.class);
                        if (productQuantity < 0)
                        {
                            System.out.println("Not Valid Number \n   ");
                            if (!TerminalUtils.tryAgain())
                                return;
                        } else
                            break;
                    }

                    double productPrice;
                    // add Price
                    while (true)
                    {
                        System.out.print("PLZ Enter The Price Of The This Product => ");
                        productPrice = TerminalUtils.getInput(Colors.RED, Double.class);
                        if (productPrice < 0)
                        {
                            System.out.println("Not Valid Number \n   ");
                            if (!TerminalUtils.tryAgain())
                                return;
                        } else
                            break;
                    }
                    marketCategory.get(categoryName).addProduct(productName, productQuantity, productPrice);
                    TerminalUtils.setColor(Colors.BLUE);
                    System.out.println("          Successful Add \n");
                    TerminalUtils.setColor(Colors.BRIGHT_WHITE);
                }
            }
            else
            {
                printCategoryNotFounded(categoryName);
            }
            if (!TerminalUtils.tryAgain())
                break;
        }
    }
    public void deleteProduct()
    {
        while(true)
        {
            System.out.print("PLZ Enter Category Name : ");
            String categoryName = TerminalUtils.getInput(Colors.RED, String.class);
            System.out.println();

            if (categoryFounded(categoryName))
            {
                System.out.print("PLZ Enter Product Name : ");
                String productName = TerminalUtils.getInput(Colors.RED, String.class);
                System.out.println();

                if(productFounded(categoryName,productName))
                {
                    marketCategory.get(categoryName).deleteProduct(productName);
                    TerminalUtils.setColor(Colors.BLUE);
                    System.out.print("          Successful Delete \n");
                    TerminalUtils.setColor(Colors.BRIGHT_WHITE);
                }
                else
                {
                    printProductNotFounded(productName);
                    if (!TerminalUtils.tryAgain())
                    {
                        System.out.println("\t\tFailed");
                        break;
                    }
                }
            }
            else
            {
                printCategoryNotFounded(categoryName);
                if (!TerminalUtils.tryAgain())
                {
                    System.out.println("\t\tFailed");
                    break;
                }
            }

            if (!TerminalUtils.tryAgain())
            {
                break;
            }
        }
    }
    public void moveProductToAnotherCategory()
    {
        while(true)
        {
            System.out.print("PLZ Enter First Category Name : ");
            String firstCategoryName = TerminalUtils.getInput(Colors.RED, String.class);
            System.out.println();

            if (categoryFounded(firstCategoryName))
            {
                System.out.print("PLZ Enter Product Name : ");
                String productName = TerminalUtils.getInput(Colors.RED, String.class);
                System.out.println();

                if(productFounded(firstCategoryName,productName))
                {
                    System.out.print("PLZ Enter Second Category Name : ");
                    String secondCategoryName = TerminalUtils.getInput(Colors.RED, String.class);
                    System.out.println();

                    if (categoryFounded(secondCategoryName))
                    {
                        Product product = marketCategory.get(firstCategoryName).getProduct(productName);
                        marketCategory.get(secondCategoryName).addProduct(productName,product.getProductQuantity(),product.getProductPrice());
                        marketCategory.get(firstCategoryName).deleteProduct(productName);

                        TerminalUtils.setColor(Colors.BLUE);
                        System.out.print("          Successful Move \n");
                        TerminalUtils.setColor(Colors.BRIGHT_WHITE);
                    }
                    else
                    {
                        printCategoryNotFounded(secondCategoryName);
                    }
                }
                else
                {
                    printProductNotFounded(productName);
                }
            }
            else
            {
                printCategoryNotFounded(firstCategoryName);
            }

            if (!TerminalUtils.tryAgain())
            {
                break;
            }
        }
    }
    public void makeOrder()
    {
        Order order = new Order();

        while(true)
        {
            System.out.print("PLZ Enter Category Name : ");
            String categoryName = TerminalUtils.getInput(Colors.RED, String.class);
            System.out.println();

            if (categoryFounded(categoryName))
            {
                printSpecificCategory(categoryName);

                System.out.print("PLZ Enter Product Name that You Want To sell : ");
                String productName = TerminalUtils.getInput(Colors.RED, String.class);
                Product product = marketCategory.get(categoryName).getProduct(productName);
                System.out.println();

                if (productFounded(categoryName, productName))
                {
                    int productQuantitySold;
                    while (true)
                    {
                        System.out.print("PLZ Enter Quantity that You Want To sell : ");
                        productQuantitySold = TerminalUtils.getInput(Colors.RED, Integer.class);

                        if(productQuantitySold > product.getProductQuantity())
                        {
                            System.out.println("This Product found but there is no quantity now");
                            if (!TerminalUtils.tryAgain())
                                return;
                        }
                        else if (productQuantitySold < 0)
                        {
                            System.out.println("Not Valid Number \n   ");
                            if (!TerminalUtils.tryAgain())
                                return;
                        }
                        else
                            break;

                    }

                    double totalProductPrice = product.getProductPrice()*productQuantitySold;

                    order.productSold.add(productName);
                    order.setTotalPrice(totalProductPrice);

                    product.setProductQuantity(product.getProductQuantity() - productQuantitySold);
                }
                else
                {
                    printProductNotFounded(productName);
                }
            }
            else
            {
                printCategoryNotFounded(categoryName);
            }

            if (!TerminalUtils.tryAgain())
            {
                break;
            }
        }

        if(order.getTotalPrice() > 0)
        {
            System.out.println("\n   **Your Receipt.....\n");
            int count = 1;
            for(String product : order.productSold)
                System.out.print((count++) + " -> " + product + "\n");
            System.out.print("And your total price = $ ");
            TerminalUtils.setColor(Colors.RED);
            System.out.print(order.getTotalPrice());
            TerminalUtils.setColor(Colors.WHITE);
            System.out.println();
            TerminalUtils.pause();
        }
    }

    public boolean categoryFounded(String categoryName)
    {
        return marketCategory.containsKey(categoryName);
    }
    public void printCategoryNotFounded(String categoryName)
    {
        TerminalUtils.setColor(Colors.BRIGHT_WHITE);
        System.out.print("This Category : ");
        TerminalUtils.setColor(Colors.RED);
        System.out.print(categoryName);
        TerminalUtils.setColor(Colors.BRIGHT_WHITE);
        System.out.println(" does not exist\n");
    }
    public boolean productFounded(String categoryName, String productName)
    {
        if(categoryFounded(categoryName))
            return marketCategory.get(categoryName).productOfCategory.containsKey(productName);
        else
            return false;
    }
    public void printProductNotFounded(String productName)
    {
        System.out.print("This Product : ");
        TerminalUtils.setColor(Colors.RED);
        System.out.print(productName);
        TerminalUtils.setColor(Colors.BRIGHT_WHITE);
        System.out.print(" does not exist\n");
    }

}
