
package com.zillus.coronadiary.domain;

import java.time.LocalDate;


// TODO: Auto-generated Javadoc
/**
 * The Class TestingEntity.
 */
public class TestingEntity extends AbstractTreatmentEntity
{
	
	/** The result. */
	private boolean result;
	
	public TestingEntity(final LocalDate date, final String name, final String type, final boolean result)
	{
		super(date, name, type);
		this.result = result;
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
	public boolean getResult()
	{
		return this.result;
	}

	/**
	 * Sets the result.
	 *
	 * @param result
	 *            the new result
	 */
	public void setResult(final boolean result)
	{
		this.result = result;
	}
	
	@Override
	public String toString()
	{
		return "TestingEntity [result=" + this.result + "]";
	}
	
}
