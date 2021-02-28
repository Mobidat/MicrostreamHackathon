
package com.zillus.coronadiary.domain.enums;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Enum Medication.
 */
public enum Medication implements Serializable
{

	/** The hydroxochloroquine. */
	HYDROXOCHLOROQUINE,

	/** The chloroquine. */
	CHLOROQUINE,

	/** The remdisivir. */
	REMDISIVIR,

	/** The lopinarvir. */
	LOPINARVIR,

	/** The ritonavir. */
	RITONAVIR,

	/** The dexamethasone. */
	DEXAMETHASONE,

	/** The ivermectin. */
	IVERMECTIN,

	/** The flavan3 ols. */
	FLAVAN3_OLS;

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
