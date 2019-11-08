package m19;

import java.io.Serializable;

public class DVD extends Work implements Serializable{
    private static final long serialVersionUID = 1L;

    private String _director;
    private char[] _IGAC = new char[10];

    public DVD(int id, int count, String title, int price, Category category,  String director, String IGAC) {
        super(id, count, title, price, category);
        _director = director;
        _IGAC = IGAC.toCharArray(); 
    }

    public String getType(){
        return "DVD";
    }
    
    @Override
    public String toString(){
        return getId() +  " - " + getAvailableCopies() + " de " + getCount() + " - " + getType().toString() + " - " +
            getTitle().toString() + " - " + getPrice() + " - " + getCategory().toString() + " - " + 
            _director.toString() + " - " + String.valueOf(_IGAC).toString();
    }
}