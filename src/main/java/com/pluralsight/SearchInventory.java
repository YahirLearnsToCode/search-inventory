package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;

public class SearchInventory {

    public static void main(String[] args) {
        ArrayList<Product> products = new ArrayList<>();

        // Removing for Bonus 1
//        Product oreos = new Product(1234, "Oreos", 5.0);
//        Product laysChips = new Product(2345, "Lays Chips", 6.0);
//        Product cocaCola = new Product(4567, "Coca Cola", 3.0);
//        Product iPhone = new Product(5678, "IPhone 18 Pro", 1300.0);
//        Product paperTowel = new Product(6789, "Paper Towel", 3.0);
//
//        products.add(oreos);
//        products.add(laysChips);
//        products.add(cocaCola);
//        products.add(iPhone);
//        products.add(paperTowel);

        // BONUS 1:
        try {
            BufferedReader bufReader = new BufferedReader(new FileReader("src/main/resources/inventory.csv"));

            String line;
            while ((line = bufReader.readLine()) != null) {
                String[] lineSplit = line.split("\\|");

                int id = Integer.parseInt(lineSplit[0]);
                String name = lineSplit[1];
                double price = Double.parseDouble(lineSplit[2]);

                Product item = new Product(id, name, price);
                products.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        getInventory(products);
        createStoreInventoryCSV(products);
    }

    public static void getInventory(ArrayList<Product> products) {
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public static void createStoreInventoryCSV(ArrayList<Product> products) {
       try {
           BufferedWriter bufWriter = new BufferedWriter(new FileWriter("src/main/resources/storeinventory.csv"));
           // Comparator used for sorting,
           // this is purely for completing the exercise and learning one way of how to sort an array list
           // This sorting algorithm is not a demonstration of my knowledge, it is purley to test how it works
           products.sort(Comparator.comparing(Product::getName));

           for (Product p : products){
               bufWriter.write(p.toString() + "\n");

           }
           bufWriter.close();

       } catch (IOException e) {
           e.printStackTrace();
       }
    }
}
