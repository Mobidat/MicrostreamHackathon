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

import com.zillus.coronadiary.domain.enums.Gender;


/**
 * The Class PatientEntity.
 */
public class PatientEntity extends AbstractPersonEntity
{

	/** The birthday. */
	private LocalDate birthday;

	/** The gender. */
	private Gender gender;

	public PatientEntity(
		final String name,
		final String adress1,
		final String adress2,
		final String city,
		final String zipCode,
		final String country,
		final LocalDate birthday,
		final Gender gender)
	{
		super(name, adress1, adress2, city, zipCode, country);
		this.birthday = birthday;
		this.gender   = gender;
	}

	/**
	 * Instantiates a new patient entity.
	 */
	public PatientEntity()
	{
		super();
	}

	/**
	 * Gets the birthday.
	 *
	 * @return the birthday
	 */
	public LocalDate getBirthday()
	{
		return this.birthday;
	}

	/**
	 * Sets the birthday.
	 *
	 * @param birthday
	 *            the new birthday
	 */
	public void setBirthday(final LocalDate birthday)
	{
		this.birthday = birthday;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Gender getGender()
	{
		return this.gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender
	 *            the new gender
	 */
	public void setGender(final Gender gender)
	{
		this.gender = gender;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "PatientEntity [birthday=" + this.birthday + ", gender=" + this.gender + "]";
	}

}
