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

    @Override
    public String getCreator() {
        return _author;
    }

    @Override
    public String getFormalIdentifier() {
        return String.valueOf(_ISBN);
    }

    @Override
    public String getType(){
        return "Livro";
    }
} 
