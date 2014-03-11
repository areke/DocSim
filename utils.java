//Utils class for DocSim
//By Mark Klein
import java.io.*;
import java.util.Set;
import java.util.Hashtable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;
import java.util.Iterator;

public class utils {
 private static String dir = "docs";
 private static int words = 1;
 /**
 * @return    an array containing all doc files in the user's current directory
 */
 public utils() {
 }
 public utils(int i) {
   if (i<1) {
     words = 1;
   }
   else if (i>10) {
     words = 10;
   }
   else {
     words = i;
   }
    
 }
 public utils(String a) {
   if (Character.toString(a.charAt(0)) == "/") {
     dir = a.substring(1, a.length());
   }
   else {
     dir = a;
   }
    
 }
 public utils(int i, String a) {
   if (Character.toString(a.charAt(0)) == "/") {
     dir = a.substring(1, a.length());
   }
   else {
     dir = a;
   }
   if (i<1) {
     words = 1;
   }
   else if (i>10) {
     words = 10;
   }
   else {
     words = i;
   }
    
 }
 public static File[] getFiles() {
     String[] extensions = {"doc", "txt", "docx", "rtf"};
     String directory = System.getProperty("user.dir")+"/"+dir;
     File directoryContents = new File(directory);
     File[] filesList = directoryContents.listFiles();
     File[] files = new File[filesList.length];
     int num = 0;
     for (int i=0; i < filesList.length; i++) {
       String[] nameParts = filesList[i].getName().split("\\.");
       if (Arrays.asList(extensions).contains(nameParts[1].toLowerCase())) {
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
 //join(String array,delimiter)
public static String join(String[] contents)
{
        if (contents.length == 0) return "";
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<contents.length; i++) {
            sb.append(contents[i]+" ");
        }
        return sb.toString();
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
  return str.replaceAll("[^A-Za-z0-9 ]", "");
 }

 /**
 * @param str    the string to be split up into an array
 * @param n      the number of words to be included in each split
 * @return       a new string split every nth word
 */
 public static String[] split(String str, int n) {
   if (n < 1 || n > 10) {
     n = 1;
   }
  String[] strings = str.split(" ");
  String[] content = new String[strings.length/n];
  int counter = 0;
  for (int i = 0; i<content.length; i+=n) {
    for (int a = 0; a < n; a++) {
      if (!strings[i].equals("") && strings[i] != null) {
        content[i] += strings[i+a]+" ";
        content[i] = content[i].replaceAll("null","");
        counter++;
      }
    }

    content[i] = content[i].substring(0, content[i].length()-1);
  }
  for (int i = 0; i < counter%n; i++) {
    content[i] += "a ";
  }
  return content;
 }
 


 /**
 * @param contents    the array of strings to be made into a hashtable
 * @return            a hashtable with each distinct word in contents as a key and the number of times 
 *                    that word appears as its value
 */
 public static Hashtable<String, Integer> createHash(String[] contents) {
  Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
   for (String content : contents) {
     System.out.println(content);
   }
  for (int i = 0; i < contents.length; i++) {
   String key = contents[i];
   if (!key.equals("") && key != null) {
     if (numbers.containsKey(key)) {
       numbers.put(key, numbers.get(key) + 1);
     }
     else {
       numbers.put(key, 1);
     }
   }
  }
  return numbers;
 }

 /**
 * @param a    a hashtable to be merged
 * @param b    a hashtable to be merged
 * @return     the merged hashtable
 */

 public static Hashtable<String, Integer> merge(Hashtable<String, Integer> a, Hashtable<String, Integer> b) {
  Hashtable<String, Integer> numbers = new Hashtable<String, Integer>();
  Set<String> aKeys = a.keySet();
  Set<String> bKeys = b.keySet();
  for (String key : aKeys) {
   int value = a.get(key);
   numbers.put(key, value);
  }
  for (String key : bKeys) {
   int value = b.get(key);
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
   
 public static int[] toArray(Hashtable<String, Integer> a) {
  int[] numbers = new int[a.size()];
  List<String> tmp = Collections.list(a.keys());
    Collections.sort(tmp);
    Iterator<String> it = tmp.iterator();
    int counter = 0;
    while(it.hasNext()){
        String element = it.next();
         int value = a.get(element);
         numbers[counter] = value;
         counter++;
    }
  return numbers;

 }

 /**
 * @return the results of comparing every document in the folder.
 */
 public static double[] everything() {
   File[] files = getFiles();
   String[][] readfiles = readFiles(files);
   String[] content = new String[readfiles.length];
   String[][] strings = new String[readfiles.length][10000];
   for (int i = 0; i < readfiles.length; i++) {
       content[i] = join(readfiles[i]);
       content[i] = removePunctuation(content[i]);
       strings[i] = split(content[i], words);
   }
   double[] results = new double[(content.length*(content.length-1)/2)];
   int counter = 0;
   String[][] stuffs = new String[results.length][2];
   for (int i = 0; i < content.length; i++) {
     for (int k = i+1; k < content.length; k++) {
       Hashtable<String, Integer> a = createHash(strings[i]);
       Hashtable<String, Integer> b = createHash(strings[k]);
       Hashtable<String, Integer> mergeda = merge(a, b);
       Hashtable<String, Integer> mergedb = merge(b, a);
       int[] arra = toArray(mergeda);
       int[] arrb = toArray(mergedb);
       DocMath doc = new DocMath();
       double cos = doc.cosSimilarity(arra, arrb);
       results[counter] = cos;
       counter++;

     }
   }
   return results;
 }

}