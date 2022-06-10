package SDyPP.PaperLab.extremos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import SDyPP.PaperLab.utils.gestorDirectorio;

public class Extremo {
	private final Logger log = LoggerFactory.getLogger(Extremo.class);
	private InetAddress ipServer;
	private int portServer = 8989;
	private static final Pattern PATTERN = Pattern
			.compile("^(([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.){3}([01]?\\d\\d?|2[0-4]\\d|25[0-5])$");
	private Socket s;

	
//HILOS ACTIVOS:
	//1) P2P SERVER- PARES PIDEN ARCHIVOS         	PORT 9001
	//2) ESPERA DE MENSAJES DE INDICE           	PORT 9002
	//3) P2P CLIENT- COMO PAR PIDO ARCHIVO        	PORT ER
	//4) MENSAJE DE ESTOY ACTIVO- HILO         		PORT RABBIT
	
//-------- EXTREMO SINCRONIZANDO
	//  GENERAR CATALOGO---> DIR BASE 
	//       (ARCHIVOS PRESENTES EN LA CARPETA)
	//   PEDIR CATALOGO DE LA RED
	// ANT = ARCHIVOS FALTAN Y VIEJOS 
	// PEDIR INFO DE ANT'S Y ENVIAR ARCHIVOS OFRECIDOS
	// CONECTARSE A PEERS PIDIENDO ANT'S
	
//----------FIN FASE INICIO------------------
	
//************ CADA 20s ************	
// MENSAJE DE ESTOY ACTIVO A INDICE
	
//USER PIDE ARCHIVO QUE NO ESTA LOCAL
	//PROCESO DE PEDIR ARCHIVO Y DESCARGAR DESDE PAR 
	//AVISAR A INDICE DE PROCESO OK
	
//INDICE PIDE ARCHIVO QUE NO ESTA LOCAL
	//PROCESO DE DESCARGAR DESDE PAR 	
	//AVISAR A INDICE DE PROCESO OK	
	
	//VARIABLES
		// DIRECTORIO RAIZ
		// CATALOGO


	public Extremo() {

	}

	public void inicializarAPP() {
		
	}
	
	
	public static void main(String[] args) throws Exception{

		Extremo stcp = new Extremo();

	}

	

}
