package recursividad;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CreacionRecursiva {
	
	public CreacionRecursiva() {
		// TODO Auto-generated constructor stub
	}
	
	public void copiaSeguridadResources() {
		
		File resources= new File("resources");
		
		try {
			copiaSeguridad(resources, new File("copiaResources"));
		} catch (IOException e) {
			System.out.println(String.format("Error con los archivos %s", e));
		}
	}
	
	public void copiaSeguridad(File carpetaOriginal, File carpetaBackup) throws IOException {
	    File[] archivos = carpetaOriginal.listFiles();
	    if (archivos != null) {
	        for (File archivo : archivos) {
	            if (archivo.isDirectory()) {
	                // Crear carpeta en la nueva ubicación
	                File carpetaNueva = new File(carpetaBackup, archivo.getName());
	                carpetaNueva.mkdirs();
	                copiaSeguridad(archivo, carpetaNueva); // Llamada recursiva
	            } else {
	                // Copiar archivo
	                Files.copy(archivo.toPath(), new File(carpetaBackup, archivo.getName()).toPath());
	            }
	        }
	    }
	}

}
