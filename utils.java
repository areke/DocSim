//Utils class for DocSim
//By Mark Klein
import java.io.*;
import java.util.HashMap;
public Class utils {
	public static File[] getFiles(String[] extensions) {
	    String directory = System.getProperty("user.dir");
	    File directoryContents = new File(directory);
	    File[] filesList = directoryContents.listFiles();
	    File[] files = new File[filesList.length];
	    int num = 0;
	    for (int i=0; i < filesList.length; i++) {
	      String[] nameParts = filesList[i].getName().split("\\.");
	      if (extensions.indexOf(nameParts[1].toLowerCase()) != -1) {
	        files[num] = filesList[i];
	        num+=1;
	      }
	    }
	    File[] onlyFiles = new File[num];
	    for (int i=0; i < num; i++) {
	    	onlyFiles[i] = files[i]
	    }
	    return onlyFiles;
	}
	public static void getFiles (File[] files) {

	}
}