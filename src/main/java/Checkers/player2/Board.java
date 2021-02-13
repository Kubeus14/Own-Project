package Checkers.player2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//Klasa plansza zawiera określone definicje i opcje dla wszystkich elementów głównego okna aplikacji.
//Zdefiniowane zostało działanie przycisków.Określone w klasie są także zmienne do połączenia internetowego z drugim graczem.
//W klasie zaimplementowany jest interfejs "ActionListener". Służy on do określenia akcji po wciśnięciu określonego przycisku.
//Drugi zaimplementowany interfejs to "Serializable". Jest to w celu serializacji obiektu wysyłanego przec połączenie sieciowe.
//W klasie, która tworzy główne okno aplikacji klasa Plansza dziedziczy po JFrame.
public class Board extends JFrame implements ActionListener, Serializable {

    //Zmienna long w celu serializacji z określonym unikalnym numerem.
    private static final long serialVersionUID = 1L;
    //Dwuwymiarowa tablica, która przechowuje współrzędne pionków na szachownicy.
    protected static int pawnsBoard[][];
    //Szerokość okna głównego aplikacji
    private Integer szer = 650;
    //Wysokość okna głównego aplikacji
    private Integer wys = 600;
    //Zmienna JButton odpowiada za przycisk Start. Uruchamia to rzogrywkę lokalną bez połączenia z siecią. gra odbywa się na 1 komputerze
    private JButton start;
    //Zmienna JButton odpowiada za przycisk Wyjście, jest to po prostu wyjście z aplikacji.
    private JButton exit;
    //Zmienna JButton, uruchamia opcje gry po kliknięciue w przycisk Opcje
    private JButton options;
    //Zmienna JButton dla przycisku MultiPlayer. Po aktywacji szukany jest dostępny serwer. Jesli jest powodzenie to odczytywany
    //jest odbierany obiekt-tablica z pionkami. Jeśli jest niepowodzenie to wyświetla się informacja o niepowodzeniu w połączeniu sieciowym
    private JButton multiplayer;
    //Zmienna JLabel do tworzenia apisu "Warcaby"
    private JLabel mainInscription;

    private JLabel nameGame;
    //Obiekt klasy Plansza
    private Board plansza;
    //Tablica obiektów klasy Pionek
    private Pawn[][] pionki;
    //Zmienna do określenia koloru planszy
    protected static Color colorBoard1;
    //Zmienna do określenia koloru planszy
    protected static Color colorBoard2;
    //Zmienna do określenia koloru pionków
    protected static Color pawnColor1;
    //Zmienna do określenia koloru pionków
    protected static Color pawnColor2;
    //Zmienna ServerSocket służy do określenia powiązania z podanym portem
    protected ServerSocket serverSocket;
    //Zmienna ServerSocket służy do określenia powiązania z podanym portem
    protected Socket socket;
    //Zmienna, która tworzy strumień odczytujący dane z gniazda sieciowego
    protected static ObjectInputStream ois;
    //Zmienna, która tworzy strumień zapisujący dane do gniazda sieciowego
    protected static ObjectOutputStream oos;
    //Zmienna okresla czy użytkownik zdecydował się na tryb Multiplayer przez sieć
    protected static boolean multi;
    //Zmienna logiczna która wynosi true gdy pionki zostaną ustawione
    protected static boolean game;

