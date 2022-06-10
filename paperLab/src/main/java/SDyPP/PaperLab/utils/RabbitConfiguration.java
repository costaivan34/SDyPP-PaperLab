package SDyPP.PaperLab.utils;

import com.rabbitmq.client.ConnectionFactory;

public class RabbitConfiguration {

	public static final String QUEUE_NAME = "queue";
	public static final String EXCHANGE_NAME = "exchange";

	private String host;

	private Integer port;

	private String user;

	private String password;

	private String virtualhost;

	public ConnectionFactory connectionFactory() throws Exception {
		final ConnectionFactory connectionFactory = new ConnectionFactory();
		connectionFactory.setHost(host);
		connectionFactory.setPort(port);
		connectionFactory.setUsername(user);
		connectionFactory.setPassword(password);
		connectionFactory.setVirtualHost(virtualhost);
		return connectionFactory;
	}
}
