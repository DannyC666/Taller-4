package uniandes.dpoo.taller4.controller;

import javax.swing.*;
import java.awt.*;

public  class CreateRectangles extends JPanel {
        private int x, y, width, height;
        private boolean isIluminated;
        public CreateRectangles(int x, int y, int width, int height, boolean isIluminated) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.isIluminated = isIluminated;
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            if(this.isIluminated){
                g2d.setColor(Color.YELLOW);
                g2d.fillRect(x, y, width, height);
                g2d.setColor(Color.GRAY);
                // Dibujar el borde del rectángulo
                g2d.drawRect(x, y, width, height);
                // Establecer suavizado de bordes
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

            }else {
                g2d.setColor(Color.GRAY);
                g2d.fillRect(x, y, width, height);
                g2d.setColor(Color.GRAY);
                // Dibujar el borde del rectángulo
                g2d.drawRect(x, y, width, height);
                // Establecer suavizado de bordes
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON);

            }

        }

         public void setIluminated(Boolean iluminated) {
                this.isIluminated = iluminated;
        }


}

