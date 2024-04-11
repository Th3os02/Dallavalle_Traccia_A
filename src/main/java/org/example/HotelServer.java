package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class HotelServer {
    int PORT = 1234;
    private List<Hotel> hotels = new ArrayList<>();

    public static void main(String[] args) {
        new HotelServer().startServer();
    }

    public HotelServer() {
        hotels.add(new Hotel("Summer Grotto Hotel", true));
        hotels.add(new Hotel("Silver Brewery Resort", false));
        hotels.add(new Hotel("Rainbow's End Hotel", true));
        hotels.add(new Hotel("Silver Lake Lodge", true));
        hotels.add(new Hotel("Crystal Peak Resort & Spa", true));
        hotels.add(new Hotel("Northern Isle Hotel", false));
        hotels.add(new Hotel("Sapphire Springs Hotel", false));
        hotels.add(new Hotel("Mystic River Lodge", true));
        hotels.add(new Hotel("Exalted Emperor Resort", false));
        hotels.add(new Hotel("Ruby Horizon Hotel", false));
    }

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server avviato sulla porta " + PORT);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client Connesso!\n" + clientSocket);
                new Thread(new ClientHandler(clientSocket, hotels)).start();
            }
        } catch (IOException e) {
            System.out.println("Errore durante l'avvio del server: " + e.getMessage());
        }
    }
}
