/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

import powerpaint.gui.Canvas;

/**
 * Action which lets a user choose a new pen color.
 * 
 * @author Danielle Tucker and TCSS 305B Class
 * @version 2012 November
 */
@SuppressWarnings("serial")
public class ChangeColorAction extends AbstractAction
{
  /**
   * The icon width/height.
   */
  private static final int ICON_SIZE = 15;

  /**
   * The Canvas to change tool colors with.
   */
  private final Canvas my_canvas;

  /**
   * Constructs an action with the specified name and icon to change the ink color.
   * @param the_canvas the Canvas to change the ink color.
   */
  public ChangeColorAction(final Canvas the_canvas)
  {
    super("Color...", new ImageIcon(new ColorRectangle(ICON_SIZE, ICON_SIZE, Color.BLACK)));
    putValue(Action.SHORT_DESCRIPTION, "Change the color of the ink.");
    putValue(Action.MNEMONIC_KEY, KeyEvent.VK_C);
    my_canvas = the_canvas;
  }

  /**
   * Sets the ink to the specified color.
   * 
   * @param the_event the event, ignored.
   */
  public void actionPerformed(final ActionEvent the_event)
  {
    final Color color_chosen = JColorChooser.showDialog(my_canvas, "Choose a Color", null);
    if (color_chosen != null)
    {
      putValue(Action.SMALL_ICON, 
               new ImageIcon(new ColorRectangle(ICON_SIZE, ICON_SIZE, color_chosen)));
      my_canvas.setInkColor(color_chosen);
    }
  }
}
