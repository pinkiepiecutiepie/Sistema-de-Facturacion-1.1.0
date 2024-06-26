package facturacion.formulario;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class formularioInternoFacebook extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public formularioInternoFacebook() {
		setTitle("Facebook");
		setBounds(100, 100, 817, 570);
		
		JButton btnNewButton = new JButton("Alerta");
		btnNewButton.setBounds(175, 90, 85, 25);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Mensaje", "Titulo", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Confirmar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogResult = JOptionPane.showConfirmDialog(null, "¿Estas seguro?", "Confirmar", JOptionPane.YES_NO_OPTION);
				if (dialogResult == JOptionPane.YES_OPTION) {
					System.out.print("Se ha pulsado si");
				} else {
					System.out.print("Se ha pulsado no");
				}
			}
		});
		btnNewButton_1.setBounds(175, 125, 85, 25);
		getContentPane().add(btnNewButton_1);
	}

}