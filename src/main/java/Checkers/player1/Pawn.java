package Checkers.player1;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

//Klasa pionek składa się ze zmiennych i metod, które mają za zadanie obsługę ruchu pionków po planszy a także
//sposób zdobywania punktów tzn.bicia. Zmienne i metody dotyczą jednego pionka który jest na planszy oraz określa jego
//ruchy oraz to jak może zbijać inne. W tej klasie jest także zaprogramowane okno w prawym dolnym rogu gdzie jest informacja
//o tym na jakim polu na planszy znajduje się kursor. Zaprogramowane są także opcje gry z królówkami, które występują w grze Warcaby.
//Okno to zostało utworzone za pomocą klasy(dziedziczy z klasy) - javax.swing.JComponent. W celu określenia funkcji(ruchów), które wybieramy
//za pomocą myszki został dodany interfejs java.awt.eventMouseListener.
public class Pawn extends JComponent implements MouseListener {

    //zmienna long z unikalnym numerem w celu serializacji
    private static final long serialVersionUID = 1L;
    //zmienna współrzędnej x dla pojedyńczego pionka
    protected Integer x;
    //zmienna współrzędnej y dla pojedyńczego pionka
    protected Integer y;
    //Szerokość pojedyńczego pionka na szachownicy
    protected Integer width = 50;
    //Wysokość pojedyńczego pionka na szachownicy
    protected Integer height = 50;
    //Zmienna, która definiuje współrzedną x klikniętego pionka
    protected int _i;
    //Zmienna, która definiuje współrzedną y klikniętego pionka
    protected int _j;
    //Obiekt z klasy StringBuilder, który ma wyświetlić współrzędne na oknie informacyjnym
    protected StringBuilder name = new StringBuilder();
    //Zmienna String która rozpoczyna wyświetlenie początkowej wartości przez kafelek informacyjny
    protected static String tmp = "A1";
    //Zmienna z klasy JFrame, która odwołuje się do okna głównej aplikacji
    protected static JFrame parent;
    //Współrzędna x pionka, na którym może być "bicie pionka"
    protected static int zbijanie_i;
    //Współrzędna y pionka, na którym może być "bicie pionka"
    protected static int zbijanieJot;

    //Konstruktor klasy PionekGracz1 ma za zadanie inicjalizację zmiennej typu JFrame(odwołanie do okna głównego aplikacji i zmiennych
    //określających współrzędne). Inicjalizuje także interfejs-java.awt.event.MouseListener, przez metodę java.awt.Component#addMouseListener.
    //Użyta jest także metoda "ustawNazwe", ustawia ona napis dla aktualnej pozycji kursora mysszy na planszy wyświetlanej w okienku informacji.
    public Pawn(JFrame f, int i, int j) {
        parent = f;
        x = 8 + 63 * i;
        y = 15 + 63 * j;
        _i = i;
        _j = j;
        setBounds(x, y, width, height);
        setTheName(i, j);
        addMouseListener(this);
        setLayout(null);
        setDoubleBuffered(false);
        setVisible(true);
    }

    //"ustawNazwe"-ta metoda ma poprawnie wyświetlić informacje w oknie informacji o współrzędnych. Ma 2 wartości jako parametr
    //do wywołania. Informacja, która jest wyświetlana zalezy od dodania do zmiennej String builder nowych dopisków poprzez metode-"append()"
    void setTheName(int i, int j) {
        String tmp = null;
        if (i == 0) {
            tmp = "A";
        }
        if (i == 1) {
            tmp = "B";
        }
        if (i == 2) {
            tmp = "C";
        }
        if (i == 3) {
            tmp = "D";
        }
        if (i == 4) {
            tmp = "E";
        }
        if (i == 5) {
            tmp = "F";
        }
        if (i == 6) {
            tmp = "G";
        }
        if (i == 7) {
            tmp = "H";
        }
        name.append(tmp);
        name.append(j + 1);
    }

