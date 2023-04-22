import java.awt.Color;
        /*
           Assignment number : 7
           File Name : Editor3 .java
           Name: Ran Zaaroor
           Student ID : 290374040
           Email : Ran.zaaroor@gmail.com
       */
/**
 * Demonstrates the morphing service of Instush.java. 
 * The program recieves three command-line arguments: the name of a PPM file
 * that represents the source image (a string), the name of a PPM file that represents
 * the target image (a string), and the number of morphing steps (an int). 
 * For example:
 * java Editor3 cake.ppm ironman.ppm 300
 * If the two images don't have the same dimensions, the program scales the target image
 * to the dimensions of the source image.
 */
public class Editor3 {

	public static void main (String[] args) {
		String filename1 = args[0];
		String filename2 = args[1];
		int iterations = Integer.parseInt(args[2]);		
		Color [][] source;
		Color [][] target;
		source = Instush.read(filename1);	
		target = Instush.read(filename2);
		Instush.morph(source, target, iterations);
	}
}
