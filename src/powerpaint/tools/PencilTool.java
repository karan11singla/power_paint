/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.tools;

import java.awt.Shape;
import java.awt.geom.GeneralPath;

/**
 * A mouse adapter that listens for clicks and creates curves
 * appropriately.
 * 
 * @author Danielle Tucker
 * @version 2012 November
 */
public class PencilTool extends AbstractTool
{
  /**
   * The current path being worked on.
   */
  private GeneralPath my_path;

  /**
   * Starts the curve being made by this tool.
   * @param the_x the x value where to start the curve.
   * @param the_y the y value where to start the curve.
   * @return the pencil shape.
   */
  public Shape start(final int the_x, final int the_y)
  {
    my_path = new GeneralPath();
    my_path.moveTo(the_x, the_y);
    return my_path;
  }

  /**
   * Changes the curve being made by this tool.
   * @param the_x the x value where to move the curve.
   * @param the_y the y value where to move the curve.
   * @return the current pencil shape.
   */
  public Shape move(final int the_x, final int the_y)
  {
    my_path.lineTo(the_x, the_y);
    return my_path;
  }

  /**
   * Finishes the curve being made by this tool.
   * @param the_x the x value where to finish the curve.
   * @param the_y the y value where to finish the curve.
   * @return the finished pencil shape.
   */
  public Shape stop(final int the_x, final int the_y)
  {
    my_path.lineTo(the_x, the_y);
    return my_path;
  }
}