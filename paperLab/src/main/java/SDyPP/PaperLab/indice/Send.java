package SDyPP.PaperLab.indice;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import SDyPP.PaperLab.utils.*;

public class Send {
	public static gestorDirectorio Directorio;
	private static final String QUEUE_NAME = "paperlab-example";
	public static String rootDirectory;

	public static void main(String[] argv) throws Exception {
		System.out.println("config3urado");
		// System.out.println("Directorio:" + Directorio.listarDir());
		// ****RabbitMQ Connection****
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.18.37");
		factory.setUsername("admin");
		factory.setPassword("admin");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();
		// ****RabbitMQ Connection****
		try {
			// channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			mensaje msj = new mensaje();
			msj.setOrigen("192.168.18.10");
			rootDirectory = "C:\\Users\\user\\Pictures\\FOTOS";
			Directorio = new gestorDirectorio(rootDirectory);
			Directorio.generarDir();
			ArrayList<fileInfo> d= Directorio.getDirectorio();
			for (fileInfo file : d) {
				msj.addFile(file.getVirtualPath());
			}
			//msj.addFile(String.format("Hello World at %s", LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)));
			channel.basicPublish("", QUEUE_NAME, null, msj.toBytes());
			Thread.sleep(1500);
		} finally {
			channel.close();
			connection.close();
		}
	}
}