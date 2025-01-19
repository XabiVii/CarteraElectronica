package recursividad;

import java.io.File;
import java.io.IOException;

public class CreacionRecursiva {
	public void hacerCopiaDeSeguridad(File carpetaOriginal, File carpetaBackup) throws IOException {
	    File[] archivos = carpetaOriginal.listFiles();
	    if (archivos != null) {
	        for (File archivo : archivos) {
	            if (archivo.isDirectory()) {
	                // Crear carpeta en la ubicación de respaldo
	                File carpetaNueva = new File(carpetaBackup, archivo.getName());
	                carpetaNueva.mkdirs();
	                hacerCopiaDeSeguridad(archivo, carpetaNueva); // Llamada recursiva
	            } else {
	                // Copiar archivo
	                Files.copy(archivo.toPath(), new File(carpetaBackup, archivo.getName()).toPath());
	            }
	        }
	    }
	}

}
