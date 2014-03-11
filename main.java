import java.util.Scanner;
import java.util.Arrays;
public class main {
  static utils util = new utils();
  public static void exit() {
    System.out.println("Usage: java main.java <directory> <words> [-explain -help].");
    System.exit(0);
  }
 public static void main(String[] args) {
  if (Arrays.asList(args).contains("-help")) {
    exit(); 
  }
  int length = args.length;
  if (length <= 0) {
    util = new utils();
  }
  else if (length == 1) {
    try {
      int words = Integer.parseInt(args[0]);
      util = new utils(words);
    } catch (Exception e) {
      String dir = args[0];
      util = new utils(dir);
    }
    
  }
  else {
    String dir = args[0];
    try {
      int words = Integer.parseInt(args[1]);
      util = new utils(words, dir);
    } catch (NumberFormatException e) {
      if (!(args[1].equals("-explain") || args[1].equals("-help"))) {
        exit();
      }
      else {
        util = new utils(dir);
      }
      
    }
  }
  if (Arrays.asList(args).contains("-explain")) {
     System.out.println("The files being read are in the example directory");
     System.out.println("There are 4 files, dog.txt, dog2.txt, cat.txt, and nobody.txt");
     System.out.println("They have been compared by finding the cosine similarity between the documents");
     System.out.println("The document is broken up into an array of words.");
     System.out.println("Consider dog.txt and cat.txt:");
     System.out.println("dog.txt contains the text 'the dog ran'");
     System.out.println("cat.txt contains the text 'the cat ran'");
     System.out.println("Arrays are created");
     System.out.println("{'dog', 'ran', 'the'}");
     System.out.println("{'cat', 'ran', 'the'}");
     System.out.println("then they are merged into hashtables with the same keys");
     System.out.println("with the values being the number of times each key appears");
     System.out.println("in the corresponding string");
     System.out.println("{'cat': 0, 'dog': 1, 'ran': 1, 'the': 1}");
     System.out.println("{'cat': 1, 'dog': 0, 'ran': 1, 'the': 1}");
     System.out.println("They are then related to the vectors");
     System.out.println("{0, 1, 1, 1}, and");
     System.out.println("{1, 0, 1, 1} respectively.");
     System.out.println("The angle between these vectors is then found to determine the similarity");
     System.out.println("between the documents.");
     System.out.println("\n\n");
     if (args.length == 1) {
       exit();
     }
  }
  

   double[] results = util.everything();
   for (int i = 0; i < results.length; i++) {
     System.out.println(results[i]);
   }
  
 }
}