/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.listeners;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

/**
 * A class to make colored rectangles to be used as icons.
 * 
 * @author Daniel M. Zimmerman
 * @author TCSS305B Class
 * @version 2012 October
 */
public final class ColorRectangle extends BufferedImage
{
  /**
   * The number of color channels.
   */
  private static final int COLOR_CHANNELS = 3;
  

  /**
   * Constructs a new colored rectangle.
   * 
   * @param the_width The width.
   * @param the_height The height.
   * @param the_color The color.
   */
  public ColorRectangle(final int the_width, final int the_height, 
                        final Color the_color)
  {
    super(the_width, the_height, BufferedImage.TYPE_INT_RGB);
    
    final int[] pixel_values = new int[COLOR_CHANNELS];
    final WritableRaster wr = getRaster();

    for (int row = 0; row < wr.getHeight(); row++)
    {
      for (int col = 0; col < wr.getWidth(); col++)
      {
        pixel_values[0] = the_color.getRed();
        pixel_values[1] = the_color.getGreen();
        pixel_values[2] = the_color.getBlue();
        wr.setPixel(col, row, pixel_values);
      }
    }
  }
}
