package recursividad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CreacionRecursiva {
	
	public CreacionRecursiva() {
	}
	
	public void copiaSeguridadResources() {
		
		File resources= new File("resources");
		
		try {
			copiaSeguridad(resources, new File("copiaResources"));
		} catch (IOException e) {
			System.out.println("Error con los archivos");
			System.out.println(e);
		}
	}
	
	private void copiaSeguridad(File carpetaOriginal, File carpetaCopia) throws IOException {
	    File[] archivos = carpetaOriginal.listFiles();
	    if (archivos != null) {
	        for (File archivo : archivos) {//Recorremos todos los archivos de la carpeta.
	            if (archivo.isDirectory()) {
	            	//Si el objeto tipo file es un directorio crea una nueva carpeta en la direccion de la copia
	            	File carpetaNueva = new File(carpetaCopia, archivo.getName());
	                carpetaNueva.mkdirs();
	                copiaSeguridad(archivo, carpetaNueva);//LLama al metodo recursivo dentro de la carpeta a copiar.
	            } else {//si es un archivo
	            	File copia=new File(carpetaCopia, archivo.getName());
	            	if(copia.exists()) {//si en la carpeta a copiar existe el archivo primero lo borra y luego hace una nueva copia
	            		copia.delete();
	            		copia=new File(carpetaCopia, archivo.getName()); 
	            	}
	                Files.copy(archivo.toPath(), copia.toPath());
	            }
	        }
	    }
	}

}
