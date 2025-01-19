package entities;

public class Chambre {
    private int id;
    private String telephone;
    private Categorie categorie;

    // Constructor
    public Chambre(int id, String telephone, Categorie categorie) {
        super();
        this.id = id;
        this.telephone = telephone;
        this.categorie = categorie;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    // toString() method
    @Override
    public String toString() {
        return "Chambre{" +
                "id=" + id +
                ", telephone='" + telephone + '\'' +
                ", categorie=" + (categorie != null ? categorie.toString() : "null") +
                '}';
    }
}