    //Konstruktor klasy, który inicjalizuje elementy z biblioteki Swing,tablic,zmiennych. Komponenty, które są definiowane to:
    //JFrame,JLabel,JButton. Ustawiane są dla nich fonty,kolory,wymiary. Dodane są do opcji głównych za pomocą metody-
    //JFrame#add(java.awt.Component). Zmienne typu Color definiują kolor pionków i planszy.
    public Board() {
        super("Checkers");
        pawnsBoard = new int[8][8];
        pionki = new Pawn[8][8];
        multi = false;
        game = false;

        setSize(szer, wys);
        setLocationRelativeTo(plansza);
        setLayout(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainInscription = new JLabel("CHECKERS");
        mainInscription.setBounds(535, 15, 120, 50);
        mainInscription.setFont(new Font("Arial", Font.ROMAN_BASELINE, 16));
        mainInscription.setForeground(Color.GREEN);
        add(mainInscription);

        nameGame = new JLabel("PLAYER");
        nameGame.setBounds(555, 55, 160, 40);
        nameGame.setFont(new Font("Calibri", Font.CENTER_BASELINE, 20));
        nameGame.setForeground(Color.BLACK);
        add(nameGame);

        start = new JButton("Start");
        start.setBounds(530, 100, 70, 20);
        start.setFont(new Font("Calibri", Font.BOLD, 13));
        add(start);
        start.addActionListener(this);

        multiplayer = new JButton("Multiplayer");
        multiplayer.setBounds(530, 130, 100, 20);
        multiplayer.setFont(new Font("Calibri", Font.BOLD, 13));
        add(multiplayer);
        multiplayer.addActionListener(this);

        options = new JButton("Options");
        options.setBounds(530, 160, 100, 20);
        options.setFont(new Font("Calibri", Font.BOLD, 13));
        add(options);
        options.addActionListener(this);

        exit = new JButton("Exit");
        exit.setBounds(530, 190, 70, 20);
        exit.setFont(new Font("Calibri", Font.BOLD, 13));
        add(exit);
        exit.addActionListener(this);

        colorBoard1 = new Color(19, 194, 36);
        colorBoard2 = new Color(194, 170, 12);
        pawnColor1 = Color.BLACK;
        pawnColor2 = Color.RED;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                pionki[i][j] = new Pawn(this, i, j);
                add(pionki[i][j]);
            }
        }
        setVisible(true);

    }

    //Metoda "ustaw" odpowiada za ustawienie pionków na planszy.
    protected void ustaw() {
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {
                pawnsBoard[i][j] = 0;
            }
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 8; i++) {
                if ((i + j) % 2 == 0)
                    pawnsBoard[i][j] = 1;
            }

        for (int j = 5; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                if ((i + j) % 2 == 0)
                    pawnsBoard[i][j] = 2;
            }
        }

        game = true;
    }

    //Metoda "paint" służy do rysowania pionków i szachownicy. Zdefiniowano w tej metodzie także kontury aktywne pionków.
    //czyli pola aktywne dla ruchu pionka. W metodzie jest także okno informacyjne ze współrzędnymi, które są pokazywane po najechaniu
    //kursorem myszy. Rysowanie jest realizowane za pomocą komponentu Graphics 2D
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paint(g);
        g2d.setColor(Color.BLACK);
        g2d.fillRect(10, 40, 505, 505);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                    g2d.setColor(colorBoard1);
                else
                    g2d.setColor(colorBoard2);
                g2d.fillRect(11 + 63 * i, 41 + 63 * j, 62, 62);
            }
        }
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {
                if (pawnsBoard[i][j] == 3 || pawnsBoard[i][j] == 4 || pawnsBoard[i][j] == 5
                        || pawnsBoard[i][j] == 8 || pawnsBoard[i][j] == 9) {
                    g2d.setColor(Color.YELLOW);
                    g2d.drawRect(pionki[i][j].x + 3, pionki[i][j].y + 25, pionki[i][j].width, pionki[i][j].height);
                }
            }
        for (int j = 0; j < 8; j++)
            for (int i = 0; i < 8; i++) {
                if (pawnsBoard[i][j] == 1 || pawnsBoard[i][j] == 3 || pawnsBoard[i][j] == 7
                        || pawnsBoard[i][j] == 9)
                    g2d.setColor(pawnColor1);
                else if (pawnsBoard[i][j] == 2 || pawnsBoard[i][j] == 4 || pawnsBoard[i][j] == 6
                        || pawnsBoard[i][j] == 8)
                    g2d.setColor(pawnColor2);

                if (pawnsBoard[i][j] == 1 || pawnsBoard[i][j] == 2 || pawnsBoard[i][j] == 3
                        || pawnsBoard[i][j] == 4)
                    g2d.fillOval(17 + 63 * i, 47 + 63 * j, 50, 50);
                if (pawnsBoard[i][j] == 6 || pawnsBoard[i][j] == 7 || pawnsBoard[i][j] == 8
                        || pawnsBoard[i][j] == 9)
                    g2d.fillRect(17 + 63 * i, 47 + 63 * j, 50, 50);
            }

        g2d.setColor(Color.BLACK);
        g2d.drawRect(550, 493, 50, 50);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString("POLE", 560, 515);
        g2d.drawString(Pawn.tmp, 570, 535);

    }

    //Metoda "actionPerformed" określa zachowanie aplikacji po naciśnięciu na dany komponent.
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == start) {
            ustaw();
            repaint();
        }

        if (source == multiplayer) {
            try {
                socket = new Socket("localhost", 5555);
                JOptionPane.showMessageDialog(null, "Connected with server");
                oos = new ObjectOutputStream(socket.getOutputStream());
                ois = new ObjectInputStream(socket.getInputStream());
                multi = true;
                ustaw();
                repaint();
                new Thread() {
                    public void run() {
                        while (true) {
                            try {
                                int tmp[][] = (int[][]) ois.readObject();
                                if (!tmp.equals(pawnsBoard)) {
                                    System.out.println("Changed");
                                    pawnsBoard = tmp;
                                    repaint();
                                }
                            } catch (ClassNotFoundException | IOException e) {
                            }
                        }
                    }
                }.start();
            } catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "No connection");
            }
        }

        if (source == options) {
            new Options(this);
        }

        if (source == exit) {
            int decyzja = JOptionPane.showConfirmDialog(null, "Do you really want to Exit?", "Confirm Dialog",
                    JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);
            if (decyzja == JOptionPane.YES_OPTION) {
                dispose();
                try {
                    ois.close();
                    oos.close();
                    socket.close();
                    serverSocket.close();
                } catch (IOException e1) {

                }

            }
        }
    }
}
