/*
 * Danielle Tucker 
 * TCSS 305 - November 2012 
 * Assignment 4 - PowerPaint
 */

package powerpaint.listeners;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import powerpaint.gui.Canvas;
import powerpaint.tools.ToolInterface;

/**
 * Actions for any Tools.
 * 
 * @author Danielle Tucker
 * @version 2012 November
 */
@SuppressWarnings("serial")
public class ToolAction extends AbstractAction
{
  /**
   * Default Tool to be Selected.
   */
  private static final String DEFAULT_TOOL = "Pencil";

  /**
   * The canvas which the my_listener should be attached.
   */
  private final Canvas my_canvas;

  /**
   * The tool being used.
   */
  private final ToolInterface my_tool;

  /**
   *  The current shape being created by the tool in use. 
   */
  private Shape my_current_shape;

  /**
   * Creates the actions for use on a button or other user event.
   * 
   * @param the_canvas the canvas this tool will draw on.
   * @param the_tool the current tool to associate with this action.
   */
  public ToolAction(final Canvas the_canvas, final ToolInterface the_tool)
  {
    super(the_tool.getName());
    putValue(Action.MNEMONIC_KEY,
             KeyEvent.getExtendedKeyCodeForChar(the_tool.getName().codePointAt(0)));
    putValue(Action.SHORT_DESCRIPTION, the_tool.getName() + " Drawing Tool");
    my_canvas = the_canvas;
    my_tool = the_tool;

    final ToolListener listener = new ToolListener();
    my_canvas.addMouseListener(listener);
    my_canvas.addMouseMotionListener(listener);
    if (the_tool.getName().equals(DEFAULT_TOOL))
    {
      putValue(Action.SELECTED_KEY, true);
      my_canvas.setTool(the_tool);
    }
  }

  /**
   * Removes old mouse listeners for other tools and sets up the listener
   * for the tool which triggered this event.
   * @param the_event the action event (not used)
   */
  public void actionPerformed(final ActionEvent the_event)
  {
    putValue(Action.SELECTED_KEY, true);
    my_canvas.setTool(my_tool);
  }

  /**
   * Inner class to associate mouse motions with tool drawing events.
   * @author Danielle Tucker
   * @version 2012 November
   */
  private class ToolListener extends MouseAdapter
  {
    /**
     * When mouse is pressed it starts the current shape to be drawn.
     * @param the_event the mouse event
     */
    public void mousePressed(final MouseEvent the_event)
    {
      if (my_tool.getName().equals(my_canvas.getTool().getName()))
      {
        my_current_shape = my_tool.start(the_event.getX(), the_event.getY());
        my_canvas.setCurrentDrawing(my_current_shape);
        my_canvas.repaint();
      }
    }
    
    /**
     * When the mouse is dragged it triggers the move event for the tool.
     * @param the_event the mouse event. 
     */
    public void mouseDragged(final MouseEvent the_event)
    {
      if (my_tool.getName().equals(my_canvas.getTool().getName()))
      {
        my_current_shape = my_tool.move(the_event.getX(), the_event.getY());
        my_canvas.repaint();
      }
    }

    /**
     * When the mouse is released it triggers the stop event for the tool.
     * @param the_event the mouse event
     */
    public void mouseReleased(final MouseEvent the_event)
    {
      if (my_tool.getName().equals(my_canvas.getTool().getName()))
      {
        my_canvas.setFinishedDrawing(my_current_shape);
        my_canvas.repaint();
      }
    }
  }
}
