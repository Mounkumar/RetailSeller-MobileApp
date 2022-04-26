package java.ac.uk.tees.w9547666.retailstoreapplication.model;

import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.Product;
import java.ac.uk.tees.w9547666.retailstoreapplication.model.entities.ProductCategory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author w9547666
 */

public class Repository {

    private static Repository repo;

    private ArrayList<ProductCategory> listOfCategory = new ArrayList<ProductCategory>();
    private ConcurrentHashMap<String, ArrayList<Product>> mapOfProductsInCategory = new ConcurrentHashMap<String, ArrayList<Product>>();
    private List<Product> listOfProductsInShoppingList = Collections.synchronizedList(new ArrayList<Product>());
    private List<Set<String>> listOfItemSetsForDataMining = new ArrayList<>();

    public static Repository getCenterRepository() {

        if (null == repo) {
            repo = new Repository();
        }
        return repo;
    }


    public List<Product> getListOfProductsInShoppingList() {
        return listOfProductsInShoppingList;
    }

    public void setListOfProductsInShoppingList(ArrayList<Product> getShoppingList) {
        this.listOfProductsInShoppingList = getShoppingList;
    }

    public Map<String, ArrayList<Product>> getMapOfProductsInCategory() {

        return mapOfProductsInCategory;
    }

    public void setMapOfProductsInCategory(ConcurrentHashMap<String, ArrayList<Product>> mapOfProductsInCategory) {
        this.mapOfProductsInCategory = mapOfProductsInCategory;
    }

    public ArrayList<ProductCategory> getListOfCategory() {

        return listOfCategory;
    }

    public void setListOfCategory(ArrayList<ProductCategory> listOfCategory) {
        this.listOfCategory = listOfCategory;
    }

    public List<Set<String>> getItemSetList() {

        return listOfItemSetsForDataMining;
    }

    public void addToItemSetList(HashSet list) {
        listOfItemSetsForDataMining.add(list);
    }

}
