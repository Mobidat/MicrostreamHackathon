
package com.zillus.coronadiary.domain;

import java.time.LocalDate;


/**
 * The Class SymptomEntity.
 */
public class SymptomEntity extends AbstractTreatmentEntity
{

	/** The days duration. */
	private int daysDuration;

	/** The intensity. */
	private int intensity;
	
	/**
	 * Instantiates a new symptom entity.
	 *
	 * @param date
	 *            the date
	 * @param name
	 *            the name
	 * @param type
	 *            the type
	 * @param daysDuration
	 *            the days duration
	 * @param intensity
	 *            the intensity
	 */
	public SymptomEntity(
		final LocalDate date,
		final String name,
		final String type,
		final int daysDuration,
		final int intensity)
	{
		super(date, name, type);
		this.daysDuration = daysDuration;
		this.intensity    = intensity;
	}
	
	/**
	 * Instantiates a new symptom entity.
	 */
	public SymptomEntity()
	{
		super();
	}

	/**
	 * Gets the days duration.
	 *
	 * @return the days duration
	 */
	public int getDaysDuration()
	{
		return this.daysDuration;
	}

	/**
	 * Sets the days duration.
	 *
	 * @param daysDuration
	 *            the new days duration
	 */
	public void setDaysDuration(final int daysDuration)
	{
		this.daysDuration = daysDuration;
	}

	/**
	 * Gets the intensity.
	 *
	 * @return the intensity
	 */
	public int getIntensity()
	{
		return this.intensity;
	}

	/**
	 * Sets the intensity.
	 *
	 * @param intensity
	 *            the new intensity
	 */
	public void setIntensity(final int intensity)
	{
		this.intensity = intensity;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "SymptomEntity [daysDuration=" + this.daysDuration + ", intensity=" + this.intensity + "]";
	}

}
