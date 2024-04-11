package org.example;

import java.io.*;
import java.net.Socket;
import java.util.Comparator;
import java.util.List;


public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<Hotel> hotels;

    public ClientHandler(Socket socket, List<Hotel> hotels) {
        this.clientSocket = socket;
        this.hotels = hotels;
    }

    @Override
    public void run() {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            out.println("Benvenuto! usa i Comandi attualmente disponibili per trovare l'hotel desiderato:");
            out.println("- all");
            out.println("- sorted_by_name");
            out.println("- with_spa");
            out.println("---------------------------");

            String command;
            while ((command = in.readLine()) != null) {
                switch (command) {
                    case "all":
                        sendAllHotels(out);
                        break;
                    case "sorted_by_name":
                        sendSortedByName(out);
                        break;
                    case "with_spa":
                        sendWithSpa(out);
                        break;
                    default:
                        out.println("Comando non riconosciuto.");
                }
            }
        } catch (IOException e) {
            System.out.println("Errore nella gestione del client: " + e.getMessage());
        }
    }

    private void sendAllHotels(PrintWriter out) {
        hotels.forEach(hotel -> out.println(String.format("-%-30s - Spa: %-10s", hotel.getName(), hotel.hasSpa() ? "Sì" : "No")));
        out.println("---------------------------");
    }

    private void sendSortedByName(PrintWriter out) {
        hotels.stream()
                .sorted(Comparator.comparing(Hotel::getName))
                .forEach(hotel -> out.println(String.format("-%-30s - Spa: %-10s", hotel.getName(), hotel.hasSpa() ? "Sì" : "No")));
        out.println("---------------------------");
    }
    private void sendWithSpa(PrintWriter out) {
        hotels.stream()
                .filter(Hotel::hasSpa)
                .forEach(hotel -> out.println(String.format("-%-30s - Spa: %-10s", hotel.getName(), hotel.hasSpa() ? "Sì" : "No")));
        out.println("---------------------------");
    }
}
