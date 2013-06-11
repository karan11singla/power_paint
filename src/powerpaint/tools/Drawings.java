/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.tools;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.Stroke;

/**
 * A class to contain all information of a shape to be drawn.
 * @author Danielle Tucker
 * @version 2012 November
 */
public class Drawings
{
  /**
   * The shape shape which is drawn.
   */
  private final Shape my_shape;
  
  /**
   * The color of the shape.
   */
  private final Color my_color;
  
  /**
   * The stroke width of the pen used to make the drawing.
   */
  private final Stroke my_stroke_width;
  
  /**
   * Constructor to create a Drawing.
   * @param the_shape the Shape of the drawing.
   * @param the_color the Color of the drawing.
   * @param the_stroke_width the Stroke width of the pen use to make the drawing.
   */
  public Drawings(final Shape the_shape, final Color the_color, final float the_stroke_width)
  {
    my_shape = the_shape;
    my_color = the_color;
    my_stroke_width = new BasicStroke(the_stroke_width); 
  }

  /**
   * Return current Shape.
   * @return current Shape.
   */
  public Shape getShape()
  {
    return my_shape;
  }
  
  /**
   * Return current color.
   * @return current color.
   */
  public Color getColor()
  {
    return new Color(my_color.getRGB());
  }

  /**
   * Return current pen width.
   * @return current Stroke.
   */
  public Stroke getStrokeWidth()
  {
    return my_stroke_width;
  }

}
