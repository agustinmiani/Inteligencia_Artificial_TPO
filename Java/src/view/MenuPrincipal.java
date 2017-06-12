package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import model.Duracion;
import model.Resultado;
import model.Riesgo;

import controller.Controller;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MenuPrincipal extends javax.swing.JFrame {

	{
		//Set Look & Feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	private static final long serialVersionUID = 1L;
	private JLabel jLabel1;
	private JLabel jl_Duracion;
	private JComboBox jcb_Duracion;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JButton jbtn_Ejecutar;
	private JTextArea jta_Resultados;
	private JSeparator jSeparator1;
	private JTextField jtf_Monto;
	private JComboBox jcb_TasaRetorno;
	private JComboBox jcb_Riesgo;
	private JPanel jPanel1;

	private JMenuBar jMenuBar1;
	private JMenu jMenu1;
	private JMenu jMenu3;
	private JMenu jMenu6;
	private JMenuItem jMenuItem7;


	public static void main(String[] args) {
		Controller.getInstance();

		MenuPrincipal inst = new MenuPrincipal();

		inst.setLocationRelativeTo(null);
		inst.setResizable(false); 
		inst.setVisible(true);
	}
	
	public MenuPrincipal() {
		super();

		initGUI();
		initEvents();
	}
	
	private void initEvents() {

		jMenu6.addMouseListener(new MouseListener() {
			public void mousePressed(MouseEvent arg0) {
				// Cierra el Sistema
				int seguro = JOptionPane.showConfirmDialog(null, "¿Está seguro que desea salir?", "Atención!", JOptionPane.YES_NO_OPTION);

				if (seguro == JOptionPane.YES_OPTION)
					System.exit(0);				
			}

			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}

			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		jMenuItem7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JOptionPane.showMessageDialog(null,
					"<html><center> <font size=4> <i>Sistema Experto v1.3</i> </font> <br><br>" +
						"<table>" +
							"<tr> <td>Braceras Santiago</td> <td>LU: 1022815</td> </tr>" +
							"<tr> <td>Cabrera Alberto</td> <td>LU: 1028513</td> </tr>" +
							"<tr> <td>Castillo de Carvalho Emmanuel</td> <td>LU: 1022258</td> </tr>" +
							"<tr> <td>Masaro Gaston</td> <td>LU: 1041265</td> </tr>" +
							"<tr> <td>Miani Agustin</td> <td>LU: 1038227</td> </tr>" +
							"<tr> <td>Pandini Stefano</td> <td>LU: 1041335</td> </tr>" +
						"</table>" +
					"</center></html>",
					"Acerca de",JOptionPane.QUESTION_MESSAGE);
			}
		});

		jcb_Duracion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarEjecucion();
			}
		});

		jcb_Riesgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarEjecucion();
			}
		});

		jcb_TasaRetorno.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				habilitarEjecucion();
			}
		});

		jtf_Monto.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent evt) {
				habilitarEjecucion();
			}
			public void keyTyped(KeyEvent evt) {
				char c = evt.getKeyChar();
				if (!Character.isDigit(c))
					evt.consume();
			}
		});

		jMenu1.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				EjecutarSistemaExperto();
			}

			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
			}

		});

		jbtn_Ejecutar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				EjecutarSistemaExperto();
			}
		});

	}

	private void habilitarEjecucion() {
		if (
//			(jcb_Duracion.getSelectedIndex() > 0) && 
			(jcb_Riesgo.getSelectedIndex() > 0) && 
//			(jcb_TasaRetorno.getSelectedIndex() > 0) && 
			(Integer.valueOf(jtf_Monto.getText()).intValue() > 0)) {

			jbtn_Ejecutar.setEnabled(true);
			jMenu1.setEnabled(true);
		} else {
			jbtn_Ejecutar.setEnabled(false);
			jMenu1.setEnabled(false);
		}
	}

	private void EjecutarSistemaExperto() {
		Integer tasaRetorno = Integer.valueOf(-1);
		List<Resultado> resultados;

		jta_Resultados.setText("");

		if (jcb_TasaRetorno.getSelectedIndex() == 0);
			// ya la inicialice...
		else if (jcb_TasaRetorno.getSelectedItem().equals("0-5"))
			// le asigno la mitad del intervalo asi entra en las reglas!
			tasaRetorno = 2;
		else if (jcb_TasaRetorno.getSelectedItem().equals("5-10"))
			tasaRetorno = 7;
		else if (jcb_TasaRetorno.getSelectedItem().equals("10-20"))
			tasaRetorno = 15;
		else if (jcb_TasaRetorno.getSelectedItem().equals("20-30"))
			tasaRetorno = 25;
		else if (jcb_TasaRetorno.getSelectedItem().equals("30-50"))
			tasaRetorno = 40;
		else if (jcb_TasaRetorno.getSelectedItem().equals("mayor a 50"))
			tasaRetorno = 60;

		resultados = Controller.getInstance().ejecutarClips(
			Riesgo.values()[jcb_Riesgo.getSelectedIndex() - 1],
			jcb_Duracion.getSelectedIndex() > 0 ? Duracion.values()[jcb_Duracion.getSelectedIndex() - 1] : null,
			tasaRetorno,
			Float.valueOf(jtf_Monto.getText())
		);

		if (resultados != null) {
			for(int i=0; i < resultados.size(); i++) {
				Resultado resultado = resultados.get(i);
				jta_Resultados.append(i+1 + ". invierta $ " + resultado.getMonto() + 
					" en " + resultado.getNombre() + "\n");
			}
		}
	}

	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			this.setSize(597, 522);
			this.setTitle("Sistema Experto - Recomendacion de Inversiones");

			getContentPane().setLayout(null);
			this.setPreferredSize(new java.awt.Dimension(597, 522));
			{
				{
					jPanel1 = new JPanel();
					getContentPane().add(jPanel1);
					jPanel1.setBounds(7, 5, 560, 434);
					jPanel1.setLayout(null);
					{
						jl_Duracion = new JLabel();
						jPanel1.add(jl_Duracion);
						jl_Duracion.setText("Duración / Inmovilización del Capital :");
						jl_Duracion.setBounds(21, 28, 210, 28);
						jl_Duracion.setOpaque(false);
						jl_Duracion.setFont(new java.awt.Font("Segoe UI",1,12));
						jl_Duracion.setLayout(null);
						jl_Duracion.setHorizontalTextPosition(SwingConstants.LEADING);
						jl_Duracion.setHorizontalAlignment(SwingConstants.TRAILING);
					}
					{
						ComboBoxModel jcb_DuracionModel = new DefaultComboBoxModel(
							new String[] { "<seleccione periodo>", "Dia", "Semana", "Mes", "Año" });
						jcb_Duracion = new JComboBox();
						jPanel1.add(jcb_Duracion);
						jcb_Duracion.setModel(jcb_DuracionModel);
						jcb_Duracion.setBounds(238, 28, 155, 28);
					}
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("(*) Nivel de riesgo :");
						jLabel1.setFont(new java.awt.Font("Segoe UI",1,12));
						jLabel1.setOpaque(false);
						jLabel1.setLayout(null);
						jLabel1.setBounds(126, 67, 105, 28);
						jLabel1.setHorizontalTextPosition(SwingConstants.LEADING);
						jLabel1.setHorizontalAlignment(SwingConstants.TRAILING);
					}
					{
						ComboBoxModel jcb_RiesgoModel = new DefaultComboBoxModel(
							new String[] { "<seleccione riesgo>", "Minimo", "Bajo", "Medio", "Alto" });
						jcb_Riesgo = new JComboBox();
						jPanel1.add(jcb_Riesgo);
						jcb_Riesgo.setModel(jcb_RiesgoModel);
						jcb_Riesgo.setBounds(238, 67, 155, 28);
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Tasa de retorno anual :");
						jLabel2.setFont(new java.awt.Font("Segoe UI",1,12));
						jLabel2.setOpaque(false);
						jLabel2.setLayout(null);
						jLabel2.setBounds(105, 107, 126, 28);
						jLabel2.setHorizontalTextPosition(SwingConstants.LEADING);
						jLabel2.setHorizontalAlignment(SwingConstants.TRAILING);
					}
					{
						ComboBoxModel jcb_TasaRetornoModel = new DefaultComboBoxModel(
							new String[] { "<seleccione segmento>", "0-5", "5-10", "10-20", "20-30", "30-50", "mayor a 50" });
						jcb_TasaRetorno = new JComboBox();
						jPanel1.add(jcb_TasaRetorno);
						jcb_TasaRetorno.setModel(jcb_TasaRetornoModel);
						jcb_TasaRetorno.setBounds(238, 107, 155, 28);
					}
					{
						jLabel3 = new JLabel();
						jPanel1.add(jLabel3);
						jLabel3.setText("(*) Monto a invertir :");
						jLabel3.setFont(new java.awt.Font("Segoe UI",1,12));
						jLabel3.setOpaque(false);
						jLabel3.setLayout(null);
						jLabel3.setBounds(112, 147, 119, 28);
						jLabel3.setHorizontalTextPosition(SwingConstants.LEADING);
						jLabel3.setHorizontalAlignment(SwingConstants.TRAILING);
					}
					{
						jtf_Monto = new JTextField();
						jPanel1.add(jtf_Monto);
						jtf_Monto.setText("0");
						jtf_Monto.setBounds(238, 147, 155, 28);
						jtf_Monto.setHorizontalAlignment(SwingConstants.TRAILING);
					}
					{
						jSeparator1 = new JSeparator();
						jPanel1.add(jSeparator1);
						jSeparator1.setBounds(14, 239, 532, 21);
					}
					{
						jta_Resultados = new JTextArea();
						jPanel1.add(jta_Resultados);
						jta_Resultados.setBounds(14, 243, 532, 175);
						jta_Resultados.setBackground(new java.awt.Color(240,255,240));
						jta_Resultados.setEditable(false);
						jta_Resultados.setFont(new java.awt.Font("Consolas",1,14));
					}
					{
						jbtn_Ejecutar = new JButton();
						jPanel1.add(jbtn_Ejecutar);
						jbtn_Ejecutar.setText("Ejecutar");
						jbtn_Ejecutar.setBounds(294, 187, 98, 35);
						jbtn_Ejecutar.setSize(98, 30);
					}
				}

				jMenuBar1 = new JMenuBar();
				setJMenuBar(jMenuBar1);
				jMenuBar1.setPreferredSize(new java.awt.Dimension(464, 23));
				{
					jMenu1 = new JMenu();
					jMenuBar1.add(jMenu1);
					jMenu1.setText("Ejecutar");
					jMenu1.setEnabled(false);
				}
				{
					jMenu3 = new JMenu();
					jMenuBar1.add(jMenu3);
					jMenu3.setText("Ayuda");
					{
						jMenuItem7 = new JMenuItem();
						jMenu3.add(jMenuItem7);
						jMenuItem7.setText("Acerca de...");
					}
				}
				{
					jMenu6 = new JMenu();
					jMenuBar1.add(jMenu6);
					jMenu6.setText("Salir");
				}
			}

			habilitarEjecucion();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
