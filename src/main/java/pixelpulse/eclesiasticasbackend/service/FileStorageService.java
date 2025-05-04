package pixelpulse.eclesiasticasbackend.service;

import org.springframework.stereotype.Service;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class FileStorageService {

    public String crearDirectorioActas() throws Exception {
        String userHome = System.getProperty("user.home");
        String docsPath = userHome + "/documentos/actas/";
        Path path = Paths.get(docsPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
        }
        return docsPath;
    }

    public String generarRutaArchivo(String prefijoNombre, String... nombres) {

        String nombreArchivo = String.join("_", nombres) + ".pdf";
        return prefijoNombre + nombreArchivo;
    }
}