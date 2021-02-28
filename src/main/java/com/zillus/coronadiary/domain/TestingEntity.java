
package com.zillus.coronadiary.domain;

import java.time.LocalDate;

import com.zillus.coronadiary.domain.enums.Testprocedure;


// TODO: Auto-generated Javadoc
/**
 * The Class TestingEntity.
 */
public class TestingEntity extends AbstractTreatmentEntity
{

	/** The result. */
	private Boolean result;

	/** The testprocedure. */
	private Testprocedure testprocedure;
	
	/**
	 * Instantiates a new testing entity.
	 *
	 * @param patientId
	 *            the patient id
	 * @param medicalId
	 *            the medical id
	 * @param date
	 *            the date
	 * @param name
	 *            the name
	 * @param result
	 *            the result
	 * @param testprocedure
	 *            the testprocedure
	 */
	public TestingEntity(
		final String patientId,
		final String medicalId,
		final LocalDate date,
		final String name,
		final Boolean result,
		final Testprocedure testprocedure)
	{
		super(patientId, medicalId, date, name);
		this.result        = result;
		this.testprocedure = testprocedure;
	}
	
	/**
	 * Instantiates a new testing entity.
	 */
	public TestingEntity()
	{
		super();
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public Boolean getResult()
	{
		return this.result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result
	 *            the new result
	 */
	public void setResult(final Boolean result)
	{
		this.result = result;
	}

	/**
	 * Gets the testprocedure.
	 *
	 * @return the testprocedure
	 */
	public Testprocedure getTestprocedure()
	{
		return this.testprocedure;
	}
	
	/**
	 * Sets the testprocedure.
	 *
	 * @param testprocedure
	 *            the new testprocedure
	 */
	public void setTestprocedure(final Testprocedure testprocedure)
	{
		this.testprocedure = testprocedure;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "TestingEntity [result=" + this.result + "]";
	}

}
