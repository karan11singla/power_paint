/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.tools;

import java.awt.geom.Ellipse2D;

/**
 * A class for creating a drawing of an Ellipse.
 * @author Danielle Tucker
 * @version 2012 November
 */
public class Ellipse extends AbstractRectangle
{
  /**
   * Constructs a tool which makes a 2D ellipse.
   */
  public Ellipse()
  {
    super(new Ellipse2D.Double());
  }
}
