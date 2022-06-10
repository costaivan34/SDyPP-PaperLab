package SDyPP.PaperLab.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class mensaje implements Serializable{
	private String tipo;
	private Object body;
	
	
	public mensaje() {
		super();
	}

	public mensaje(String tipo, Object body) {
		super();
		this.tipo = tipo;
		this.body = body;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
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
