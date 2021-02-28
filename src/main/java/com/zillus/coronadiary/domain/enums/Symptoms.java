
package com.zillus.coronadiary.domain.enums;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Enum Symptoms.
 */
public enum Symptoms implements Serializable
{

	/** The cough. */
	COUGH,

	/** The fever. */
	FEVER,

	/** The fatigue. */
	FATIGUE,

	/** The rhinitis. */
	RHINITIS,

	/** The sore throat. */
	SORE_THROAT,

	/** The shortness of breath. */
	SHORTNESS_OF_BREATH,

	/** The diarrhea. */
	DIARRHEA,

	/** The loss of smell. */
	LOSS_OF_SMELL,

	/** The loss of taste. */
	LOSS_OF_TASTE;
	
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
