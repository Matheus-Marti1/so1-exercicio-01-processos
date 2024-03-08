package view;

import controller.RedesController;

import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		RedesController rede = new RedesController();

		int opc = 0;
		while (opc != 9) {
			try {
				opc = Integer.parseInt(JOptionPane.showInputDialog(
						"Digite uma opção:\n1 - Mostrar os adaptadores de rede com IPv4\n2 - Mostrar a média do ping para o Google\n9 - Sair"));
				switch (opc) {
				case 1:
					rede.ip();
					break;
				case 2:
					rede.ping();
					break;
				case 9:
					System.exit(0);
				default:
					JOptionPane.showMessageDialog(null, "Opção inválida. Digite novamente.");
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Opção inválida. Digite novamente.");
			}
		}
	}
}
