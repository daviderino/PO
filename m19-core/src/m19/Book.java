package m19;

import java.io.Serializable;

public class Book extends Work implements Serializable {
    private String _author;
    private char[] _ISBN = new char[10];

    public Book(int id, String title, String author, int price, Category category, String ISBN, int count) {
        super(id, title, price, category, count);
        _author = author;
        _ISBN = ISBN.toCharArray(); 
    }

    public String getType(){
        return "Livro";
    }
    
    @Override
    public String toString(){
        return getId() +  " - " + getAvailableCopies() + " de " + getCount() + " - " + getType().toString() + " - " +
            getTitle().toString() + " - " + getPrice() + " - " + getCategory().toString() + " - " + 
            _author.toString() + " - " + String.valueOf(_ISBN).toString();
    }
} 