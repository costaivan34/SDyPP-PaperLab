package SDyPP.PaperLab.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

public class prueba {
	private String pathTo;
	private String nombreA;
	private String path;

	public prueba(String pathTo, String nombreA, String path) {
		super();
		this.pathTo = pathTo;
		this.nombreA = nombreA;
		this.path = path;
		System.out.println("ARRAY:");
		File fRuta = new File(this.path+this.nombreA);
		if (fRuta.isFile()) {
			
			try {
				byte[] array = Files.readAllBytes(fRuta.toPath());
				System.out.println("ARRAY:"+ array);
				escribirArchivo(array,this.pathTo+this.nombreA);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public  boolean escribirArchivo(byte[] fileBytes, String archivoDestino){
		   boolean correcto = false;
		   try {
		     OutputStream out = new FileOutputStream(archivoDestino);
		     out.write(fileBytes);
		     out.close();        
		     correcto = true;
		   } catch (Exception e) {
		     e.printStackTrace();
		   }        
		     return correcto;

		}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		prueba archivo = new prueba("C:\\Users\\user\\Desktop\\","PFC_Manuel_Caballo_Gil.pdf","C:\\Users\\user\\Documents\\");
	}

}
