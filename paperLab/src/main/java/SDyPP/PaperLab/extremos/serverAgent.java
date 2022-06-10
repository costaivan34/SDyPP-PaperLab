package SDyPP.PaperLab.extremos;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class serverAgent implements Runnable {
	private String rootDirectory;
	private int puerto;
	private Socket socketCliente;
	private ServerSocket socketServer;

	public serverAgent(int puerto, String sysdir) throws Exception {
		this.rootDirectory = sysdir;
		this.setPuerto(puerto);
		// this.iniciarServer();
	}

	@Override
	public void run() {
		try {
			socketServer = new ServerSocket(this.puerto);

			System.out.println(" Server started on Port: " + this.puerto);
			System.out.println("Waiting...");
			while (true) {
				this.socketCliente = socketServer.accept();
				System.out.println("Client connected from: " + socketCliente.getInetAddress().getCanonicalHostName()
						+ ":" + socketCliente.getPort());
				serverProcess SP = new serverProcess(this.socketCliente , this.rootDirectory);
				Thread clientAsistant = new Thread(SP);
				clientAsistant.start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

}
