package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RedesController {

	public RedesController() {
		super();
	}

	private String os() {
		String os = System.getProperty("os.name");
		return os;
	}

	@SuppressWarnings("deprecation")
	public void ip() {
		String os = os();
		if (os.contains("Windows")) {
			try {
				Process p = Runtime.getRuntime().exec("ipconfig");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String separador = " ";
				String nomeAdaptador = "";
				while (linha != null) {
					String[] partes = linha.split(separador);
					if (linha.contains("Adaptador") || linha.contains("Adapter")) {
						int index = linha.lastIndexOf(":");
						nomeAdaptador = linha.substring(0, index);
						linha = buffer.readLine();
					}

					partes = linha.split(separador);
					if (linha.contains("IPv4")) {
						String ipv4 = partes[partes.length - 1];
						System.out.println(nomeAdaptador);
						System.out.println("IPv4: " + ipv4);
						System.out.println();
					}

					linha = buffer.readLine();
				}
				fluxo.close();
				leitor.close();
				buffer.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (os.contains("Linux")) {
			try {
				Process p = Runtime.getRuntime().exec("/sbin/ifconfig");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String separador = " ";
				String nomeAdaptador = "";
				while (linha != null) {
					String[] partes = linha.split(separador);
					if (linha.contains("flags")) {
						nomeAdaptador = partes[0];
						linha = buffer.readLine();
					}

					partes = linha.split(separador);
					if (linha.contains("inet") && !linha.contains("t6")) {
						String ipv4 = partes[9];
						System.out.println(nomeAdaptador);
						System.out.println("IPv4: " + ipv4);
						System.out.println();
					}

					linha = buffer.readLine();
				}
				fluxo.close();
				leitor.close();
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	public void ping() {
		String os = os();
		if (os.contains("Windows")) {
			try {
				System.out.println("Calculando tempo médio de ping para www.google.com.br, aguarde...");
				Process p = Runtime.getRuntime().exec("ping -4 -n 10 www.google.com.br");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String separador = " ";
				while (linha != null) {
					String[] partes = linha.split(separador);
					if (linha.contains("dia")) {
						String media = partes[partes.length - 1];
						System.out.println("Média do ping para www.google.com.br: " + media);
					}
					linha = buffer.readLine();
				}
				fluxo.close();
				leitor.close();
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if (os.contains("Linux")) {
			try {
				System.out.println("Calculando tempo médio de ping para www.google.com.br, aguarde...");
				Process p = Runtime.getRuntime().exec("ping -4 -c 10 www.google.com.br");
				InputStream fluxo = p.getInputStream();
				InputStreamReader leitor = new InputStreamReader(fluxo);
				BufferedReader buffer = new BufferedReader(leitor);
				String linha = buffer.readLine();
				String separador = "/";
				while (linha != null) {
					String[] partes = linha.split(separador);
					if (linha.contains("rtt")) {
						String media = partes[4];
						System.out.println("Média do ping para www.google.com.br: " + media);
					}
					linha = buffer.readLine();
				}
				fluxo.close();
				leitor.close();
				buffer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
