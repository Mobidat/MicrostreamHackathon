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
package com.zillus.coronadiary.microstream;

import java.util.HashSet;
import java.util.Set;

import com.zillus.coronadiary.domain.AbstractPersonEntity;
import com.zillus.coronadiary.domain.AbstractTreatmentEntity;


// TODO: Auto-generated Javadoc
/**
 * MicroStream data root. Create your data model from here.
 *
 * @see <a href="https://manual.docs.microstream.one/">Reference Manual</a>
 */
public class DataRoot
{
	
	/** The person entities. */
	private final Set<AbstractPersonEntity> personEntities = new HashSet<>();
	
	/** The Treatment entities. */
	private final Set<AbstractTreatmentEntity> treatmentEntities = new HashSet<>();

	/**
	 * Instantiates a new data root.
	 */
	public DataRoot()
	{
		super();
	}

	/**
	 * Gets the person entities.
	 *
	 * @return the person entities
	 */
	public Set<AbstractPersonEntity> getPersonEntities()
	{
		return this.personEntities;
	}

	/**
	 * Gets the treatment entities.
	 *
	 * @return the treatment entities
	 */
	public Set<AbstractTreatmentEntity> getTreatmentEntities()
	{
		return this.treatmentEntities;
	}

	/**
	 * Checks if is persons.
	 *
	 * @return true, if is persons
	 */
	public boolean isPersons()
	{
		return !this.personEntities.isEmpty();
	}

	/**
	 * Checks if is treatments.
	 *
	 * @return true, if is treatments
	 */
	public boolean isTreatments()
	{
		return !this.treatmentEntities.isEmpty();
	}

}
