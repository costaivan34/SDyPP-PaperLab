package SDyPP.PaperLab.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class container implements Serializable{
	private String origen;
	private ArrayList<String> directorio = new ArrayList();
	
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public ArrayList<String> getDirectorio() {
		return directorio;
	}
	/*public void setDirectorio(ArrayList<String> directorio) {
		this.directorio = directorio;
	}*/
	
	public void addFile(String virtualPath ) {
		this.directorio.add(virtualPath);
		
	}
	
	public byte[] toBytes() {
	      byte[]bytes = null; 
	      ByteArrayOutputStream baos = new ByteArrayOutputStream(); 
	      try{ 
	        ObjectOutputStream oos = new ObjectOutputStream(baos); 
	        oos.writeObject(this); 
	        oos.flush();
	        oos.reset();
	        bytes = baos.toByteArray();
	        oos.close();
	        baos.close();
	      } catch(IOException e){ 
	       
	      }         
	      return bytes; 
	    }
	
	public mensaje fromBytes(byte[] body) {
		mensaje obj = null;
	    try {
	        ByteArrayInputStream bis = new ByteArrayInputStream (body);
	        ObjectInputStream ois = new ObjectInputStream (bis);
	        obj = (mensaje)ois.readObject();
	        ois.close();
	        bis.close();
	    }
	    catch (IOException e) {
	        e.printStackTrace();
	    }
	    catch (ClassNotFoundException ex) {
	        ex.printStackTrace();
	    }
	    return obj;     
	}
	
}
