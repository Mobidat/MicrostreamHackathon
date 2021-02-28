/*******************************************************************************
 * Copyright 2021 Frank Zillus
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
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
