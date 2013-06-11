/*
 * Danielle Tucker
 * TCSS 305 - November 2012
 * Assignment 4 - PowerPaint
 */

package powerpaint.tools;

/**
 * A general definition of a tool which can draw a shape.
 * 
 * @author Danielle Tucker
 * @version 2012 November
 */
public abstract class AbstractTool implements ToolInterface
{
  /**
   * The string to remove from the class name if present for my_name.
   */
  private static final String TOOL_SUFFIX = "Tool";

  /**
   * The name of the tool.
   */
  private final String my_name;

  /**
   * Constructor for tool object.
   */
  public AbstractTool()
  {
    // Make sure name is just Tool name without "Tool" Suffix
    final String name = getClass().getName();
    final int dot = name.lastIndexOf('.');
    if (dot >= 0 && name.endsWith(TOOL_SUFFIX))
    {
      // truncate the word "Name"
      my_name = name.substring(dot + 1, name.length() - TOOL_SUFFIX.length());
    }
    else
    {
      my_name = name.substring(dot + 1, name.length());
    }
  }

  /**
   * Provide the name of this tool.
   * 
   * @return the name of this tool.
   */
  public final String getName()
  {
    return my_name;
  }
}
