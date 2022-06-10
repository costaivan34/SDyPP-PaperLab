package SDyPP.PaperLab.extremos;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

import SDyPP.PaperLab.utils.mensaje;


public class clientProcess implements Runnable{
	private String nombreArchivo;
	private byte[] fileData;
	private ArrayList<InetAddress> listaPares = new ArrayList();
	private Socket socket;
	private int serverPort;
	private ObjectInputStream inputChannel;
	private ObjectOutputStream outputChannel;
	
	
	public clientProcess(String nombreArchivo,ArrayList<InetAddress> listaPares) throws Exception {
		this.nombreArchivo= nombreArchivo;
		this.listaPares=listaPares;
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
	
	public byte[] getFileData() {
		return fileData;
	}


	private void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}
	
	private void iniciarCliente(InetAddress ip) throws IOException  {
		mensaje m = null;
		boolean getState = false;
		for (InetAddress IA: listaPares){
			if (IA.isReachable(this.serverPort)) {
				this.socket = new Socket (IA , this.serverPort);
				outputChannel = new ObjectOutputStream(this.socket.getOutputStream());
				inputChannel = new ObjectInputStream(this.socket.getInputStream());			
				m = new mensaje("GET",nombreArchivo);
				this.enviar(m);
				m = (mensaje) this.recibir();
				if(m.getTipo().equals("OK")) {
					this.setFileData((byte[]) m.getBody());
					getState = true;
					break;
				}
				this.closeConection(socket);
			}
		}
	}


	@Override
	public void run() {
		mensaje m = null;
		boolean getState = false;
		for (InetAddress IA: listaPares){
			try {
				if (IA.isReachable(this.serverPort)) {
					this.socket = new Socket (IA , this.serverPort);
					outputChannel = new ObjectOutputStream(this.socket.getOutputStream());
					inputChannel = new ObjectInputStream(this.socket.getInputStream());			
					m = new mensaje("GET",this.nombreArchivo);
					this.enviar(m);
					m = (mensaje) this.recibir();
					if(m.getTipo().equals("OK")) {
						this.setFileData((byte[]) m.getBody());
						getState = true;
						break;
					}
					this.closeConection(socket);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
