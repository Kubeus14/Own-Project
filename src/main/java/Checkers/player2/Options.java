package Checkers.player2;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

//Klasa-"Opcje" jest odpowiedzialna za wyświetlanie okna Opcji oraz ich funkcje. Wykorzystane zostały w tej klasie zmienne z
//pakietu Swong:JLabel,JButton,JComboBox,JFrame. Metoda posiada konstruktor oraz metodę "actionPerformed" odpowiedzialną za prawidłowe
//działanie klikniętych przycisków i innych opcji po kliknięciu we właściwe miejsce.
public class Options extends JFrame implements ActionListener {

    //Zmienna long z unikalnym numerem dla serializacji
    private static final long serialVersionUID = 1L;
    //Zmienna JFrame nawiązuje do głównego okna aplikacji, używana dla zmiany koloru pionków i planszy w zakładce "opcje"
    protected static JFrame parent;
    //Zmienna JFrame określająca okno dla opcji gry
    protected JFrame window;
    //Zmienna JLabel jest w celu utworzenia napisu "Opcje Gry"
    protected JLabel inscriptionMain;
    //Zmienna JLabel jest w celu utworzenia napisu "Styl Aplikacji"
    protected JLabel inscriptionStyle;
    //Zmienna JLabel jest w celu utworzenia napisu "Wygląd planszy"
    protected JLabel inStyleChange;
    //Zmienna JLabel jest w celu utworzenia napisu "Wygląd pionków"
    protected JLabel inPawnsChange;
    //Zmienna JButton odpowiada za przycisk "Domyślny" w zakładce "Wygląd planszy", ustawia wybrany styl planszy
    protected JButton defaultStyle;
    //Zmienna JButton odpowiada za przycisk "Beżowo-Biała" w zakładce "Wygląd planszy", ustawia wybrany styl planszy
    protected JButton boardStyle1;
    //Zmienna JButton odpowiada za przycisk "Niebiesko-żółta" w zakładce "Wygląd planszy", ustawia wybrany styl planszy
    protected JButton boardStyle2;
    //Zmienna JButton odpowiada za przycisk "Czerwono-żółta" w zakładce "Wygląd pionkówy", ustawia wybrany styl pionków
    protected JButton pawnsStyle1;
    //Zmienna JButton odpowiada za przycisk "Zielono-szara" w zakładce "Wygląd pionków", ustawia wybrany styl pionków
    protected JButton pawnsStyle2;
    //Zmienna JButton odpowiada za przycisk "Domyślny" w zakładce "Wygląd pionków", ustawia wybrany styl pionków
    protected JButton defaultPawnsStyle;
    //Zmienna JComboBox odpowiada za możliwość wyboru ogólnego wyglądu gry-"zmiana koloru okienka"
    protected JComboBox<Object> style;
    //Konstruktor klasy Opcje są definiowane: rozmiar okna z opcjami gry oraz jego pozostałe właściwości.Określone są także
    //napisy dla określonych sekcji(JLabel), rozmiar,wymiary, font, kolor. Okreslona jest także lista, która umozliwia wybór
    //stylu aplikacji wraz z rozmiarem i stylem. Określone są także właściwości przycisków(JButton). Po okresleniu opcji dodano
    //je do opcji głównych za pomocą metody-JFrame#add(java.awt.Component).
    public Options(JFrame oknoF) {
        parent = oknoF;
        window = new JFrame();
        window.setTitle("Options");
        window.setSize(650, 300);
        window.setLocationRelativeTo(window);
        window.setLayout(null);
        window.setResizable(false);

        inscriptionMain = new JLabel("Game Options");
        inscriptionMain.setBounds(265, 10, 120, 25);
        inscriptionMain.setFont(new Font("Arial", Font.BOLD, 18));
        inscriptionMain.setForeground(Color.BLUE);
        window.add(inscriptionMain);

        inscriptionStyle = new JLabel("Aplication Style");
        inscriptionStyle.setBounds(10, 70, 100, 20);
        inscriptionStyle.setFont(new Font("Calibri", Font.BOLD, 14));
        inscriptionStyle.setForeground(Color.RED);
        window.add(inscriptionStyle);

        style = new JComboBox<Object>(new String[] { "Default", "Metalic", "Fitom", "Sumbin" });
        style.setBounds(225, 90, 200, 20);
        window.add(style);
        style.addActionListener(this);

        inStyleChange = new JLabel("Board Style");
        inStyleChange.setBounds(10, 130, 120, 20);
        inStyleChange.setFont(new Font("Calibri", Font.BOLD, 14));
        inStyleChange.setForeground(Color.RED);
        window.add(inStyleChange);

        defaultStyle = new JButton("Default");
        defaultStyle.setBounds(100, 160, 150, 20);
        window.add(defaultStyle);
        defaultStyle.addActionListener(this);

        boardStyle1 = new JButton("Blue-White");
        boardStyle1.setBounds(260, 160, 150, 20);
        window.add(boardStyle1);
        boardStyle1.addActionListener(this);

        boardStyle2 = new JButton("Purple-Orange");
        boardStyle2.setBounds(420, 160, 150, 20);
        window.add(boardStyle2);
        boardStyle2.addActionListener(this);

        inPawnsChange = new JLabel("Pawns Style");
        inPawnsChange.setBounds(10, 200, 120, 20);
        inPawnsChange.setFont(new Font("Calibri", Font.BOLD, 14));
        inPawnsChange.setForeground(Color.RED);
        window.add(inPawnsChange);

        defaultPawnsStyle = new JButton("Default");
        defaultPawnsStyle.setBounds(100, 230, 150, 20);
        window.add(defaultPawnsStyle);
        defaultPawnsStyle.addActionListener(this);

        pawnsStyle1 = new JButton("Red - Grey");
        pawnsStyle1.setBounds(260, 230, 150, 20);
        window.add(pawnsStyle1);
        pawnsStyle1.addActionListener(this);

        pawnsStyle2 = new JButton("Green - Grey");
        pawnsStyle2.setBounds(420, 230, 150, 20);
        window.add(pawnsStyle2);
        pawnsStyle2.addActionListener(this);

        window.setVisible(true);
    }

