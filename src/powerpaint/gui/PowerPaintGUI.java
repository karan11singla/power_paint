/*
 * Danielle Tucker 
 * TCSS 305 - November 2012 
 * Assignment 4 - PowerPaint
 */

package powerpaint.gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import powerpaint.listeners.ChangeColorAction;
import powerpaint.listeners.ToolAction;
import powerpaint.tools.Ellipse;
import powerpaint.tools.LineTool;
import powerpaint.tools.PencilTool;
import powerpaint.tools.RectangleTool;

/**
 * A basic painting/drawing program GUI.
 * 
 * @author Danielle Tucker
 * @version 2012 November
 */

@SuppressWarnings("serial")
public class PowerPaintGUI extends JFrame
{
  /**
   * The default line thickness.
   */
  private static final String DEFAULT_LINE_THICKNESS = "2";

  /**
   * The panel to draw on.
   */
  private final Canvas my_canvas = new Canvas();

  /**
   * The top level menu bar for this GUI.
   */
  private final JMenuBar my_menu_bar = new JMenuBar();
  
  /**
   * The actions for the tools.
   */
  private final List<ToolAction> my_tool_actions = new ArrayList<ToolAction>();

  /**
   * The color change action to change the color of the pen in use.
   */
  private ChangeColorAction my_change_color;

  /**
   * Constructs the Canvas, Menus, and Tool Bars for the GUI.
   */
  public PowerPaintGUI()
  {
    super("PowerPaint");

    createActions();

    createFileMenu();
    createOptionsMenu();
    createToolsMenu();
    createHelpMenu();

    setJMenuBar(my_menu_bar);
    add(createToolBar(), BorderLayout.SOUTH);

    add(my_canvas, BorderLayout.CENTER);
  }

  /**
   * Creates and displays a panel for drawing.
   * 
   * @param the_args Command line arguments (ignored).
   */
  public static void main(final String... the_args)
  {
    final PowerPaintGUI panel = new PowerPaintGUI();
    panel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    try
    {
      // set cross-platform Java look and feel
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
    }
    catch (final UnsupportedLookAndFeelException e)
    {
      assert false;
    }
    catch (final ClassNotFoundException e)
    {
      assert false;
    }
    catch (final InstantiationException e)
    {
      assert false;
    }
    catch (final IllegalAccessException e)
    {
      assert false;
    }

    // Display the window
    panel.pack();
    panel.setVisible(true);
  }

  /**
   * Create all of the actions for the buttons and tool bar.
   */
  private void createActions()
  {
    my_change_color = new ChangeColorAction(my_canvas);

    my_tool_actions.add(new ToolAction(my_canvas, new PencilTool()));
    my_tool_actions.add(new ToolAction(my_canvas, new LineTool()));
    my_tool_actions.add(new ToolAction(my_canvas, new RectangleTool()));
    my_tool_actions.add(new ToolAction(my_canvas, new Ellipse()));
  }

  /**
   * Create the JTool Bar with the drawing tools and color selector.
   * @return the tool bar
   */
  private JToolBar createToolBar()
  {
    final JToolBar tool_bar = new JToolBar();
    tool_bar.add(new JButton(my_change_color));

    JToggleButton toggle_button;
    final ButtonGroup bg = new ButtonGroup();
    for (ToolAction t : my_tool_actions)
    {
      toggle_button = new JToggleButton(t);
      tool_bar.add(toggle_button);
      bg.add(toggle_button);
    }
    return tool_bar;
  }

  /**
   * Creates a File menu.
   */
  private void createFileMenu()
  {
    // Create File Menu
    final JMenu file_menu = new JMenu("File");
    file_menu.setMnemonic(KeyEvent.VK_F);
    my_menu_bar.add(file_menu);

    // Clear Option
    final JMenuItem clear_menu = new JMenuItem("Clear", KeyEvent.VK_C);
    clear_menu.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_canvas.clearDrawings();
      }
    });
    file_menu.add(clear_menu);

    // Quit Option
    file_menu.addSeparator();
    final JMenuItem quit_menu = new JMenuItem("Quit", KeyEvent.VK_Q);
    quit_menu.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        dispose();
      }
    });
    file_menu.add(quit_menu);
  }

  /**
   * Create a menu with program options.
   */
  private void createOptionsMenu()
  {
    final JMenu options_menu = new JMenu("Options");
    options_menu.setMnemonic(KeyEvent.VK_O);
    my_menu_bar.add(options_menu);
    
    final JCheckBoxMenuItem grid_checkbox = new JCheckBoxMenuItem("Grid");
    grid_checkbox.setMnemonic(KeyEvent.VK_G);
    grid_checkbox.addActionListener(new ActionListener()
    {
      public void actionPerformed(final ActionEvent the_event)
      {
        my_canvas.toggleGrid();
      }
    });
    options_menu.add(grid_checkbox);
    
    
    final JMenu thickness_menu = new JMenu("Thickness");
    thickness_menu.setMnemonic(KeyEvent.VK_T);
    options_menu.add(thickness_menu);
    
    JRadioButtonMenuItem line_radio;
    final ButtonGroup thickness_group = new ButtonGroup();
  
    final String[] line_thickness = {"1", "2", "4"};
    int key;
    for (int i = 0; i < line_thickness.length; i++)
    {
      line_radio = new JRadioButtonMenuItem(line_thickness[i]);
      key = KeyEvent.getExtendedKeyCodeForChar(line_thickness[i].codePointAt(0));
      line_radio.setMnemonic(key);
      if (line_thickness[i].equals(DEFAULT_LINE_THICKNESS))
      {
        line_radio.setSelected(true);
        my_canvas.setStrokeWidth(Float.parseFloat(DEFAULT_LINE_THICKNESS));
      }

      line_radio.addActionListener(new ActionListener()
      {
        public void actionPerformed(final ActionEvent the_event)
        {
          my_canvas.setStrokeWidth(Float.parseFloat(the_event.getActionCommand()));
        }
      });
      
      thickness_group.add(line_radio);
      thickness_menu.add(line_radio);
    }
  }

  /**
   * Create a drawing Tools menu.
   */
  private void createToolsMenu()
  {
    final JMenu tools_menu = new JMenu("Tools");
    tools_menu.setMnemonic(KeyEvent.VK_T);
    my_menu_bar.add(tools_menu);

    tools_menu.add(my_change_color);

    tools_menu.addSeparator();

    final ButtonGroup tool_group = new ButtonGroup();
    JRadioButtonMenuItem radio_button;

    for (ToolAction t : my_tool_actions)
    {
      radio_button = new JRadioButtonMenuItem(t);
      tools_menu.add(radio_button);
      tool_group.add(radio_button);
    }
  }

  /**
   * Create the Help menu.
   */
  private void createHelpMenu()
  {
    // Create Help Menu
    final JMenu help_menu = new JMenu("Help");
    help_menu.setMnemonic(KeyEvent.VK_H);
    my_menu_bar.add(help_menu);

    final JMenuItem about = new JMenuItem("About", KeyEvent.VK_A);
    about.addActionListener(new ActionListener()
    {
      private static final String INFO = "Author: Danielle Tucker\nVersion: 2012 November\n";

      public void actionPerformed(final ActionEvent the_event)
      {
        JOptionPane.showMessageDialog(my_canvas, INFO, about.getText(),
                                      JOptionPane.INFORMATION_MESSAGE);
      }
    });
    help_menu.add(about);
  }
}
