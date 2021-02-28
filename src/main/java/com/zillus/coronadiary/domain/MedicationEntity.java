
package com.zillus.coronadiary.domain;

import java.time.LocalDate;


/**
 * The Class Medication.
 */
public class MedicationEntity extends AbstractTreatmentEntity
{
	
	/** The dose. */
	private int dose;

	/**
	 * Instantiates a new medication entity.
	 *
	 * @param date
	 *            the date
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param dose
	 *            the dose
	 */
	public MedicationEntity(final LocalDate date, final String name, final String type, final int dose)
	{
		super(date, name, type);
		this.dose = dose;
	}
	
	/**
	 * Instantiates a new medication entity.
	 */
	public MedicationEntity()
	{
		super();
	}
	
	/**
	 * Gets the dose.
	 *
	 * @return the dose
	 */
	public int getDose()
	{
		return this.dose;
	}
	
	/**
	 * Sets the dose.
	 *
	 * @param dose
	 *            the new dose
	 */
	public void setDose(final int dose)
	{
		this.dose = dose;
	}
	
	@Override
	public String toString()
	{
		return "MedicationEntity [dose=" + this.dose + "]";
	}
	
}
