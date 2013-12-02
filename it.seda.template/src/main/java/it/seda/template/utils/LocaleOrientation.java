/**
 * 
 */
package it.seda.template.utils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * The LocaleOrientation class specifies the language-sensitive orientation
 * of component's elements or text. It is used to reflect the differences in
 * this ordering between different writing systems. The LocaleOrientation
 * class indicates the orientation of the elements/text in the horizontal
 * direction ("left to right" or "right to left") and in the vertical direction
 * ("top to bottom" or "bottom to top").
 * 
 * @author f.ricci
 */
public class LocaleOrientation implements Serializable {
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = -4113291392143563828L;

	/**
	 * The Constant LEFT_TO_RIGHT indicates that items run left to right.
	 */
	public static final LocaleOrientation LEFT_TO_RIGHT = new LocaleOrientation(true, true);

	/**
	 * The Constant RIGHT_TO_LEFT indicates that items run right to left.
	 */
	public static final LocaleOrientation RIGHT_TO_LEFT = new LocaleOrientation(true, false);

	/**
	 * The Constant UNKNOWN indicates that a component's orientation is not set.
	 */
	public static final LocaleOrientation UNKNOWN = new LocaleOrientation(true, true);

	/**
	 * The Constant rlLangs.
	 */
	private static final Set<String> rlLangs = new HashSet<String>(); // RIGHT_TO_LEFT

	// languages

	/**
	 * The horizontal.
	 */
	private final boolean horizontal;

	/**
	 * The left2right.
	 */
	private final boolean left2right;

	static {
		rlLangs.add("ar"); //$NON-NLS-1$
		rlLangs.add("fa"); //$NON-NLS-1$
		rlLangs.add("iw"); //$NON-NLS-1$
		rlLangs.add("ur"); //$NON-NLS-1$
	}

	/**
	 * Gets the orientation for the specified locale.
	 * 
	 * @param locale
	 *            the specified Locale.
	 * @return the ComponentOrientation.
	 */
	public static LocaleOrientation getOrientation(Locale locale) {
		String lang = locale.getLanguage();
		return rlLangs.contains(lang) ? RIGHT_TO_LEFT : LEFT_TO_RIGHT;
	}

	/**
	 * Instantiates a new component orientation.
	 * 
	 * @param hor
	 *            whether the items should be arranged horizontally.
	 * @param l2r
	 *            whether this orientation specifies a left-to-right flow.
	 */
	private LocaleOrientation(boolean hor, boolean l2r) {
		horizontal = hor;
		left2right = l2r;
	}

	/**
	 * Returns true if the text of the of writing systems arranged horizontally.
	 * 
	 * @return true, if the text is written horizontally, false for a vertical
	 *         arrangement.
	 */
	public boolean isHorizontal() {
		return horizontal;
	}

	/**
	 * Returns true if the text is arranged from left to right.
	 * 
	 * @return true, for writing systems written from left to right; false for
	 *         right-to-left.
	 */
	public boolean isLeftToRight() {
		return left2right;
	}
}
