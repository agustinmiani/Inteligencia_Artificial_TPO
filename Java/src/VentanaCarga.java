import net.sf.clipsrules.jni.Environment;
import net.sf.clipsrules.jni.FactAddressValue;
import net.sf.clipsrules.jni.MultifieldValue;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by amiani on 5/17/17.
 */
public class VentanaCarga extends JFrame implements ActionListener {

    private JButton btnCarga;
    private JButton btnColor;

    private Vector<String> coloresCargados = new Vector<>();

    private static Environment clips;

    private JLabel categoriaLbl, patronLbl, cantidadBarrasLbl, tipoEquipoLbl;

    private DefaultTableModel listaEquipos;

    private JSeparator jSeparator1;
    private JPanel pnlNE;
    private JSeparator jSeparator2;
    private JPanel panel;
    private JScrollPane jScrollPane1;
    private JComboBox<String> comboBox;
    private JComboBox<String> coloresComboBox;
    private JComboBox<String> cantidadDeBarrasBox;
    private JComboBox<String> patronesBox;
    private JComboBox<String> categoriasBox;
    private JComboBox<String> tipoEquipoBox;
    private JTable tableResultado;

    public VentanaCarga()
    {
        initUI();
        this.setLocation(50 , 100);
        setVisible(true);
        this.pack();
        this.setSize(654, 404);
    }


