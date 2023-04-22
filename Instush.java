import java.awt.Color;
        /*
           Assignment number : 7
           File Name : instush .java
           Name: Ran Zaaroor
           Student ID : 290374040
           Email : Ran.zaaroor@gmail.com
       */
/**
 * A library of image processing functions.
 */
public class Instush {
	
	public static void main(String[] args) {
		Color[][] image = read("ironman.ppm"); 
		Color [][] image2 = read("xmen.ppm");
		morph(image, image2, 5);
	}

	/**
	 * Returns an image created from a given PPM file.
	 * SIDE EFFECT: Sets standard input to the given file.
	 * @return the image, as a 2D array of Color values
	 */
	public static Color[][] read(String filename) {
		StdIn.setInput(filename);
		// Reads the PPM file header (ignoring some items)
		StdIn.readString();
		int numRows = StdIn.readInt();
		int numCols = StdIn.readInt();
		StdIn.readInt();
		// Creates the image
		Color[][] image = new Color[numCols][numRows];
		for(int i = 0; i < numCols; i ++){
			for(int j = 0; j < numRows; j++){
				int r = StdIn.readInt();
				int g = StdIn.readInt();
				int b = StdIn.readInt();
				image[i][j] = new Color(r,g,b);
				
			}
		}
			return image;
	}

	/**
	 * Prints the pixels of a given image.
	 * Each pixel is printed as a triplet of (r,g,b) values.
	 * For debugging purposes.
	 * @param image - the image to be printed
	 */
	public static void print(Color[][] image) {
		for(int i = 0; i < image.length; i ++) { 
			for(int j = 0; j < image[0].length; j ++) {
				System.out.print("(");
				System.out.printf("%3s", image[i][j].getRed());    // Prints the color's red component
				System.out.printf("%4s", image[i][j].getGreen());  // Prints the color's green component
				System.out.printf("%4s", image[i][j].getBlue());   // Prints the color's blue component
				System.out.print(") ");
			}
			System.out.println();
		}
	}
	
	/**
	 * Returns an image which is the horizontally flipped version of the given image. 
	 * @param image - the image to flip
	 * @return the horizontally flipped image
	 */
	public static Color[][] flippedHorizontally(Color[][] image) {
		Color [][] flippedImage = new Color [image.length][image[0].length];
		int jIndexImage = 0;
		for(int i = 0; i < flippedImage.length; i ++) {
			jIndexImage = 0;
			for(int j = flippedImage[0].length - 1; j >= 0; j --) {
				flippedImage[i][j] = image[i][jIndexImage];
				jIndexImage ++;
			}
		}
		return flippedImage;
	}
	
	/**
	 * Returns an image which is the vertically flipped version of the given image. 
	 * @param image - the image to flip
	 * @return the vertically flipped image
	 */
	public static Color[][] flippedVertically(Color[][] image){
		Color [][] flippedImage = new Color [image.length][image[0].length];
		int iIndexImage = 0;
		for(int j = 0; j < flippedImage[0].length; j ++) {
			iIndexImage = 0;
			for(int i = flippedImage.length - 1; i >= 0; i --) {
				flippedImage[i][j] = image[iIndexImage][j];
				iIndexImage ++;
			}
		}
		return flippedImage;
	}
	
	/**
	 * Returns the average of the RGB values of all the pixels in a given image.
	 * @param image - the image
	 * @return the average of all the RGB values of the image
	 */
	public static double average(Color[][] image) {
		// Replace the following statement with your code
		return 0.0;
	}

	/**
	 * Returns the luminance value of a given pixel. Luminance is a weighted average
	 * of the RGB values of the pixel, given by 0.299 * r + 0.587 * g + 0.114 * b.
	 * Used as a shade of grey, as part of the greyscaling process.
	 * @param pixel - the pixel
	 * @return the greyscale value of the pixel, as a Color object
	 *         (r = g = b = the greyscale value)
	 */
	public static Color luminance(Color pixel) {
		int lumR = (int) (pixel.getRed() * 0.299 + pixel.getGreen() * 0.587 + pixel.getBlue() * 0.114);
		Color lumPixel = new Color(lumR, lumR, lumR);
		//(𝑖𝑛𝑡) (0. 299 · 𝑟  +  0. 587 · 𝑔  +  0. 114 · 𝑏;
		return lumPixel;
	}
	
