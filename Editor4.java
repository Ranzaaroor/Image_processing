import java.awt.Color;
        /*
           Assignment number : 7
           File Name : Editor4 .java
           Name: Ran Zaaroor
           Student ID : 290374040
           Email : Ran.zaaroor@gmail.com
       */
/**
 * Demonstrates the morphing service of Instush.java, when turning an image into a greyscaled image.
 * The program recieves three command-line arguments: the name of a PPM file, and a number of morphing steps.
 * The source image (a string), and the number of morphing steps (an int, which determines how long will the mophing process take). 
 * For example:
 * java Editor3 ironman.ppm  300
 * creates a source image for "ironman.ppm", then creates a target image (the greyscaled version of the source image),
 * and does the morphing process within 300 steps.
 */
public class Editor4 {

	public static void main (String[] args) {
		String filename = args[0];	
        int iterations = Integer.parseInt(args[1]);
		Color [][] source;
		Color [][] target;
		source = Instush.read(filename);	
        target = Instush.greyscaled(source);
		Instush.morph(source, target, iterations);
	}
}