    //Metoda "toString" ma za zadanie czytelne wyświetlenie informacji w okienku informacyjnym o współrzędnych.
    @Override
    public String toString() {
        return name + "";
    }
    //Metoda z interfejsu MouseListener. Służy do określenia ruchów kursora myszy w aplikacji.Dokładniej wyjaśniając gdy
    //gdy mysz najeżdża na pole szachownicy to wyświetla współrzędne pola w oknie informacyjnym
    @Override
    public void mouseEntered(MouseEvent e) {
        Object source = e.getSource();
        tmp = source.toString();
        parent.repaint(560, 515, 25, 25);
    }
    //Metoda MouseClicked okresla funkcje aplikacji po kliknięciu myszka na dane pole lub opcję. Pochodzi z interfejsu Mouselistener
    //Metoda odpowiada za ruchy pionków i zbijanie. Oznaczeniu algorytmu ruchu oraz zbijania:
    //0-pole puste
    //1-pionek czarny
    //2-pionek biały
    //3-pionek czarny wybrany przez gracza
    //4-pionek biały wybrany przez gracza
    //5-pole puste gdzie można wykonać ruch pionka
    //6-biała królówka
    //7-czarna królówka
    //8-wybrana przez gracza biała królówka
    //9-wybrana przez gracza czarna królówka
    //W metodzie jest także obiekt tablicy pionków, który poprzez sieć jest wysłany do drugiego gracza
    //Jest w metodzie także informacja o zakończeniu gry wyświetlana za pomocą komunikatu
    @Override
    public void mouseClicked(MouseEvent e) {
        if (Board.pawnsBoard[_i][_j] != 5) {
            for (int j = 0; j < 8; j++)
                for (int i = 0; i < 8; i++) {
                    if (Board.pawnsBoard[i][j] == 3 || Board.pawnsBoard[i][j] == 4
                            || Board.pawnsBoard[i][j] == 8 || Board.pawnsBoard[i][j] == 9) {
                        Board.pawnsBoard[i][j] -= 2;
                    }
                    if (Board.pawnsBoard[i][j] == 5) {
                        Board.pawnsBoard[i][j] = 0;
                    }
                }


            if (Board.pawnsBoard[_i][_j] == 1 || Board.pawnsBoard[_i][_j] == 2
                    || Board.pawnsBoard[_i][_j] == 6 || Board.pawnsBoard[_i][_j] == 7)
            {
                zbijanie_i = -2;
                zbijanieJot = -2;
                Board.pawnsBoard[_i][_j] += 2;

                if (Board.pawnsBoard[_i][_j] == 8 || Board.pawnsBoard[_i][_j] == 9)
                {
                    for (int i = 0; i < 4; i++) {
                        int tmpX = _i;
                        int tmpY = _j;
                        while (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8) {
                            if (i == 0) {
                                tmpX--;
                                tmpY--;
                            } else if (i == 1) {
                                tmpX--;
                                tmpY++;
                            } else if (i == 2) {
                                tmpX++;
                                tmpY++;
                            } else if (i == 3) {
                                tmpX++;
                                tmpY--;
                            }
                            if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
                                if (Board.pawnsBoard[tmpX][tmpY] == 0) {
                                    Board.pawnsBoard[tmpX][tmpY] = 5;
                                } else {
                                    if (((Board.pawnsBoard[tmpX][tmpY] == 1
                                            || Board.pawnsBoard[tmpX][tmpY] == 7)
                                            && Board.pawnsBoard[_i][_j] == 8)
                                            || (Board.pawnsBoard[tmpX][tmpY] == 2
                                            || Board.pawnsBoard[tmpX][tmpY] == 6)
                                            && Board.pawnsBoard[_i][_j] == 9) {
                                        if (i == 0) {
                                            tmpX--;
                                            tmpY--;
                                        } else if (i == 1) {
                                            tmpX--;
                                            tmpY++;
                                        } else if (i == 2) {
                                            tmpX++;
                                            tmpY++;
                                        } else if (i == 3) {
                                            tmpX++;
                                            tmpY--;
                                        }
                                        if (tmpX != -1 && tmpX != 8 && tmpY != -1 && tmpY != 8)
                                            if (Board.pawnsBoard[tmpX][tmpY] == 0) {
                                                Board.pawnsBoard[tmpX][tmpY] = 5;
                                                if (i == 0) {
                                                    tmpX++;
                                                    tmpY++;
                                                } else if (i == 1) {
                                                    tmpX++;
                                                    tmpY--;
                                                } else if (i == 2) {
                                                    tmpX--;
                                                    tmpY--;
                                                } else if (i == 3) {
                                                    tmpX--;
                                                    tmpY++;
                                                }
                                                zbijanie_i = tmpX;
                                                zbijanieJot = tmpY;
                                            }
                                    }
                                    break;
                                }
                        }
                    }
                }

                if (Board.pawnsBoard[_i][_j] == 4)
                {
                    if (_i != 0 && _i != 7) {
                        if (Board.pawnsBoard[_i - 1][_j - 1] == 0) {
                            Board.pawnsBoard[_i - 1][_j - 1] = 5;
                        }
                        if (Board.pawnsBoard[_i + 1][_j - 1] == 0) {
                            Board.pawnsBoard[_i + 1][_j - 1] = 5;
                        }

                    } else if (_i == 0) {
                        if (Board.pawnsBoard[_i + 1][_j - 1] == 0)
                            Board.pawnsBoard [_i + 1][_j - 1] = 5;
                    } else if (_i == 7) {
                        if (Board.pawnsBoard[_i - 1][_j - 1] == 0)
                            Board.pawnsBoard[_i - 1][_j - 1] = 5;
                    }
                    // *****************************************************
                    if (_j != 1)
                        if (_i != 0 && _i != 1 && _i != 6 && _i != 7) {
                            if (Board.pawnsBoard[_i - 2][_j - 2] == 0
                                    && (Board.pawnsBoard[_i - 1][_j - 1] == 1
                                    || Board.pawnsBoard[_i - 1][_j - 1] == 7)) {
                                Board.pawnsBoard[_i - 2][_j - 2] = 5;
                                zbijanie_i = _i - 1;
                                zbijanieJot = _j - 1;
                            }
                            if (Board.pawnsBoard[_i + 2][_j - 2] == 0
                                    && (Board.pawnsBoard[_i + 1][_j - 1] == 1
                                    || Board.pawnsBoard[_i - 1][_j - 1] == 7)) {
                                Board.pawnsBoard[_i + 2][_j - 2] = 5;
                                zbijanie_i = _i + 1;
                                zbijanieJot = _j - 1;
                            }
                        } else if (_i == 1 || _i == 0) {
                            if (Board.pawnsBoard[_i + 2][_j - 2] == 0
                                    && (Board.pawnsBoard[_i + 1][_j - 1] == 1
                                    || Board.pawnsBoard[_i + 1][_j - 1] == 7)) {
                                Board.pawnsBoard[_i + 2][_j - 2] = 5;
                                zbijanie_i = _i + 1;
                                zbijanieJot = _j - 1;
                            }
                        } else if (_i == 6 || _i == 7) {
                            if (Board.pawnsBoard[_i - 2][_j - 2] == 0
                                    && (Board.pawnsBoard[_i - 1][_j - 1] == 1
                                    || Board.pawnsBoard[_i - 1][_j - 1] == 7)) {
                                Board.pawnsBoard[_i - 2][_j - 2] = 5;
                                zbijanie_i = _i - 1;
                                zbijanieJot = _j - 1;
                            }
                        }
                }

                if (Board.pawnsBoard[_i][_j] == 3) {
                    zbijanie_i = -2;
                    zbijanieJot = -2;
                    if (_i != 0 && _i != 7) {
                        if (Board.pawnsBoard[_i - 1][_j + 1] == 0) {
                            Board.pawnsBoard[_i - 1][_j + 1] = 5;
                        }
                        if (Board.pawnsBoard[_i + 1][_j + 1] == 0) {
                            Board.pawnsBoard[_i + 1][_j + 1] = 5;
                        }
                    } else if (_i == 0) {
                        if (Board.pawnsBoard[_i + 1][_j + 1] == 0)
                            Board.pawnsBoard[_i + 1][_j + 1] = 5;
                    } else if (_i == 7) {
                        if (Board.pawnsBoard[_i - 1][_j + 1] == 0)
                            Board.pawnsBoard[_i - 1][_j + 1] = 5;
                    }

                    if (_j != 6) {
                        if (_i != 0 && _i != 1 && _i != 6 && _i != 7) {

                            if (Board.pawnsBoard[_i - 2][_j + 2] == 0
                                    && (Board.pawnsBoard[_i - 1][_j + 1] == 2
                                    || Board.pawnsBoard[_i - 1][_j + 1] == 6)) {
                                Board.pawnsBoard[_i - 2][_j + 2] = 5;
                                zbijanie_i = _i - 1;
                                zbijanieJot = _j + 1;
                            }
                            if (Board.pawnsBoard[_i + 2][_j + 2] == 0
                                    && (Board.pawnsBoard[_i + 1][_j + 1] == 2
                                    || Board.pawnsBoard[_i + 1][_j + 1] == 6)) {
                                Board.pawnsBoard[_i + 2][_j + 2] = 5;
                                zbijanie_i = _i + 1;
                                zbijanieJot = _j + 1;
                            }
                        } else if (_i == 1 || _i == 0) {
                            if (Board.pawnsBoard[_i + 2][_j + 2] == 0
                                    && (Board.pawnsBoard[_i + 1][_j + 1] == 2
                                    || Board.pawnsBoard[_i + 1][_j + 1] == 6)) {
                                Board.pawnsBoard[_i + 2][_j + 2] = 5;
                                zbijanie_i = _i + 1;
                                zbijanieJot = _j + 1;
                            }
                        } else if (_i == 6 || _i == 7) {
                            if (Board.pawnsBoard[_i - 2][_j + 2] == 0
                                    && (Board.pawnsBoard[_i - 1][_j + 1] == 2
                                    || Board.pawnsBoard[_i - 1][_j + 1] == 6)) {
                                Board.pawnsBoard[_i - 2][_j + 2] = 5;
                                zbijanie_i = _i - 1;
                                zbijanieJot = _j + 1;
                            }
                        }
                    }
                }
            }
        }

        else {
            for (int j = 0; j < 8; j++)
                for (int i = 0; i < 8; i++) {
                    if (Board.pawnsBoard[i][j] == 4) {
                        Board.pawnsBoard[i][j] = 0;
                        Board.pawnsBoard[_i][_j] = 2;
                    }
                    if (Board.pawnsBoard[i][j] == 3) {
                        Board.pawnsBoard[i][j] = 0;
                        Board.pawnsBoard[_i][_j] = 1;
                    }
                    if (Board.pawnsBoard[i][j] == 8) {
                        Board.pawnsBoard[i][j] = 0;
                        Board.pawnsBoard[_i][_j] = 6;
                    }
                    if (Board.pawnsBoard[i][j] == 9) {
                        Board.pawnsBoard[i][j] = 0;
                        Board.pawnsBoard[_i][_j] = 7;
                    }
                    if (Board.pawnsBoard[i][j] == 5)
                        Board.pawnsBoard[i][j] = 0;
                }
            if ((_i == zbijanie_i + 1 || _i == zbijanie_i - 1) && (_j == zbijanieJot - 1 || _j == zbijanieJot + 1)) {
                Board.pawnsBoard[zbijanie_i][zbijanieJot] = 0;
                zbijanie_i = -2;
                zbijanieJot = -2;
            }

            if (_j == 0 && Board.pawnsBoard[_i][_j] == 2) {
                Board.pawnsBoard[_i][_j] = 6;
            }
            if (_j == 7 && Board.pawnsBoard[_i][_j] == 1) {
                Board.pawnsBoard[_i][_j] = 7;
            }

            if (Board.multi) {
                try {
                    Board.oos.writeObject(Board.pawnsBoard);
                    Board.oos.flush();
                    Board.oos.reset();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        if (Board.game) {
            int b = 0;
            int c = 0;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (Board.pawnsBoard[i][j] == 1 || Board.pawnsBoard[i][j] == 3
                            || Board.pawnsBoard[i][j] == 7 || Board.pawnsBoard[i][j] == 9)
                        c++;
                    if (Board.pawnsBoard[i][j] == 2 || Board.pawnsBoard[i][j] == 4
                            || Board.pawnsBoard[i][j] == 6 || Board.pawnsBoard[i][j] == 8)
                        b++;
                }
            }
            if (b == 0 || c == 0)
                JOptionPane.showMessageDialog(null, "Game finished!");
        }
        parent.repaint();
    }

    //Metoda "mouseExited" jest w celu poprawnej implementacji metod np.metody-PionekGracz1#mouseClicked(MouseEvent)
    //Pochodzi z interfejsu MouseListener
    @Override
    public void mouseExited(MouseEvent e) {
    }

    //Metoda "mousePressed" jest w celu poprawnej implementacji metod np.metody-PionekGracz1#mouseClicked(MouseEvent)
    //Pochodzi z interfejsu MouseListener
    @Override
    public void mousePressed(MouseEvent e) {
    }
    //Metoda "mouseReleased" jest w celu poprawnej implementacji metod np.metody-PionekGracz1#mouseClicked(MouseEvent)
    //Pochodzi z interfejsu MouseListener
    @Override
    public void mouseReleased(MouseEvent e) {
    }
}
