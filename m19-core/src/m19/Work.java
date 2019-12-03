package m19;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Work implements Serializable {

    private static final long serialVersionUID = 201901101348L;

    private int _id;
    private int _count;
    private String _title;
    private int _price;
    private int _availableCopies;
    private Category _category;
    private HashMap<String, Integer> _requestDays;
    //private int[] _requestDays; // [normal, complaint, noncompliant]

    public Work(int id, String title, int price, Category category, int count) {
        _id = id;
        _count = count;
        _availableCopies = count;
        _title = title;
        _price = price;
        _category = category;

        _requestDays = new HashMap<String, Integer>();

        // this is bad
        if(count == 1) {
            _requestDays.put("NORMAL", 3);
            _requestDays.put("CUMPRIDOR", 8);
            _requestDays.put("FALTOSO", 2);
        }
        else if(count > 1 && count <= 5) {
            _requestDays.put("NORMAL", 8);
            _requestDays.put("CUMPRIDOR", 15);
            _requestDays.put("FALTOSO", 2);
        }
        else {
            _requestDays.put("NORMAL", 15);
            _requestDays.put("CUMPRIDOR", 30);
            _requestDays.put("FALTOSO", 2);
        }
    }

    public String getTitle() {
        return _title;
    }

    public int getPrice() {
        return _price;
    }

    public int getAvailableCopies() {
        return _availableCopies;
    }

    public int getCount() {
        return _count;
    }

    public int getId() {
        return _id;
    }

    public Category getCategory(){
        return _category;
    }

    public String category() {
        return _category.getName();
    }

    public abstract String getCreator();
    public abstract String getFormalIdentifier();
    public abstract String getType();

    public void incrementCount() {
        _availableCopies++;
    } 

    public void decrementCount() {
        _availableCopies--;
    }

    public int computeReturnDate(User user) {
        return _requestDays.get(user.getBehaviour());
    }

    @Override
    public String toString() {
        return getId() +  " - " + getAvailableCopies() + " de " + getCount() + " - " + getType() + " - " +
                getTitle() + " - " + getPrice() + " - " + category() + " - " +
                getCreator() + " - " + getFormalIdentifier();
    }
}