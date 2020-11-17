package gilko.marcin.bookstore;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException{
		Path uploadPath = Paths.get(uploadDir);
		
		if(Files.exists(uploadPath)) {
			
			File file = new File(uploadPath.toString());
			File[] files = file.listFiles();
			for(File f:files)
			{
				Files.delete(Paths.get(f.getPath()));
			}
			Files.delete(uploadPath);			
		}
		
		if(!Files.deleteIfExists(uploadPath)) {
			Files.createDirectories(uploadPath);
		}
		try(InputStream inputStream = multipartFile.getInputStream()){
			Path filePath = uploadPath.resolve(fileName);
			Files.copy(inputStream,  filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch(IOException ioe) {
			throw new IOException("Nie można zapisać zdjecia: " + fileName, ioe);
		}
	}
}
