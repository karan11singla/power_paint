/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.tools;

import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * A mouse adapter that listens for clicks creates lines
 * appropriately.
 * 
 * @author Danielle Tucker
 * @version 2012 November
 */
public class LineTool extends AbstractTool
{
  /**
   * The current path being worked on.
   */
  private Line2D my_line;

  /**
   * The x value of the start of the line drawing.
   */
  private int my_starting_x;
  
  /**
   * The y value of the start of the line drawing.
   */
  private int my_starting_y;
  
  /**
   * Constructor for the Line Tool.
   */
  public LineTool()
  {
    super();
    my_starting_x = 0;
    my_starting_y = 0;
    my_line = new Line2D.Double();
  }
  
  /**
   * Begins creating the line.
   * @param the_x the x value where to start the line.
   * @param the_y the y value where to start the line.
   * @return the current line
   */
  public Shape start(final int the_x, final int the_y)
  {
    my_starting_x = the_x;
    my_starting_y = the_y;
    my_line = new Line2D.Double(my_starting_x, my_starting_y, my_starting_x, my_starting_y);
    return  my_line;
  }
  
  /**
   * Changes the line being made by this tool.
   * @param the_new_x the x value where to draw the line to.
   * @param the_new_y the y value where to draw the line to.
   * @return the current line
   */
  public Shape move(final int the_new_x, final int the_new_y)
  {
    my_line.setLine(my_starting_x, my_starting_y, the_new_x, the_new_y);
    return my_line;
  }

  /**
   * Changes the line being made by this tool.
   * @param the_final_x the x value where to draw the line to.
   * @param the_final_y the y value where to draw the line to.
   * @return the finished line.
   */
  public Shape stop(final int the_final_x, final int the_final_y)
  {
    my_line.setLine(my_starting_x, my_starting_y, the_final_x, the_final_y);
    return my_line;
  }
}
