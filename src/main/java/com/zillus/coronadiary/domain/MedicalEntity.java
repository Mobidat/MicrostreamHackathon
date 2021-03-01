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

import com.zillus.coronadiary.domain.enums.Profession;


/**
 * The Class MedicalEntity.
 */
public class MedicalEntity extends AbstractPersonEntity
{
	
	/** The profession. */
	private Profession profession;
	
	/**
	 * Instantiates a new medical entity.
	 */
	public MedicalEntity()
	{
		super();
	}

	/**
	 * Instantiates a new medical entity.
	 *
	 * @param name
	 *            the name
	 * @param adress1
	 *            the adress 1
	 * @param adress2
	 *            the adress 2
	 * @param city
	 *            the city
	 * @param zipCode
	 *            the zip code
	 * @param country
	 *            the country
	 * @param profession
	 *            the profession
	 */
	public MedicalEntity(
		final String name,
		final String adress1,
		final String adress2,
		final String city,
		final String zipCode,
		final String country,
		final Profession profession)
	{
		super(name, adress1, adress2, city, zipCode, country);
		this.profession = profession;
	}

	/**
	 * Gets the profession.
	 *
	 * @return the profession
	 */
	public Profession getProfession()
	{
		return this.profession;
	}
	
	/**
	 * Sets the profession.
	 *
	 * @param profession
	 *            the new profession
	 */
	public void setProfession(final Profession profession)
	{
		this.profession = profession;
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "MedicalEntity [profession=" + this.profession + "]";
	}

}
