package uniandes.dpoo.taller4.view;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Top10;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;

public class VentanaTop10 extends JFrame {
    private Top10 top;

    public VentanaTop10() {

        JFrame frame = new JFrame("Top10");
        top = new Top10();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(200,400);
        top.cargarRecords(new File("data/top10.csv"));
        //System.out.println(top.darRegistros());
        Collection<RegistroTop10> registers = top.darRegistros();
        int i = 1;
        DefaultListModel<JPanel> modeloLista = new DefaultListModel<>();
        JPanel title = new JPanel();
        JLabel titleTxt= new JLabel("#  Nombre | Puntaje");
        title.setPreferredSize(new Dimension(150, 30));
        title.setBackground(Color.decode("#489CE1"));
        title.add(titleTxt);
        modeloLista.addElement(title);


        for (RegistroTop10 register : registers) {
            JPanel eachRegister = new JPanel();
            JLabel pos= new JLabel(String.valueOf(i));
            JLabel name = new JLabel(register.darNombre());
            JLabel points = new JLabel(String.valueOf(register.darPuntos()));
            if(i >=1 && i <=3){
                pos.setForeground(Color.GREEN);
                name.setForeground(Color.GREEN);
                points.setForeground(Color.GREEN);
            }
            eachRegister.setPreferredSize(new Dimension(150, 30));
            eachRegister.add(pos);
            eachRegister.add(name);
            eachRegister.add(points);
            //panelPrincipal.add(eachRegister);
            modeloLista.addElement(eachRegister);
            i++;
        }

        // Crear una instancia de JList y pasarle el arreglo de elementos como argumento
        JList<JPanel> lista = new JList<JPanel>(modeloLista);
//        // Configurar algunas propiedades de la lista
        lista.setCellRenderer(new PanelRenderer());
        lista.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // permitir sólo una selección
        lista.setLayoutOrientation(JList.VERTICAL); // mostrar la lista en forma vertical
        lista.setVisibleRowCount(5); // mostrar todos los elementos

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.add(new JScrollPane(lista)); // Agregamos la lista dentro de un JScrollPane para permitir el desplazamiento
        frame.add(panelPrincipal);


        // Mostrar la ventana
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}

class PanelRenderer extends JPanel implements ListCellRenderer<JPanel> {
    @Override
    public Component getListCellRendererComponent(JList<? extends JPanel> list, JPanel value, int index, boolean isSelected, boolean cellHasFocus) {
        removeAll();
        add(value);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setSize(150,10);
        if (isSelected) {
            setBackground(Color.LIGHT_GRAY);
        } else {
            setBackground(Color.WHITE);
        }
        return this;
    }
}