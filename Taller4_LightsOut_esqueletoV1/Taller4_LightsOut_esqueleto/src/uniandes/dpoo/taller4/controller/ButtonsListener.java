package uniandes.dpoo.taller4.controller;

import uniandes.dpoo.taller4.modelo.Tablero;
import uniandes.dpoo.taller4.view.VentanaTop10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonsListener implements ActionListener {
    Tablero modelo;

    public ButtonsListener(Tablero modelo ) {
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String textoBoton = e.getActionCommand();
        if (textoBoton.equals("NUEVO")){
            int randomPlays = 1;
            String difficulty = e.getActionCommand();
            switch (difficulty) {
                case "Facil" -> randomPlays = 1;
                case "Medio" -> randomPlays = 2;
                case "Dificil" -> randomPlays = 5;
            }
            modelo.desordenar(randomPlays);
            boolean[][] tablero = modelo.darTablero();
            int tam = tablero.length;
            CreateRectangles[][] rectangles = PanelTablero.getRectangles();
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    if (tablero[i][j]){
                        rectangles[i][j].setIluminated(true);
                        rectangles[i][j].repaint();
                    }else{
                        rectangles[i][j].setIluminated(false);
                        rectangles[i][j].repaint();
                    }
                }
            }

        }else if(textoBoton.equals("REINICIAR")){
            modelo.reiniciar();
            boolean[][] tablero = modelo.darTablero();
            int tam = tablero.length;
            CreateRectangles[][] rectangles = PanelTablero.getRectangles();
            for (int i = 0; i < tam; i++) {
                for (int j = 0; j < tam; j++) {
                    if (tablero[i][j]){
                        rectangles[i][j].setIluminated(true);
                        rectangles[i][j].repaint();
                    }else{
                        rectangles[i][j].setIluminated(false);
                        rectangles[i][j].repaint();
                    }
                }

            }

        }else if(textoBoton.equals("TOP 10")){
            VentanaTop10 top10 = new VentanaTop10();


        }else if(textoBoton.equals("CAMBIAR JUGADOR")){

        }


    }
}
