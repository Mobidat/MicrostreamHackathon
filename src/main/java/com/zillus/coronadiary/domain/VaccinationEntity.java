
package com.zillus.coronadiary.domain;

import java.time.LocalDate;


/**
 * The Class VaccinationEntity.
 */
public class VaccinationEntity extends AbstractTreatmentEntity
{
	
	/** The booster. */
	private boolean booster;
	
	/**
	 * Instantiates a new vaccination entity.
	 *
	 * @param date
	 *            the date
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param booster
	 *            the booster
	 */
	public VaccinationEntity(final LocalDate date, final String name, final String type, final boolean booster)
	{
		super(date, name, type);
		this.booster = booster;
	}
	
	/**
	 * Instantiates a new vaccination entity.
	 */
	public VaccinationEntity()
	{
		super();
	}
	
	/**
	 * Gets the booster.
	 *
	 * @return the booster
	 */
	public boolean getBooster()
	{
		return this.booster;
	}

	/**
	 * Sets the booster.
	 *
	 * @param booster
	 *            the new booster
	 */
	public void setBooster(final boolean booster)
	{
		this.booster = booster;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "VaccinationEntity [booster=" + this.booster + "]";
	}
}
