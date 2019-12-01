package m19;

import java.io.Serializable;

public class DVD extends Work implements Serializable{
    private String _director;
    private char[] _IGAC = new char[10];

    public DVD(int id, String title, String director, int price, Category category, String IGAC, int count) {
        super(id, title, price, category, count);
        _director = director;
        _IGAC = IGAC.toCharArray(); 
    }

    @Override
    public String getCreator() {
        return _director;
    }

    @Override
    public String getFormalIdentifier() {
        return String.valueOf(_IGAC);
    }

    @Override
    public String getType(){
        return "DVD";
    }
    
    @Override
    public String toString(){
        return getId() +  " - " + getAvailableCopies() + " de " + getCount() + " - " + getType() + " - " +
            getTitle() + " - " + getPrice() + " - " + getCategory() + " - " + 
            _director + " - " + String.valueOf(_IGAC);
    }
}