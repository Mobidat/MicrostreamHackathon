
package com.zillus.coronadiary.domain;

import java.time.LocalDate;

import com.zillus.coronadiary.domain.enums.Vaccine;


// TODO: Auto-generated Javadoc
/**
 * The Class VaccinationEntity.
 */
public class VaccinationEntity extends AbstractTreatmentEntity
{

	/** The booster. */
	private Boolean booster;
	
	/** The vaccine. */
	private Vaccine vaccine;
	
	/**
	 * Instantiates a new vaccination entity.
	 *
	 * @param patientId
	 *            the patient id
	 * @param medicalId
	 *            the medical id
	 * @param date
	 *            the date
	 * @param name
	 *            the name
	 * @param booster
	 *            the booster
	 * @param vaccine
	 *            the vaccine
	 */
	public VaccinationEntity(
		final String patientId,
		final String medicalId,
		final LocalDate date,
		final String name,
		final Boolean booster,
		final Vaccine vaccine)
	{
		super(patientId, medicalId, date, name);
		this.booster = booster;
		this.vaccine = vaccine;
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
	public Boolean getBooster()
	{
		return this.booster;
	}
	
	/**
	 * Sets the booster.
	 *
	 * @param booster
	 *            the new booster
	 */
	public void setBooster(final Boolean booster)
	{
		this.booster = booster;
	}

	/**
	 * Gets the vaccine.
	 *
	 * @return the vaccine
	 */
	public Vaccine getVaccine()
	{
		return this.vaccine;
	}

	/**
	 * Sets the vaccine.
	 *
	 * @param vaccine
	 *            the new vaccine
	 */
	public void setVaccine(final Vaccine vaccine)
	{
		this.vaccine = vaccine;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "VaccinationEntity [booster=" + this.booster + ", vaccine=" + this.vaccine + "]";
	}

}
