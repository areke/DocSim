//Utils class for DocSim
//By Mark Klein
import java.io.*;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public Class utils {
	public static File[] getFiles() {
		String[] extensions = {"doc", "txt", "docx", "rtf"}
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
	    	onlyFiles[i] = files[i];
	    }
	    return onlyFiles;
	}
	public static String[][] readFiles(File[] files) {
		//10000 line limit per file
		String[][] contents = new String[files.length][10000];
		for (int i = 0; i < files.length; i++) {
			BufferedReader in = null;
			try {
				String line; 
				in = new BufferedReader(new FileReader(files[i]));
				int counter = 0;
				while ((line = in.readLine()) != null) {
					contents[i][counter] = line;
				}
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			finally {
				try {
					if (in != null) {
						in.close();
					}
				}
				catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
		return contents;
	}
	public static String join(String[] contents) {
		String content = StringUtils.join(contents, " ");
	}

	public static String[] join(String[][] contents) {
		String[] newContents = new String[contents.length];
		for (int i = 0; i < contents.length; i++) {
			newContents[i] = join(contents[i]);
		}
		return newContents;
	}

}