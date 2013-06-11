/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.tools;

import java.awt.geom.Rectangle2D;

/**
 * Creates a tool which is creates rectangles.
 * @author Danielle Tucker
 * @version 2012 November
 */
public class RectangleTool extends AbstractRectangle
{
  /**
   * Constructor for the Tool which makes a 2D Rectangle.
   */
  public RectangleTool()
  {
    super(new Rectangle2D.Double());
  }
}
