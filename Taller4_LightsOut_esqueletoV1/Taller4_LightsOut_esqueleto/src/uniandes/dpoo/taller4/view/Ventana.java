package uniandes.dpoo.taller4.view;

import uniandes.dpoo.taller4.controller.ButtonsListener;
import uniandes.dpoo.taller4.controller.CreateRectangles;
import uniandes.dpoo.taller4.controller.PanelTablero;
import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.modelo.Top10;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.UnsupportedEncodingException;

public class Ventana extends JFrame {

    private Tablero modelo ;
    private Top10 top;

    public Ventana(){

        JFrame frame = new JFrame("BoxLayoutDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);
        // --------------------------------  Crear parte de arriba -----------------------------
        JPanel highPanel = new JPanel();
        highPanel.setLayout(new BoxLayout(highPanel, BoxLayout.X_AXIS));
        String[] opciones = {"3X3", "5X5","7X7" , "4X4", "8X8"};
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        comboBox.setPreferredSize(new Dimension(200, comboBox.getPreferredSize().height));
        int tam = setTam(comboBox);
        modelo = new Tablero(tam);
        top = new Top10();

        JLabel size = new JLabel("Tamano: ");
        JLabel difficulty = new JLabel("      Dificultad: ");
        ButtonGroup grupo = new ButtonGroup();
        JRadioButton boton1 = new JRadioButton("Facil");
        JRadioButton boton2 = new JRadioButton("Medio");
        JRadioButton boton3 = new JRadioButton("Dificil");
        ButtonsListener listener = new ButtonsListener(modelo);
        boton1.addActionListener(listener);
        boton2.addActionListener(listener);
        boton3.addActionListener(listener);

        //Cambio de colores
        highPanel.setBackground(Color.decode("#489CE1"));
        size.setForeground(Color.WHITE);
        difficulty.setForeground(Color.WHITE);
        boton1.setBackground(Color.decode("#489CE1"));
        boton1.setForeground(Color.WHITE);
        boton2.setBackground(Color.decode("#489CE1"));
        boton2.setForeground(Color.WHITE);
        boton3.setBackground(Color.decode("#489CE1"));
        boton3.setForeground(Color.WHITE);
        grupo.add(boton1);
        grupo.add(boton2);
        grupo.add(boton3);
        highPanel.add(size);
        highPanel.add(comboBox);
        highPanel.add(difficulty);
        highPanel.add(boton1);
        highPanel.add(boton2);
        highPanel.add(boton3);
        frame.add(highPanel, BorderLayout.NORTH);
        //Padding panel superior
        JLabel paddingHigh = new JLabel("");
        EmptyBorder paddingH = new EmptyBorder(1, 30, 1, 30); // Crear un padding de 10 píxeles
        paddingHigh.setHorizontalAlignment(JLabel.CENTER); // Centrar el JLabel
        highPanel.setBorder(paddingH); // Establecer el padding al panel
        highPanel.add(paddingHigh, BorderLayout.CENTER);

        // --------------------------------  Crear parte derecha -----------------------------
        JPanel buttonPane = new JPanel();
        addComponentsToPane(buttonPane);
        frame.add(buttonPane, BorderLayout.EAST);
        //Padding panel derecho
        JLabel paddingRight = new JLabel("");
        EmptyBorder padding = new EmptyBorder(100, 0, 100, 0); // Crear un padding de 10 píxeles
        paddingRight.setHorizontalAlignment(JLabel.CENTER); // Centrar el JLabel
        buttonPane.setBorder(padding); // Establecer el padding al panel
        buttonPane.add(paddingRight, BorderLayout.CENTER);

        // --------------------------------  Crear parte abajo -----------------------------
        JPanel lowPanel = new JPanel();
        lowPanel.setLayout(new BoxLayout(lowPanel, BoxLayout.X_AXIS));
        JLabel movements = new JLabel("Jugadas: ");
        JTextField movementsText = new JTextField("0");
        JLabel player = new JLabel("Jugador:  ");
        JTextField playerText = new JTextField("");
        lowPanel.add(movements);
        lowPanel.add(movementsText);
        lowPanel.add(player);
        lowPanel.add(playerText);
        frame.add(lowPanel, BorderLayout.SOUTH);

        // --------------------------------  crear tablero  --------------------------------
        JPanel dashBoard = new JPanel();
        new PanelTablero(dashBoard,tam,frame,this);
        frame.add(dashBoard, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        PanelTablero.actualizar(dashBoard,frame,tam,movementsText);
        Ventana ventana = this;
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int tam = setTam(comboBox);
                modelo = new Tablero(tam);
                buttonPane.removeAll();
                addComponentsToPane(buttonPane);
                dashBoard.removeAll();
                new PanelTablero(dashBoard,tam,frame,ventana);
                frame.add(dashBoard, BorderLayout.CENTER);
                frame.pack();
                frame.setVisible(true);
                PanelTablero.actualizar(dashBoard,frame,tam,movementsText);
            }
        });

    }
    public  void addComponentsToPane(Container pane) {
            pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
            addAButton("NUEVO", pane);
            addAButton("REINICIAR", pane);
            addAButton("TOP 10", pane);
            addAButton("CAMBIAR JUGADOR", pane);
    }
    private  void addAButton(String text, Container container) {
            JButton button = new JButton(text);
            button.setBackground(Color.decode("#489CE1")); // Establecer el color de fondo del JButton a azul
            button.setForeground(Color.WHITE); // Establecer el color de fuente del JButton a blanco
            button.setPreferredSize(new Dimension(200, 50));
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            button.setAlignmentY(Component.CENTER_ALIGNMENT);
            //Se crea un listener a cada boton
            ButtonsListener listener = new ButtonsListener(modelo);
            button.addActionListener(listener);
            container.add(button,  BorderLayout.EAST);

    }

    public void jugar(int fila, int columna ) throws FileNotFoundException, UnsupportedEncodingException {
            modelo.jugar(fila,columna);
            boolean[][] tablero = modelo.darTablero();
            int tam = tablero.length;
            CreateRectangles[][] rectangles = PanelTablero.getRectangles();
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    if (tablero[i][j]){
                        rectangles[i][j].setIluminated(false);
                        rectangles[i][j].repaint();
                    }else{
                        rectangles[i][j].setIluminated(true);
                        rectangles[i][j].repaint();
                    }
                }
            }
            if(modelo.tableroIluminado()  ){
                JOptionPane.showMessageDialog(this, "Has completado el tablero!", "Fin del juego",
                        JOptionPane.INFORMATION_MESSAGE);
                finishGame();
            }

    }
    public void countMovements(JTextField movements) throws FileNotFoundException, UnsupportedEncodingException {
        int movement = modelo.darJugadas();
        movements.setText(String.valueOf(movement));
        if(movement >= 500){
            JOptionPane.showMessageDialog(this, "Llegaste al limite de" + movement + " jugadas!", "Fin del juego",
                    JOptionPane.INFORMATION_MESSAGE);
            finishGame();
        }
    }

    public void finishGame() throws FileNotFoundException, UnsupportedEncodingException {

        int puntaje = modelo.calcularPuntaje();
        top.cargarRecords(new File("data/top10.csv"));
        top.agregarRegistro("Juan", puntaje);


        if(top.esTop10(puntaje)){
            JOptionPane.showMessageDialog(this, "Entraste en el top  10!","Fin del juego",
                    JOptionPane.INFORMATION_MESSAGE);
        }else{
            JOptionPane.showMessageDialog(this, "Su puntaje es muy malo, no entra al top 10","Fin del juego",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        top.salvarRecords(new File("data/top10.csv"));
        int opcion = JOptionPane.showConfirmDialog(this, "Quieres iniciar un nuevo juego?", "Juego Terminado", JOptionPane.YES_NO_OPTION);
        // si el usuario hace clic en "Sí", reinicia el juego.
        if (opcion == JOptionPane.YES_OPTION) {
            modelo.reiniciar();
        }else{
            dispose();
        }

    }

    public int setTam(JComboBox options){
        String seleccion = (String) options.getSelectedItem();
        return Character.getNumericValue(seleccion.charAt(0));
    }

    public static void main(String[] args) {
        Ventana ventana = new Ventana();

    }
}
