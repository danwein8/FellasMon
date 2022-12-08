import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileActions {
	
	
	public static int getCount(String name, String ext, String animation) {
		int counter =0;
		String path;
		Path p;
		while(true) {
			//this assumes files have a zero in their naming convention.
			
			path = String.format("%s_%s_%d.%s", name, animation, counter, ext);
			p = Paths.get(System.getProperty("user.dir"),"src", path);
			if(Files.exists(p)) counter +=1;
			else return counter;
		}	
	}
	
}
