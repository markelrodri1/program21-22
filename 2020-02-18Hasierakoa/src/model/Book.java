package model;

import java.time.LocalDate;

/*
 * The Book class models a book with one (and only one) author.
 */
public class Book implements Argitagarria {
    // The private instance variables

    private String name;
    private Author author;
    private double price;
    private int pages;
    private 
    private static int argitaratutakoLiburuak = 0;

    // Constructors
    public Book() {

    }

    public Book(String name, Author author, double price, int pages) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.pages = pages;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public Author getAuthor() {
        return author;  // return member author, which is an instance of the class Author
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
    public String getNolakoa(){
        if (pages < 100){
            return "txikia";
        }
        else if(pages > 100 && pages < 400){
            return "normala";
        }
        else{
            return "handia";
        }
        
    }

    public static int getArgitaratutakoLiburuak() {
        return argitaratutakoLiburuak;
    }

    public static void setArgitaratutakoLiburuak(int argitaratutakoLiburuak) {
        Book.argitaratutakoLiburuak = argitaratutakoLiburuak;
    }
    
    @Override
    public String toString() {
        return name + ", " + author.getName() + ", " + price + ", " + pages;  
    }

    @Override
    public void argitaratu() {
        System.out.println("Eibarren argitaratua " + LocalDate.now() + "egunean");
        argitaratutakoLiburuak++;
    }
}