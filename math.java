//Math class for DocSim
//By Mark Klein
import Java.lang.Math;

public class DocMath {

	/**
	* @param a    an array of integers; a vector
	* @param b    a second array of integers; a second vector
	* @return     the dot product (inner product) of vectors a and b
	*/
	public static double dotProduct(int[] a, int[] b) {
		double answer = 0;
		for (int i = 0; i < a.length; i++) {
			answer += a[i] * b[i];
		}
		return answer;
	}

	/**
	* @param a    an array of integers; a vector
	* @return     the norm (magnitude) of vector a 
	*/
	public static double norm(int[] a) {
		double answer = 0;
		for (int i = 0; i< a.length; i++) {
			answer += a[i];
		}
		answer = Math.sqrt(answer);
		return answer;
	}


	/**
	* @param inner    the inner product (dot product) of two vectors
	* @param a        the norm of one vector
	* @param b        the norm of another vector
	* @return         the cosine of the angle between the two vectors
	*/
	public static double cos(double inner, double a, double b) {
		double answer = inner/(a+b);
		return answer;
	}

	/** 
	* @param a    an array of integers; a vector
	* @param b    a second array of integers; another vector
	* @return     the cosine of the angle between vectors a and b
	*/
	public static double cosSimilarity(int[] a, int[] b) {
		double inner = dotProduct(a, b);
		double normA = norm(a);
		double normB = norm(b);
		double cos = norm(inner, normA, normB);
		return cos;
	}


}