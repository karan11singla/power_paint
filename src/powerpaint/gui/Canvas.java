/*
 * Danielle Tucker 
 * TCSS 305 - November 2012 
 * Assignment 4 - PowerPaint
 */

package powerpaint.gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import powerpaint.tools.Drawings;
import powerpaint.tools.ToolInterface;

/**
 * BLAH.
 * 
 * @author Danielle Tucker
 * @version 2012 November
 */
public class Canvas extends JPanel
{
  /**
   * The width of the panel.
   */
  public static final int WIDTH = 400;

  /**
   * The height of the panel.
   */
  public static final int HEIGHT = 300;

  /**
   * The number of pixels before a grid mark is made.
   */
  private static final int GRID_SPACING = 10;

  /**
   * The serial version UID.
   */
  private static final long serialVersionUID = 201211; // Should be private??

  /**
   * The current items on the canvas.
   */
  private final List<Drawings> my_finished_drawings;

  /**
   * The tool currently in use on the canvas.
   */
  private ToolInterface my_current_tool;
  /**
   * The color of the tool.
   */
  private Color my_color;

  /**
   * The stroke width of the pen in use.
   */
  private float my_stroke_width;

  /**
   * The current object being drawn.
   */
  private Shape my_current_drawing;

  /**
   * Whether or not the grid is visible on the canvas.
   */
  private boolean my_grid_visible;

  /**
   * Flag to set if there is a current drawing.
   */
  private boolean my_has_current;
  /**
   * Constructor to create a drawing canvas with default ink color to
   * Color.BLACK and default line width of 2f.
   */
  public Canvas()
  {
    super();
    setBackground(Color.WHITE);
    setPreferredSize(new Dimension(WIDTH, HEIGHT));
    my_color = Color.BLACK;
    my_stroke_width = 2f;
    my_finished_drawings = new ArrayList<Drawings>();
    my_grid_visible = false;
    my_has_current = false;
  }

  /**
   * Set the Shape to be added to the completed drawings.
   * 
   * @param the_shape the shape of the finished drawing.
   */
  public void setFinishedDrawing(final Shape the_shape)
  {
    my_finished_drawings.add(new Drawings(the_shape, my_color, my_stroke_width));
    my_has_current = false;
  }

  /**
   * Change the current drawing.
   * 
   * @param the_shape the shape of the current drawing.
   */
  public void setCurrentDrawing(final Shape the_shape)
  {
    my_current_drawing = the_shape;
    my_has_current = true;
  }

  /**
   * Removes all of the drawings on the canvas.
   */
  public void clearDrawings()
  {
    my_finished_drawings.clear();
    my_has_current = false;
    repaint();
  }

  /**
   * Get the tool currently in use on the canvas. May return null if none has
   * been set.
   * 
   * @return the tool currently in use on the canvas.
   */
  public final ToolInterface getTool()
  {
    return my_current_tool;
  }

  /**
   * Set the tool to be used on the canvas.
   * 
   * @param the_current_tool the current drawing tool being used.
   */
  public void setTool(final ToolInterface the_current_tool)
  {
    my_current_tool = the_current_tool;
  }

  /**
   * Set the ink color to be used on the canvas.
   * 
   * @param the_ink_color the ink color for the "pen".
   */
  public void setInkColor(final Color the_ink_color)
  {
    my_color = the_ink_color;
  }

  /**
   * Get the ink color currently in use on the canvas.
   * 
   * @return the color of the ink in use for the "pen".
   */
  public final Color getInkColor()
  {
    return my_color;
  }

  /**
   * Get the value of the stroke width for the "pen" in use on the canvas.
   * 
   * @return the stroke width
   */
  public float getStrokeWidth()
  {
    return my_stroke_width;
  }

  /**
   * Set the width of the "pen" in use on the canvas.
   * 
   * @param the_width the width of the pen.
   */
  public void setStrokeWidth(final float the_width)
  {
    my_stroke_width = the_width;
  }

  /**
   * Paints the all of the drawings currently in the CurrentDrawings.
   * 
   * @param the_graphics The graphics context to use for painting.
   */
  public void paintComponent(final Graphics the_graphics)
  {
    super.paintComponent(the_graphics);
    final Graphics2D g2d = (Graphics2D) the_graphics;

    for (Drawings d : my_finished_drawings)
    {
      g2d.setColor(d.getColor());
      g2d.setStroke(d.getStrokeWidth());
      g2d.draw(d.getShape());
    }
    // paint current drawing
    if (my_has_current)
    {
      g2d.setColor(my_color);
      g2d.setStroke(new BasicStroke(my_stroke_width));
      g2d.draw(my_current_drawing);
    }
    // paint grid
    if (my_grid_visible)
    {
      final int height = getHeight();
      final int width = getWidth();
      g2d.setColor(Color.BLACK);
      g2d.setStroke(new BasicStroke(1f));
      final Line2D.Double grid_line = new Line2D.Double();

      for (int x = 0; x < width; x++)
      {
        if (x % GRID_SPACING == 0)
        {
          grid_line.setLine(x, 0, x, height);
          g2d.draw(grid_line);
        }
      }
      for (int y = 0; y < height; y++)
      {
        if (y % GRID_SPACING == 0)
        {
          grid_line.setLine(0, y, width, y);
          g2d.draw(grid_line);
        }
      }
    }
  }

  /**
   * Toggle the visibility of the grid.
   */
  public void toggleGrid()
  {
    my_grid_visible ^= true;
    repaint();
  }

  /**
   * Is the grid visible on the Canvas?
   * 
   * @return if the grid is visible.
   */
  public boolean isGridVisible()
  {
    return my_grid_visible;
  }
}
