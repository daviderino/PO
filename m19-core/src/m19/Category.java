package m19;

import java.io.Serializable;

public enum Category implements Serializable {
    FICTION("Ficção", true),
    SCITECH("Técnica e Científica", true),
    REFERENCE("Referência", false);

    private String _name;
    private boolean _isRequestable;

    Category(String name, boolean isRequestable){
        this._name = name;
        this._isRequestable = isRequestable;
    }

    public String getName() {
        return _name;
    }

    public boolean getIsRequestable() {
        return _isRequestable;
    }
}