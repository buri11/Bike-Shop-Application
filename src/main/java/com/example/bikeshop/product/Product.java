package com.example.bikeshop.product;

import javax.persistence.*;

/**
 * This class describes the objects contained in the table with the same name, Product
 */
@Entity
@Table
public class Product {
    /**
     *  Product ID
     */
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    /**
     * The values in the generated sequence
     */
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private long id;
    /**
     * Name of the product
     */
    private String name;
    /**
     * Brand of the product
     */
    private String brand;
    /**
     * Quantity of the product
     */
    private int quantity;
    /**
     * Price of this product
     */
    private float price;

    /**
     * Default constructor
     */
    public Product() {
    }

    /**
     * One of the constructors for Product, which also includes the id, for special cases
     * @param id ID of the product
     * @param name Explicit name of the product
     * @param brand The brand which manufactures this specific product
     * @param quantity The quantity of this product that the shop has in stock
     * @param price The price for one product of this kind
     */
    public Product(long id, String name, String brand, int quantity, float price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * This constructor is most often used, as it doesn't contain the id,
     * which is going to be assigned later by the database
     * @param name Explicit name of the product
     * @param brand The brand which manufactures this specific product
     * @param quantity The quantity of this product that the shop has in stock
     * @param price The price for one product of this kind
     */
    public Product(String name, String brand, int quantity, float price) {
        this.name = name;
        this.brand = brand;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the id of the product.
     * @return Id of the product (long)
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id for the product.
     * @param id New id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for the name of the product
     * @return Name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the product.
     * @param name New product name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the quantity
     * @return Quantity of the product
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity.
     * @param quantity New product quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter for the price of the product
     * @return Returns the price of this product
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets the new price.
     * @param price New value of the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Getter for the product brand.
     * @return The current brand name
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Setter for the brand
     * @param brand new brand name
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Converts the product into a String object
     * @return String equivalent of the product.
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}