    private void initUI()
    {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        FlowLayout thisLayout = new FlowLayout();
        getContentPane().setLayout(thisLayout);
        JPanel pnlNO = new JPanel(new GridLayout(4, 1));
        pnlNO.setLayout(null);
        {
            panel = new JPanel();
            pnlNO.add(panel);
            panel.setLayout(null);
            panel.setOpaque(true);
            panel.setBounds(7, 5, 619, 43);
            panel.setSize(619, 30);
        }

        {
            jScrollPane1 = new JScrollPane();
            pnlNO.add(jScrollPane1);
            jScrollPane1.setMinimumSize(new java.awt.Dimension(21, 21));
            jScrollPane1.setBounds(2, 41, 628, 75);
            {
                tableResultado = new JTable();
                jScrollPane1.setViewportView(tableResultado);
                listaEquipos = new DefaultTableModel(
                        new Object[][] {
                                {null, null, null},
                        },
                        new String[] {
                                "Nombre", "Patron", "Cantidad de Barras", "Colores"
                        }
                );
                listaEquipos.setRowCount(20);
                tableResultado.setModel(listaEquipos);
                tableResultado.setPreferredSize(new java.awt.Dimension(622, 50));
                tableResultado.getTableHeader().setSize(622, 50);
                tableResultado.getTableHeader().setPreferredSize(new java.awt.Dimension(225, 20));
                tableResultado.getTableHeader().setAutoscrolls(true);
            }
        }
        {
            JPanel pnlSO = new JPanel(new GridLayout(3, 2));
            pnlNO.add(pnlSO);
            pnlSO.setLayout(null);
            pnlSO.setBorder(BorderFactory.createTitledBorder(""));
            pnlSO.setBounds(2, 121, 628, 235);
            {
                pnlNE = new JPanel();

                pnlSO.add(pnlNE);
                pnlNE.setLayout(null);
                pnlNE.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
                pnlNE.setBounds(424, 32, 159, 64);
            }
            {
                btnCarga = new JButton();
                pnlSO.add(btnCarga);
                btnCarga.setText("Ejecutar");
                btnCarga.setBounds(433, 100, 150, 30);
                btnCarga.addActionListener(this);
            }
            {
                jSeparator2 = new JSeparator();
                pnlSO.add(jSeparator2);
                jSeparator2.setOrientation(SwingConstants.VERTICAL);
                jSeparator2.setBounds(379, 8, 12, 209);
            }
            {
                JPanel pnlSE = new JPanel(new GridLayout(3, 2));
                pnlSO.add(pnlSE);
                pnlSE.setLayout(null);
                pnlSE.setBounds(7, 141, 307, 86);

                JLabel lblPais = new JLabel("Pais");
                pnlSE.add(lblPais, "0, 0");
                lblPais.setBounds(0, 30, 131, 21);
                lblPais.setFont(new java.awt.Font("Segoe UI",1,12));
                comboBox = new JComboBox<>();
                pnlSE.add(comboBox, "1, 0");
                comboBox.setBounds(60, 30, 200, 24);
                comboBox.addItem("Argentina");
                comboBox.addItem("Inglaterra");
                comboBox.addItem("Espania");
                comboBox.addItem("Italia");

                btnColor = new JButton();
                pnlSE.add(btnColor, "1, 0");
                btnColor.setText("Cargar");
                btnColor.setBounds(60, 51, 95, 30);
                btnColor.addActionListener(this);


                patronLbl = new JLabel();
                patronLbl.setText("Patron");
                patronLbl.setBounds(0, 0, 131, 21);
                patronLbl.setFont(new java.awt.Font("Segoe UI",1,12));
                pnlSE.add(patronLbl);

                patronesBox = new JComboBox<>();
                pnlSE.add(patronesBox);
                patronesBox.setBounds(60, 0, 170, 30);
                patronesBox.addItem("Barra Horizontal");
                patronesBox.addItem("Barra Vertical");
                patronesBox.addItem("Barra Diagonal");
                patronesBox.addItem("Liso");

                cantidadBarrasLbl = new JLabel();
                cantidadBarrasLbl.setText("Cantidad de Barras");
                cantidadBarrasLbl.setFont(new java.awt.Font("Segoe UI",1,12));
                pnlSO.add(cantidadBarrasLbl);
                cantidadBarrasLbl.setBounds(7, 115, 120, 30);

                cantidadDeBarrasBox = new JComboBox<>();
                pnlSO.add(cantidadDeBarrasBox);
                cantidadDeBarrasBox.addItem("N/A");
                cantidadDeBarrasBox.addItem("1");
                cantidadDeBarrasBox.addItem("2");
                cantidadDeBarrasBox.addItem("3");
                cantidadDeBarrasBox.addItem("4");
                cantidadDeBarrasBox.addItem("5");
                cantidadDeBarrasBox.addItem("6");
                cantidadDeBarrasBox.setBounds(150, 115, 90, 30);

                categoriaLbl = new JLabel();
                pnlSO.add(categoriaLbl);
                categoriaLbl.setBounds(7, 85, 120, 30);
                categoriaLbl.setText("Categoria");
                categoriaLbl.setFont(new java.awt.Font("Segoe UI",1,12));

                categoriasBox = new JComboBox<>();
                pnlSO.add(categoriasBox);
                categoriasBox.setBounds(80, 85, 130, 30);
                categoriasBox.addItem("Primera A");
                categoriasBox.addItem("Primera B");
                categoriasBox.addItem("Seleccionado Del Pais");

                tipoEquipoLbl = new JLabel();
                tipoEquipoLbl.setText("Tipo de Equipo");
                pnlSO.add(tipoEquipoLbl);
                tipoEquipoLbl.setFont(new java.awt.Font("Segoe UI",1,12));
                tipoEquipoLbl.setBounds(7, 60, 120, 30);

                tipoEquipoBox = new JComboBox<>();
                pnlSO.add(tipoEquipoBox);
                tipoEquipoBox.addItem("Nacional");
                tipoEquipoBox.addItem("Seleccion");
                tipoEquipoBox.setBounds(120, 60, 120, 30);


                JLabel lblColores = new JLabel("Colores");
                pnlSE.add(lblColores, "2, 0");
                lblColores.setBounds(0, 51, 150, 30);
                lblColores.setFont(new java.awt.Font("Segoe UI",1,12));


                coloresComboBox = new JComboBox<>();
                pnlSE.add(coloresComboBox, "2, 0");
                coloresComboBox.setBounds(150, 51, 150, 30);
                coloresComboBox.addItem("Celeste");
                coloresComboBox.addItem("Blanco");
                coloresComboBox.addItem("Azul");
                coloresComboBox.addItem("Bordo");
                coloresComboBox.addItem("Rojo");

            }
        }

        getContentPane().add(pnlNO);
        pnlNO.setPreferredSize(new java.awt.Dimension(640, 381));

    }

    public void actionPerformed(ActionEvent e) {


        if (e.getSource().equals(btnCarga)) {
           ejecutarClips();
        }
        if (e.getSource().equals(btnColor)) {
            cargarColorSeleccionado(coloresComboBox.getSelectedItem().toString());
        }
    }


