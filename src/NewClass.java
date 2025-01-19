
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import service.*;
import entities.Chambre;
import java.util.Date;
import java.util.List;

/**
 * 
 *
 * @author lenovo
 */
public class NewClass {
  public static void main(String[] args) {
    System.out.println("test");
    ClientService cs = new ClientService();
    /*
     * cs.create(new Client(1, "salim", "alomp", "0252598525", "gfgyt@gmail.com"));
     * cs.create(new Client(2, "ahmed", "rokl", "49899879", "pppp@gmail.com"));
     * cs.create(new Client(3, "hatim", "pmlj", "945454", "mmmm@gmail.com"));
     * cs.create(new Client(4, "kareem", "tcrt", "2452656545", "ggggdd@gmail.com"));
     * cs.create(new Client(5, "fath", "redu", "558525", "hbbbb@gmail.com"));
     * cs.create(new Client(6, "ismail", "cdtr", "1424558", "wwww@gmail.com"));
     * cs.create(new Client(7, "safwan", "ytftfu", "01505455", "ssss@gmail.com"));
     * cs.create(new Client(8, "othmane", "trdy(", "48485698", "aaaa@gmail.com"));
     */

    CategorieService cats = new CategorieService();
    /*
     * cats.create(new Categorie(1,"code1", "simple1"));
     * cats.create(new Categorie(2,"code2", "simple2"));
     * cats.create(new Categorie(3,"code3", "simple3"));
     * cats.create(new Categorie(4,"code4", "simple4"));
     */

    ChambreService chs = new ChambreService();

    /*
     * chs.create(new Chambre(1,"0745878521", cats.findById(1))) ;
     * chs.create(new Chambre(2,"0745878521", cats.findById(2))) ;
     * chs.create(new Chambre(3,"0745878521", cats.findById(3))) ;
     * chs.create(new Chambre(4,"0745878521", cats.findById(4))) ;
     * chs.create(new Chambre(5,"0745878521", cats.findById(1))) ;
     * chs.create(new Chambre(6,"0745878521", cats.findById(2))) ;
     */

    ReservationService reserv = new ReservationService();
    /*
     * reserv.create(new Reservation ( new Date("2022/01/01"), new
     * Date("2022/01/10"), cs.findById(40), chs.findById(1))) ;
     * reserv.create(new Reservation ( new Date("2022/01/11"), new
     * Date("2022/01/20"),cs.findById(41), chs.findById(2)));
     * reserv.create(new Reservation ( new Date("2022/01/21"), new
     * Date("2022/01/30") ,cs.findById(42), chs.findById(3)));
     * reserv.create(new Reservation ( new Date("2022/01/21"), new
     * Date("2022/01/30") ,cs.findById(43), chs.findById(4)));
     * reserv.create(new Reservation ( new Date("2022/01/21"), new
     * Date("2022/01/30") ,cs.findById(44), chs.findById(5)));
     * reserv.create(new Reservation ( new Date("2022/01/21"), new
     * Date("2022/01/30") ,cs.findById(45), chs.findById(6)));
     * reserv.create(new Reservation ( new Date("2022/02/10"), new
     * Date("2022/02/20") ,cs.findById(46), chs.findById(4)));
     * reserv.create(new Reservation ( new Date("2022/02/21"), new
     * Date("2022/02/30") ,cs.findById(47), chs.findById(5)));
     */

    List<Chambre> chambresClients = reserv.findChambreBetweenDates(cs.findById(40),
        new java.sql.Date(new Date("2022/01/01").getTime()), new java.sql.Date(new Date("2025/01/14").getTime()));

    System.out.println("\n --> Chambres Reserves par " + cs.findById(40).getPrenom());
    for (Chambre chambre : chambresClients) {
      System.out.println(chambre);
    }

  }

}
