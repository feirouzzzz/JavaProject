package entities;

import java.util.Date;

public class Reservation {
    private Date datedebut;
    private Date datefin;
    private Client client;
    private Chambre chambre;

    // Constructor
    public Reservation(Date datedebut, Date datefin, Client client, Chambre chambre) {
        super();
        this.datedebut = datedebut;
        this.datefin = datefin;
        this.client = client;
        this.chambre = chambre;
    }

    // Getters and Setters
    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Chambre getChambre() {
        return chambre;
    }

    public void setChambre(Chambre chambre) {
        this.chambre = chambre;
    }

    public Date getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(Date datedebut) {
        this.datedebut = datedebut;
    }

    public Date getDatefin() {
        return datefin;
    }

    public void setDatefin(Date datefin) {
        this.datefin = datefin;
    }

    /**
     * Returns a string representation of the Reservation object,
     * including the details of the client, the room, and the reservation dates.
     */
    @Override
    public String toString() {
        return "Reservation{" +
                "datedebut=" + datedebut +
                ", datefin=" + datefin +
                ", client=" + (client != null ? client.toString() : "null") +
                ", chambre=" + (chambre != null ? chambre.toString() : "null") +
                '}';
    }
}
