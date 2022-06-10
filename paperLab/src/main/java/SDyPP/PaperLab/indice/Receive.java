package SDyPP.PaperLab.indice;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import SDyPP.PaperLab.utils.*;


public class Receive {

    private final static String QUEUE_NAME =  "paperlab-example";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.18.37");
       // factory.setPort(15672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
       // channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
        
        
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        	
        	mensaje msj = new mensaje();
        	
        	msj =msj.fromBytes(delivery.getBody());
           // String message = new String(body, "UTF-8");
        //    System.out.println("recibi mensaje de:"+msj.getOrigen());
          //  String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received '"  +msj.getOrigen() + "'");
            ArrayList<String> d= msj.getDirectorio();
			for (String file : d) {
				  System.out.println(" [x] file '"  +file +"'");
				//msj.addFile(file.getVirtualPath());
			}
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        /*try {
           
            System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
            int qMessages = (channel.queueDeclarePassive(QUEUE_NAME).getMessageCount());
            System.out.println("recibi mensaje:"+qMessages);
           if (qMessages==0) {
			
			}else {
            GetResponse data = channel.basicGet(QUEUE_NAME, true);
            mensaje msj = new mensaje();
        	msj = msj.fromBytes(data.getBody());
        	 System.out.println("recibi mensaje de:"+msj.getOrigen());
          /*  Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                	mensaje msj = new mensaje();
                	msj =msj.fromBytes(body);
                   // String message = new String(body, "UTF-8");
                    System.out.println("recibi mensaje de:"+msj.getOrigen());
                }
            };

          //  channel.basicConsume(QUEUE_NAME, true, consumer);
			}
            Thread.sleep(20000);
        } finally {
            channel.close();
            connection.close();
        }*/
    }
}