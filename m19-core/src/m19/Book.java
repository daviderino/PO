package m19;

import java.io.Serializable;

public class Book extends Work implements Serializable {

    private static final long serialVersionUID = 1L;

    private String _author;
    private char[] _ISBN = new char[10];

    public Book(int id, int count, String title, int price, Category category, String author, String ISBN) {
        super(id, count, title, price, category);
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