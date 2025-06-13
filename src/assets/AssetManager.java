package assets;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public class AssetManager {

	private static final Map<String, Font> fontCache = new HashMap<>();
	private static final Map<String, BufferedImage> imageCache = new HashMap<>();
	
	public static Font loadFont(float size) {
	    return loadFont(size, "powergreen");
	}
	
	public static Font loadFont(float size, String fontName) {
		String cacheKey = "font_" + size + "_" + fontName;
		if (fontCache.containsKey(cacheKey)) {
			 return fontCache.get(cacheKey);
		}

		try (InputStream is = AssetManager.class.getResourceAsStream("/fonts/" + fontName + ".ttf")) {
			 if (is == null) {
				  System.err.println("Could not find font file");
				  return null;
			 }

			 Font customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(size);
			 GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			 ge.registerFont(customFont);
			 fontCache.put(cacheKey, customFont);
			 return customFont;
		} catch (IOException e) {
			 System.err.println("Error loading font: " + e.getMessage());
		} catch (FontFormatException e) {
			 System.err.println("Invalid font format: " + e.getMessage());
		}
		return null;
  }

	public static BufferedImage loadImage(String path) {
		if (imageCache.containsKey(path)) {
			 return imageCache.get(path);
		}

		try {
			 InputStream is = AssetManager.class.getResourceAsStream(path);
			 if (is == null) {
				  System.err.println("Could not find image at path: " + path);
				  return null;
			 }
			 BufferedImage image = ImageIO.read(is);
			 imageCache.put(path, image);
			 return image;
		} catch (IOException e) {
			 System.err.println("Error loading image: " + path);
			 return null;
		}
  }

	public static BufferedImage getSprite(BufferedImage spriteSheet, int x, int y, int width, int height) {
		return spriteSheet.getSubimage(x, y, width, height);
	}

}
