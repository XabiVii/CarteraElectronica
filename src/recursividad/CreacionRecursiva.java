package recursividad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class CreacionRecursiva {
	
	public CreacionRecursiva() {
	}
	
	public void copiaSeguridadResources() {
		
		File resources= new File("resources");
		
		try {
			copiaSeguridad(resources, new File("copiaResources"));
		} catch (IOException e) {
			System.out.println(String.format("Error con los archivos %s", e));
		}
	}
	
	private void copiaSeguridad(File carpetaOriginal, File carpetaBackup) throws IOException {
	    File[] archivos = carpetaOriginal.listFiles();
	    if (archivos != null) {
	        for (File archivo : archivos) {
	            if (archivo.isDirectory()) {
	            	//Si el objeto tipo file es un directorio crea una nueva carpeta en la direccion de la copia
	            	File carpetaNueva = new File(carpetaBackup, archivo.getName());
	                carpetaNueva.mkdirs();
	                copiaSeguridad(archivo, carpetaNueva);//LLama al metodo recursivo dentro de la carpeta a copiar.
	            } else {
	            	File copia=new File(carpetaBackup, archivo.getName());
	            	if(copia.exists()) {//si en la carpeta a copiar existe el archivo primero lo borra y luego hace una nueva copia
	            		copia.delete();
	            		copia=new File(carpetaBackup, archivo.getName()); 
	            	}
	                Files.copy(archivo.toPath(), copia.toPath());
	            }
	        }
	    }
	}

}
