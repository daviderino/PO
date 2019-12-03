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

    public Work(int id, String title, int price, Category category, int count) {
        _id = id;
        _count = count;
        _availableCopies = count;
        _title = title;
        _price = price;
        _category = category;
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

    public boolean isRequestable() {
        return _category.getIsRequestable();
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
        if(_count == 1) {
            return user.getRequestDay(0);
        }
        else if(_count > 1 && _count < 5) {
            return user.getRequestDay(1);
        }
        else {
            return user.getRequestDay(2);
        }
    }

    @Override
    public String toString() {
        return getId() +  " - " + getAvailableCopies() + " de " + getCount() + " - " + getType() + " - " +
                getTitle() + " - " + getPrice() + " - " + category() + " - " +
                getCreator() + " - " + getFormalIdentifier();
    }
}