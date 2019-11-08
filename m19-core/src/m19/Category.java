package m19;

public enum Category {
    FICTION("Ficção"),
    SCITECH("Técnica e Científica"),
    REFERENCE("Referência");

    private String _name;

    Category (String name){
        this._name = name;
    }

    public String getName() {
        return _name;
    }
}