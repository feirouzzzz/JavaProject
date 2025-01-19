package Presentation;

import entities.Client;
import service.ClientService;

public class App {
    public static void main(String[] args) {

        ClientService clientService = new ClientService();

        Client client = new Client(4, "nom", "prenom", "telephone", "email");
        Client client2 = new Client(5, "nom", "prenom", "telephone", "email");

        clientService.create(client2);

        Client client1 = new Client(4, "hhh", "jjj", "000258", "gyu@.com");

        // clientService.update(client1);

        // clientService.delete(client);

        System.out.println(clientService.findAll());
        System.out.println(clientService.findById(4));

    }
}