    private void ejecutarClips (){


        try {
            clips = new Environment();

            clips.loadFromResource("/net/sf/clipsrules/jni/examples/tp6clips/CamisetasFutbolCompleto.clp");

            clips.reset();

            StringBuilder coloresAppend = new StringBuilder();

            if (coloresCargados.size() > 0) {
                coloresAppend.append(coloresCargados.get(0));
                coloresCargados.remove(0);
                if (!coloresCargados.isEmpty()) {
                    for (String color : coloresCargados) {
                        coloresAppend.append(" ");
                        coloresAppend.append(color);
                    }
                }
            } else {
                coloresAppend.append(coloresComboBox.getSelectedItem().toString());
            }

            String cantidadDeBarras = cantidadDeBarrasBox.getSelectedItem().toString();

            if (cantidadDeBarras.equalsIgnoreCase("N/A"))
                cantidadDeBarras = "0";

            String evalString = "(assert (patrones " + eliminarEspacios(patronesBox.getSelectedItem().toString()) + " ) (colores " + coloresAppend.toString() + " ) (cantidadBarras " + cantidadDeBarras + " ) (categoria " + eliminarEspacios(categoriasBox.getSelectedItem().toString()) + " ) (pais " + comboBox.getSelectedItem().toString() + " ))";

            clips.eval(evalString);


            clips.run();


            String evalStr = "(find-all-facts((?J resultado_final)) TRUE)";
            //Aca nos devuelve un campo que esta definido en el paquete de clips. Si yo que tengo un
            //solo hecho de resultado como esto lo que devuelve es como un array en este caso me
            //quedo con el primero, es decir con el unico
            try {
                MultifieldValue pv = (MultifieldValue) clips.eval(evalStr);

                String nombre = null;
                String patron = null;
                MultifieldValue colores = null;
                FactAddressValue fv = null;

                if (pv != null) {

                    actualizarTabla(pv);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al Conectar con Clips. \n Verifique si dispone de las Configuraciones Correspondientes.", "Trabajo Practico 6", JOptionPane.WARNING_MESSAGE);
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error al Conectar con Clips. \n Verifique si dispone de las Configuraciones Correspondientes.", "Trabajo Practico 6", JOptionPane.WARNING_MESSAGE);
            }
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error al Levantar la Libreria de Clips. \n Verifique si dispone de las Configuraciones Correspondientes.", "Trabajo Practico 6", JOptionPane.WARNING_MESSAGE);
        }

        coloresCargados = new Vector<>();

    }

    private void cargarColorSeleccionado (String colorSeleccionado) {


        for(String color: coloresCargados){
            if(color.equals(colorSeleccionado))
                return;
        }

        coloresCargados.add(colorSeleccionado);

    }

    private void actualizarTabla (MultifieldValue pv) {

        // borro la tabla entera porque obtengo TODOS los resultados
        String nombre = null;
        String patron = null;
        MultifieldValue colores = null;
        FactAddressValue fv = null;
        String cantidadDeBarras = null;
        String colorString;

        listaEquipos.setRowCount(0);

        //	CABECERA : "Nombre", "Patron", "Cantidad de Barras", "Colores"
        if(pv.size() > 0) {


            Vector<String> aux = null;

            System.out.println("\n\n\n\n");
            for (int i2 = 0; i2 < pv.size(); i2++) {

                aux = new Vector<>();
                colorString = "";
                nombre = null;
                patron = null;
                colores = null;
                cantidadDeBarras = null;

                fv = (FactAddressValue) pv.get(i2);

                try {
                    //ResultadoFinal era el hecho, aca obtengo el slot "resultado" u otro slot que quiera
                	if (fv.getFactSlot("nombre") != null) {
                        nombre = fv.getFactSlot("nombre").toString();
                        aux.add(nombre);
                    }
                        colores = (MultifieldValue) fv.getFactSlot("colores-camisetas");
                        if (fv.getFactSlot("patron") != null) {
                            patron = fv.getFactSlot("patron").toString();
                            aux.add(patron);
                        }
                        if (fv.getFactSlot("cantidadBarras") != null) {
                            cantidadDeBarras = fv.getFactSlot("cantidadBarras").toString();
                            aux.add(cantidadDeBarras);
                        }

                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }

                    System.out.println("Nombre: " + nombre);
                    System.out.println("Patron: " + patron);
                    System.out.println("Cantidad de Barras: " + cantidadDeBarras);
                    if (colores != null && colores.size() > 0) {
                        System.out.println("Colores: ");
                        for (int i = 0; i < colores.size(); i++) {
                            System.out.println(colores.get(i));
                            if(colorString.length() != 0)
                                colorString = colorString + " / ";
                            colorString = colorString + colores.get(i);
                        }
                        aux.add(colorString);
                    }
                    System.out.println("\n\n\n\n");

                listaEquipos.addRow(aux);
                }

            }
            else
                JOptionPane.showMessageDialog(null, "No se han obtenido resultados con dicha combinaci?n.\nPor favor, intente con otra...", "Trabajo Practico 6", JOptionPane.WARNING_MESSAGE);


    }

    private String eliminarEspacios (String palabra){

        return palabra.replaceAll("\\s","");
    }

}
