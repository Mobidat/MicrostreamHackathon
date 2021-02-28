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

import com.zillus.coronadiary.domain.enums.Symptoms;


// TODO: Auto-generated Javadoc
/**
 * The Class SymptomEntity.
 */
public class SymptomEntity extends AbstractTreatmentEntity
{
	
	/** The days duration. */
	private Integer daysDuration;
	
	/** The intensity. */
	private Integer intensity;
	
	/** The symptoms. */
	private Symptoms symptoms;
	
	/**
	 * Instantiates a new symptom entity.
	 *
	 * @param patientId
	 *            the patient id
	 * @param medicalId
	 *            the medical id
	 * @param date
	 *            the date
	 * @param name
	 *            the name
	 * @param daysDuration
	 *            the days duration
	 * @param intensity
	 *            the intensity
	 * @param symptoms
	 *            the symptoms
	 */
	public SymptomEntity(
		final String patientId,
		final String medicalId,
		final LocalDate date,
		final String name,
		final Integer daysDuration,
		final Integer intensity,
		final Symptoms symptoms)
	{
		super(patientId, medicalId, date, name);
		this.daysDuration = daysDuration;
		this.intensity    = intensity;
		this.symptoms     = symptoms;
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
	public Integer getDaysDuration()
	{
		return this.daysDuration;
	}
	
	/**
	 * Sets the days duration.
	 *
	 * @param daysDuration
	 *            the new days duration
	 */
	public void setDaysDuration(final Integer daysDuration)
	{
		this.daysDuration = daysDuration;
	}
	
	/**
	 * Gets the intensity.
	 *
	 * @return the intensity
	 */
	public Integer getIntensity()
	{
		return this.intensity;
	}
	
	/**
	 * Sets the intensity.
	 *
	 * @param intensity
	 *            the new intensity
	 */
	public void setIntensity(final Integer intensity)
	{
		this.intensity = intensity;
	}

	/**
	 * Gets the symptoms.
	 *
	 * @return the symptoms
	 */
	public Symptoms getSymptoms()
	{
		return this.symptoms;
	}

	/**
	 * Sets the symptoms.
	 *
	 * @param symptoms
	 *            the new symptoms
	 */
	public void setSymptoms(final Symptoms symptoms)
	{
		this.symptoms = symptoms;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "SymptomEntity [daysDuration=" + this.daysDuration + ", intensity=" + this.intensity + ", symptoms="
			+ this.symptoms + "]";
	}

}
