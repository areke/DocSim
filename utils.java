//Utils class for DocSim
//By Mark Klein
import java.io.*;
import java.util.Set;
import java.util.Hashtable;
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
		String dots = "";
		for (int i = 0; i < n; i++) {
			dots += ".";
		}
		String[] strings = java.util.Arrays.toString(s.split("(?<=\\G"+dots+")"));
		return strings;
	}

	/**
	* @param contents    the array of strings to be made into a hashtable
	* @return            a hashtable with each distinct word in contents as a key and the number of times 
	*                    that word appears as its value
	*/
	public static Hashtable<String, Integer> createHash(String[] contents) {
		Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
		for (int i = 0; i < contents.length; i++) {
			String key = contents[i];
			if (numbers.containsKey(key)) {
				numbers.put(key, numbers.get(key) + 1);
			}
			else {
				numbers.put(key, 1);
			}
		}
		return numbers;
	}

	/**
	* @param a    a hashtable to be merged
	* @param b    a hashtable to be merged
	* @return     the merged hashtable
	*/
	public static Hashtable<String, Int> merge(Hashtable<String, int> a, Hashtable<String, int> b) {
		Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
		Set<String> aKeys = a.keySet();
		Set<String> bKeys = b.keySet();
		for (String key : aKeys) {
			int value = aKeys.get(key);
			numbers.put(key, value);
		}
		for (String key : bKeys) {
			int value = bKeys.get(key);
			if (!numbers.containsKey(key)) {
				numbers.put(key, 0);
			}
		}
		return numbers;
	}

	/**
	* @param a    a hastable of strings with their values
	* @return     an array of integers
	*/
	public static int[] toArray(Hashtable<String, int> a) {
		int[] numbers = new int[a.size()];
		Set<String> keys = a.keySet();
		int counter = 0;
		for (String key : keys) {
			int value = keys.get(key);
			numbers[counter] = value;
			counter++;
		}
		return numbers;

	}

	// Move to a different file later 
	public static String everything(String[][] contents) {
	}

}