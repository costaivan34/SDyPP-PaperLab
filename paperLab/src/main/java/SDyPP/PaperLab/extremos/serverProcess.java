package SDyPP.PaperLab.extremos;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.nio.file.Files;

import SDyPP.PaperLab.utils.mensaje;

public class serverProcess implements Runnable {
	private ObjectInputStream inputChannel;
	private ObjectOutputStream outputChannel;
	private Socket socketCliente;
	private String rootDirectory;
	private byte[] fileData;

	public serverProcess(Socket socketCliente2, String rootDirectory) {
		// TODO Auto-generated constructor stub
	}

	public void enviar(Object obj) {
		try {
			outputChannel.writeObject(obj);
			outputChannel.flush();
		} catch (IOException e) {
			System.out.println("Connection Timeout");
			System.exit(0);
		}

	}

	public Object recibir() {
		Object response = null;
		try {
			response = inputChannel.readObject();
		} catch (IOException | ClassNotFoundException e) {
			System.out.println("Connection Timeout");
			System.exit(0);
		}
		return response;

	}

	public void closeConection(Socket s) {
		try {
			this.inputChannel.close();
			this.outputChannel.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			mensaje m = new mensaje("ER", null);
			outputChannel = new ObjectOutputStream(this.socketCliente.getOutputStream());
			inputChannel = new ObjectInputStream(this.socketCliente.getInputStream());
			Object obj = this.recibir();
			if (obj instanceof mensaje) {
				if ((((mensaje) obj).getTipo().equals("GET")) && (((mensaje) obj).getBody() instanceof String)) {
					File fRuta = new File(this.rootDirectory + ((mensaje) obj).getBody());
					if (fRuta.isFile()) {
						this.fileData = Files.readAllBytes(fRuta.toPath());
						m = new mensaje("OK", this.fileData);
					}
				}
			}
			this.enviar(m);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
