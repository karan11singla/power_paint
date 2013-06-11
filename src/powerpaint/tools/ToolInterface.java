/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.tools;

import java.awt.Shape;

/**
 * Interface for Tool Object functionality.
 * @author Danielle Tucker
 * @version 2012 November
 *
 */
public interface ToolInterface
{
  /**
   * Starts creating the shape for the drawing made by this tool.
   * @param the_x the x_position of the starting point
   * @param the_y the y_position of the starting point
   * @return the shape which is created.
   */
  Shape start(final int the_x, final int the_y);

  /**
   * Changes the shape for the drawing made by this tool.
   * @param the_x the x_position of the move
   * @param the_y the y_position of the move
   * @return the changed shape after the move
   */
  Shape move(final int the_x, final int the_y);

  /**
   * Completes the shape for the drawing made by this tool.
   * @param the_x the final x position
   * @param the_y the final y position
   * @return the completed shape for this tool
   */
  Shape stop(final int the_x, final int the_y);

  /**
   * Provides a Name of the Tool for use on Buttons.
   * 
   * @return the name.
   */
  String getName();
}
