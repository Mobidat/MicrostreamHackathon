
package com.zillus.coronadiary.domain;

import java.time.LocalDate;

import com.zillus.coronadiary.domain.enums.Medication;


// TODO: Auto-generated Javadoc
/**
 * The Class Medication.
 */
public class MedicationEntity extends AbstractTreatmentEntity
{

	/** The dose. */
	private Integer dose;

	/** The medication. */
	private Medication medication;
	
	/**
	 * Instantiates a new medication entity.
	 */
	public MedicationEntity()
	{
		super();
	}
	
	/**
	 * Instantiates a new medication entity.
	 *
	 * @param patientId
	 *            the patient id
	 * @param medicalId
	 *            the medical id
	 * @param date
	 *            the date
	 * @param name
	 *            the name
	 * @param dose
	 *            the dose
	 * @param medication
	 *            the medication
	 */
	public MedicationEntity(
		final String patientId,
		final String medicalId,
		final LocalDate date,
		final String name,
		final Integer dose,
		final Medication medication)
	{
		super(patientId, medicalId, date, name);
		this.dose       = dose;
		this.medication = medication;
	}
	
	/**
	 * Gets the dose.
	 *
	 * @return the dose
	 */
	public Integer getDose()
	{
		return this.dose;
	}
	
	/**
	 * Sets the dose.
	 *
	 * @param dose
	 *            the new dose
	 */
	public void setDose(final Integer dose)
	{
		this.dose = dose;
	}

	/**
	 * Gets the medication.
	 *
	 * @return the medication
	 */
	public Medication getMedication()
	{
		return this.medication;
	}

	/**
	 * Sets the medication.
	 *
	 * @param medication
	 *            the new medication
	 */
	public void setMedication(final Medication medication)
	{
		this.medication = medication;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "MedicationEntity [dose=" + this.dose + ", medication=" + this.medication + "]";
	}

}
