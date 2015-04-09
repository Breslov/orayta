package html;


/*
 * Helper class that helps create HTML elements when someone needs them.
 * Most of this is just simple String returning static methods.
 */
public class HtmlBuilder 
{
	public static String createAnchor(String anchorHexID, String txt)
	{	
		String prefix = "<a id=\"" + anchorHexID + "\"> ";
		String suffix = " </a>";
		
		return prefix + txt + suffix;
	}
	
	public static String createHeading(int level, String txt)
	{
		String lvlStr = String.valueOf(level);
		String prefix = "<h" + lvlStr + "> ";
		String suffix = " </h" + lvlStr + ">";
		
		return prefix + txt + suffix;
	}
}
