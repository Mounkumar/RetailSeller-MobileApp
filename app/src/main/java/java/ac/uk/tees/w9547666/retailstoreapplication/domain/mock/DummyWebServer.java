package java.ac.uk.tees.w9547666.retailstoreapplication.domain.mock;

import java.ac.uk.tees.w9547666.retailstoreapplication.model.Repository;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.Product;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.ProductCategory;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author w9547666
 */

public class DummyWebServer {

    private static DummyWebServer dummyServer;

    public static DummyWebServer getDummyWebServer() {

        if (null == dummyServer) {
            dummyServer = new DummyWebServer();
        }
        return dummyServer;
    }

    void initiateDummyServer() {

        addCategory();

    }

    public void addCategory() {

        ArrayList<ProductCategory> listOfCategory = new ArrayList<ProductCategory>();

        listOfCategory
                .add(new ProductCategory(
                        "Alcohol",
                        "Alcoholic Drinks",
                        "10%",
                        "cat_alcohol"
                ));

        listOfCategory
                .add(new ProductCategory(
                        "Hot drinks and smothies",
                        "Hot drinks and the smothies",
                        "15%",
                        "cat_hotdrinks"));

        Repository.getCenterRepository().setListOfCategory(listOfCategory);
    }

    public void getAllAlcholicDrinks() {

        ConcurrentHashMap<String, ArrayList<Product>> productMap = new ConcurrentHashMap<String, ArrayList<Product>>();

        ArrayList<Product> DragonSoopList = new ArrayList<Product>();

        // Ovens
        DragonSoopList
                .add(new Product(
                        "DragonSoop Venom",
                        "Dragon soop with venom flavour",
                        "Enjoy the most special one among all dragon soups which is one of the most liked.",
                        "2.99",
                        "0",
                        "2.99",
                        "0",
                        "dragonsoop",
                        "d_venom"));

        DragonSoopList
                .add(new Product(
                        "DragonSoop Apple",
                        "dragon soop with exciting apple flavour",
                        "This is a must try drink for all the lovers of apple  flavour.",
                        "2.99",
                        "0",
                        "2.99",
                        "0",
                        "dragonsoop",
                        "d_apple"));

        DragonSoopList
                .add(new Product(
                        "DragonSoop MangoPinkLemonade",
                        "DragonSoop with mango pink lemonade",
                        "DragonSoup with mango pink lemonade is one of highly selling flavours across the UK",
                        "2.99",
                        "0",
                        "2.99",
                        "0",
                        "dragonsoop",
                        "d_mango"));

        DragonSoopList
                .add(new Product(
                        "DragonSoop BlueRasbery",
                        "DragonSoop with blue rasbery flavours",
                        "this is a fantastic option for all the blue rasbery lovers",
                        "2.99",
                        "0",
                        "2.99",
                        "0",
                        "dragonsoop",
                        "d_blue"));

        DragonSoopList
                .add(new Product(
                        "DragonSoop DarkFruit",
                        "DragonSoop with darkfruit flavour",
                        "Do you love dark fruit flavour? then don't miss it.",
                        "2.99",
                        "0",
                        "2.99",
                        "0",
                        "dragonsoop",
                        "d_darkf"));

        productMap.put("DRAGONSOOP", DragonSoopList);

        ArrayList<Product> vodkaList = new ArrayList<Product>();

        // TV
        vodkaList.add(new Product(
                "Smirnoff",
                "Smirnoff 1 litre",
                "Smirnoff 1 litre vodka.",
                "20.99",
                "0",
                "20.99",
                "0",
                "smirnoff",
                "smirnoff"));

        vodkaList.add(new Product(
                "Glenz",
                "Glenz 1 litre",
                "Glenz 1 litre Vodka.",
                "20.99",
                "0",
                "20.99",
                "0",
                "glens",
                "glenz"));


        productMap.put("Vodka", vodkaList);

        ArrayList<Product> ciderList = new ArrayList<Product>();

        ciderList
                .add(new Product(
                        "Carling",
                        "Carling 500 ml can",
                        "Carling is one of the most selling cost effective among all the ciders.",
                        "1.5",
                        "0",
                        "1.5",
                        "0",
                        "carling",
                        "carling"));

        ciderList
                .add(new Product(
                        "Foster",
                        "Foster 440 ml can",
                        "Fosters is cheap and best ciders among the UKs most famous ciders",
                        "1.5",
                        "0",
                        "1.5",
                        "0",
                        "fosters",
                        "foster"));

        productMap.put("Cider", ciderList);


        Repository.getCenterRepository().setMapOfProductsInCategory(productMap);

    }

    public void getAllHotandSoftDrinks() {

        ConcurrentHashMap<String, ArrayList<Product>> productMap = new ConcurrentHashMap<String, ArrayList<Product>>();

        ArrayList<Product> Coffeproductlist = new ArrayList<Product>();

        // Table
        Coffeproductlist
                .add(new Product(
                        "Capachino",
                        "Hot capachino",
                        "Hot and delicious capachino made with castomer desired toppings",
                        "3.00",
                        "0",
                        "3.00",
                        "0",
                        "coffee",
                        "cap"));

        Coffeproductlist
                .add(new Product(
                        "Latte",
                        "Hot latte",
                        "Hot and delicious Latte served with additional toppings as per customer wish.",
                        "3.00",
                        "0",
                        "3.00",
                        "0",
                        "coffee",
                        "latte"));


        productMap.put("Hot drniks", Coffeproductlist);

        ArrayList<Product> juiceList = new ArrayList<Product>();


        juiceList
                .add(new Product(
                        "Apple Juice",
                        "Cold Apple Juice",
                        "Cold and delicious apple juice.",
                        "3.00",
                        "0",
                        "3.00",
                        "0",
                        "apple_juice",
                        "applej"));

        juiceList
                .add(new Product(
                        "Orange Juice",
                        "Cold orange Juice",
                        "Cold and Origional Orange Juice.",
                        "3.00",
                        "0",
                        "3.00",
                        "0",
                        "orange_juice",
                        "orange_juice"));



        productMap.put("SoftDrinks", juiceList);


        Repository.getCenterRepository().setMapOfProductsInCategory(productMap);

    }

    public void getAllProducts(int productCategory) {

        if (productCategory == 0) {

            getAllAlcholicDrinks();
        } else {

            getAllHotandSoftDrinks();

        }

    }

}