	/**
	 * Returns an image which is the greyscaled version of the given image.
	 * @param image - the image
	 * @return rhe greyscaled version of the image
	 */
	public static Color[][] greyscaled(Color[][] image) {
		Color [][] gsImage = new Color [image.length][image[0].length];
		for(int i = 0; i < gsImage.length; i ++) {
			for(int j = 0; j < gsImage[0].length; j++) {
				gsImage[i][j] = luminance(image[i][j]);
			}
		}
		return gsImage;
	}	
	
	/**
	 * Returns an umage which is the scaled version of the given image. 
	 * The image is scaled (resized) to be of the given width and height.
	 * @param image - the image
	 * @param width - the width of the scaled image
	 * @param height - the height of the scaled image
	 * @return - the scaled image
	 */
	public static Color[][] scaled(Color[][] image, int width, int height) {
		Color [][] scaledImage = new Color [height][width];
		for(int i = 0; i < scaledImage.length; i ++) {
			for( int j = 0; j < scaledImage[0].length; j ++) {
				scaledImage[i][j] = image[i * (image.length) / height][j * (image[0].length) / width];
			}
		}
		return scaledImage;
	}
	
	/**
	 * Returns a blended color which is the linear combination of two colors.
	 * Each r, g, b, value v is calculated using v = (1 - alpha) * v1 + alpha * v2.
	 * 
	 * @param pixel1 - the first color
	 * @param pixel2 - the second color
	 * @param alpha - the linear combination parameter
	 * @return the blended color
	 */
	public static Color blend(Color c1, Color c2, double alpha) {
		int bRed = (int) (c1.getRed() * alpha + c2.getRed() * (1 - alpha));
		int bGreen = (int) (c1.getGreen() * alpha + c2.getGreen() * (1 - alpha));
		int bBlue = (int) (c1.getBlue() * alpha + c2.getBlue() * (1 - alpha));
		Color blendedColor = new Color(bRed, bGreen, bBlue);
		return blendedColor;
	}
	
	/**
	 * Returns an image which is the blending of the two given images.
	 * The blending is the linear combination of (1 - alpha) parts the
	 * first image and (alpha) parts the second image.
	 * The two images must have the same dimensions. 
	 * @param image1 - the first image
	 * @param image2 - the second image
	 * @param alpha - the linear combination parameter
	 * @return - the blended image
	 */
	public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
		Color [][] blendedImage = new Color [image1.length][image1[0].length];
		for(int i = 0; i < blendedImage.length; i ++) {
			for(int j = 0; j < blendedImage[0].length; j ++) {	
				blendedImage[i][j] = blend(image1[i][j], image2[i][j], alpha);
			}
		}
		return blendedImage;
	}
	
	/**
	 * Morphs the source image into the target image, gradually, in n steps.
	 * Animates the morphing process by displaying the morphed image in each step.
	 * The target image is an image which is scaled to be a version of the target
	 * image, scaled to have the width and height of the source image.
	 * @param source - source image
	 * @param target - target image
	 * @param n - number of morphing steps
	 */
	public static void morph(Color[][] source, Color[][] target, int n) {
		if(source.length != target.length || source[0].length != target[0].length) 
			target = scaled(target, source[0].length, source.length);
		Color[][] morphImage;
		double alpha = 0;
		for(int i = 0; i <= n; i ++) {
			alpha = (double) (n-i) / (double) n;
			morphImage = blend(source, target, alpha);
			show(morphImage);
		}
	}
	
     /**
	 * Renders (displays) an image on the screen, using StdDraw. 
	 * 
	 * @param image - the image to show
	 */
	public static void show(Color[][] image) {
		StdDraw.setCanvasSize(image[0].length, image.length);
		int width = image[0].length;
		int height = image.length;
		StdDraw.setXscale(0, width);
		StdDraw.setYscale(0, height);
		StdDraw.show(25); 
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				// Sets the pen color to the color of the pixel
				StdDraw.setPenColor( image[i][j].getRed(),
					                 image[i][j].getGreen(),
					                 image[i][j].getBlue() );
				// Draws the pixel as a tiny filled square of size 1
				StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
			}
		}
		StdDraw.show();
	}
}


