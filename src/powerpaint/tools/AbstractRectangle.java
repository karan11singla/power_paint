/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.tools;

import java.awt.Shape;
import java.awt.geom.RectangularShape;

/**
 * Class to create drawings which can be defined by RectangularShape in java.awt.geom.
 * @author Danielle Tucker
 * @version 2012 November
 */
public abstract class AbstractRectangle extends AbstractTool
{
  /**
   * The shape which is being drawn.
   */
  private RectangularShape my_shape;
  
  /**
   * The x value of the starting corner of the bounding box.
   */
  private int my_starting_x;
  
  /**
   * The y value of the starting corner of the bounding box.
   */
  private int my_starting_y;

  /**
   * Creates a drawing of a shape which is defined by RectangularShape on the canvas.
   * @param the_shape the shape which is drawn (see java.awt.geom)
   */
  public AbstractRectangle(final RectangularShape the_shape)
  {
    super();
    my_shape = the_shape;
  }
  
  /**
   * Start the rectangle with the x and y value as one corner.
   * @param the_x the starting x value.
   * @param the_y the starting y value.
   * @return the current rectangle.
   */
  public Shape start(final int the_x, final int the_y)
  {
    my_starting_x = the_x;
    my_starting_y = the_y;
    my_shape = (RectangularShape) my_shape.clone();
    my_shape.setFrameFromDiagonal(my_starting_x, my_starting_y, 
                                  the_x, the_y);
    return my_shape;
  }

  /**
   * Resize the rectangle with the x and y value as the new corner.
   * @param the_x the x value.
   * @param the_y the y value.
   * @return the current rectangle.
   */
  public Shape move(final int the_x, final int the_y)
  {
    my_shape.setFrameFromDiagonal(my_starting_x, my_starting_y, 
                                      the_x, the_y);
    return my_shape;
  }
  
  /**
   * Finish the rectangle with the x and y value as the new corner.
   * @param the_x the x value.
   * @param the_y the y value.
   * @return the final rectangle.
   */
  public Shape stop(final int the_x, final int the_y)
  {
    my_shape.setFrameFromDiagonal(my_starting_x, my_starting_y, 
                                      the_x, the_y);
    return my_shape;
  }
}
