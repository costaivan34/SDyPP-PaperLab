package SDyPP.PaperLab.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class gestorDirectorio {
	private String mountPath;
	public ArrayList<fileInfo> getDirectorio() {
		return Directorio;
	}

	private ArrayList<fileInfo> Directorio = new ArrayList();

	public gestorDirectorio() {

	}

	public gestorDirectorio(String rootPath) {

		this.mountPath = rootPath;
	}

	// GENERAR DIR
	// PATH ES DIRECTORIO?
	// SI, LISTAR
	// RECORRER Y BUSCAR



	public void generarDir() {
		File[] listado = null;
		ArrayList<File> pendientes = new ArrayList();
		File carpeta = new File(mountPath);
		if (carpeta.isDirectory()) {
			listado = carpeta.listFiles();
			for (File f : listado) {
				pendientes.add(f);
				// System.out.println("size"+ f.toString());
			}
			while (pendientes.size() > 0) {
				File f1 = pendientes.get(pendientes.size() - 1);
				if (f1.isDirectory()) {
					listado = f1.listFiles();
				//	System.out.println("size"+ listado.length+"wewe"+f1.getAbsolutePath());
					for (File s : listado) {
						if (!pendientes.contains(s)) {	
					
						pendientes.add(s);	
					}
					}
				} else {
				
					fileInfo i = new fileInfo();
					i.setNombre(f1.getName());	
					i.setVirtualPath( f1.getAbsolutePath().replace(this.mountPath, ""));
					i.setPath( f1.getAbsolutePath());
					i.setFecha(f1.lastModified());
					i.setLocal(true);
					this.Directorio.add(i);
				}
				pendientes.remove(f1);
			}
		}

	}

	public String listarDir() {
		String lista = "";
		for (fileInfo file : Directorio) {
			if (file.isLocal()) {
				lista = lista + "Nombre: " + file.getNombre() + " | Path: " + file.getVirtualPath() + " | Modif: "
						+ file.getFecha()  + " |Downloaded"+ "\n";
			}else {
				lista = lista + "Nombre: " + file.getNombre() + " | Path: " + file.getVirtualPath() + " | Modif: "
						+ file.getFecha() + "\n";	
			}
			
		}
		return lista;
	}
}