    //Konstruktor klasy Opcje są definiowane: rozmiar okna z opcjami gry oraz jego pozostałe właściwości.Określone są także
    //napisy dla określonych sekcji(JLabel), rozmiar,wymiary, font, kolor. Okreslona jest także lista, która umozliwia wybór
    //stylu aplikacji wraz z rozmiarem i stylem. Określone są także właściwości przycisków(JButton). Po okresleniu opcji dodano
    //je do opcji głównych za pomocą metody-JFrame#add(java.awt.Component).
    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == defaultStyle) {
            JOptionPane.showMessageDialog(null, "Changed!");
            Board.colorBoard1 = new Color(19, 194, 36);
            Board.colorBoard2 = new Color(194, 170, 12);
            parent.repaint();
        }
        if (source == boardStyle1) {
            JOptionPane.showMessageDialog(null, "Changed!");
            Board.colorBoard1 = new Color(61, 107, 222);
            Board.colorBoard2 = new Color(255, 255, 255);
            parent.repaint();
        }
        if (source == boardStyle2) {
            JOptionPane.showMessageDialog(null, "Changed!");
            Board.colorBoard1 = new Color(170, 42, 167);
            Board.colorBoard2 = new Color(227, 109, 17);
            parent.repaint();
        }
        if (source == defaultPawnsStyle) {
            JOptionPane.showMessageDialog(null, "Changed!");
            Board.pawnColor1 = Color.BLACK;
            Board.pawnColor2 = Color.RED;
            parent.repaint();
        }
        if (source == pawnsStyle1) {
            JOptionPane.showMessageDialog(null, "Changed!");
            Board.pawnColor1 = Color.YELLOW;
            Board.pawnColor2 = Color.GRAY;
            parent.repaint();
        }
        if (source == pawnsStyle2) {
            JOptionPane.showMessageDialog(null, "Changed!");
            Board.pawnColor1 = Color.GREEN;
            Board.pawnColor2 = Color.DARK_GRAY;
            parent.repaint();
        }

        if (source == style) {
            String s = (String) style.getSelectedItem();
            if (s == "Default") {
                parent.getContentPane().setBackground(null);
                parent.repaint();
            }
            if (s == "Metalic") {
                parent.getContentPane().setBackground(new Color(198, 49, 54));
                parent.repaint();
            }
            if (s == "Fitom") {
                parent.getContentPane().setBackground(new Color(146, 194, 28));
                parent.repaint();
            }
            if (s == "Sumbin") {
                parent.getContentPane().setBackground(new Color(49, 161, 180));
                parent.repaint();
            }
        }
    }

}




