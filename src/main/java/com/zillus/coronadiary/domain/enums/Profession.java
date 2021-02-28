
package com.zillus.coronadiary.domain.enums;

import java.io.Serializable;


public enum Profession implements Serializable
{
	NURSE,
	DOCTOR,
	DR_MED,
	PROF_DR_MED;
	
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
