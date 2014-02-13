//Utils class for DocSim
//By Mark Klein
import java.io.*;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Utils {

	/**
	* @return    an array containing all doc files in the user's current directory
	*/
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

	/**
	* @param files    the array of files to be read
	* @return         a two-dimensional array containing the different lines from each file.
	*/
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

	/**
	* @param contents    the array of strings to be joined into one string
	* @return            the string produced after joining the array of strings separated by a space
	*/
	public static String join(String[] contents) {
		String content = StringUtils.join(contents, " ");
		return content;
	}

	/**
	* @param contents    the two-dimensional array whose elements should be joined into single strings
	* @return            an array containing joined strings 
	*/
	public static String[] join(String[][] contents) {
		String[] newContents = new String[contents.length];
		for (int i = 0; i < contents.length; i++) {
			newContents[i] = join(contents[i]);
		}
		return newContents;
	}

	/**
	* @param str    the string whose non-alphanumeric characters should be stripped
	* @return       a string containing only alphanumeric characters 
	*/
	public static String removePunctuation(String str) {
		return str.replaceAll("[^A-Za-z0-9]", "");
	}

	/**
	* @param str    the string to be split up into an array
	* @param n      the number of words to be included in each split
	* @return       a new string split every nth word
	*/
	public static String[] split(String str, int n) {
		String[] strings = java.util.Arrays.toString(s.split("(?<=\\G...)"));
		return strings
	}


}