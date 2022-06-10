package SDyPP.PaperLab.utils;

import java.util.Date;

public class fileInfo {
	private String virtualPath;
	private String path;
	private String nombre;
	private Date fecha;
	private boolean local;
	
	public String getVirtualPath() {
		return virtualPath;
	}
	public void setVirtualPath(String virtualPath) {
		this.virtualPath = virtualPath;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(long fecha) {
		Date date = new Date(fecha);
		this.fecha = date;
	}
    public boolean isLocal() {
		return local;
	}
	public void setLocal(boolean local) {
		this.local = local;
	}
	 
}
