
package com.zillus.coronadiary.domain.enums;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Enum Gender.
 */
public enum Gender implements Serializable
{
	
	/** The male. */
	MALE,

	/** The female. */
	FEMALE,

	/** The unknown. */
	UNKNOWN;
	
	/**
	 * Gets the caption.
	 *
	 * @return the caption
	 */
	public String getCaption()
	{
		return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
	}
	
}
