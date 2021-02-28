
package com.zillus.coronadiary.domain.enums;

import java.io.Serializable;


public enum Testprocedure implements Serializable
{
	PCR_TEST,
	PCR_FAST_TEST,
	ANTIGEN_TEST,
	CORONA_SELFTEST,
	ANTIBODY_TEST;

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
