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

package com.zillus.coronadiary.dal;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.zillus.coronadiary.domain.AbstractPersonEntity;
import com.zillus.coronadiary.domain.MedicalEntity;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.microstream.DB;


/**
 * The Class PersonDAO.
 */
public class PersonDAO
{
	
	/**
	 * Find all.
	 *
	 * @return the sets the
	 */
	public static Set<AbstractPersonEntity> findAll()
	{
		return DB.root().getPersonEntities();
	}
	
	/**
	 * Adds the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public static void addEntity(final AbstractPersonEntity entity)
	{
		if(entity != null)
		{
			PersonDAO.findAll().add(entity);
			PersonDAO.storePerson();
		}
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public static void removeEntity(final AbstractPersonEntity entity)
	{
		if(PersonDAO.isSaved(entity))
		{
			PersonDAO.findAll().remove(entity);
			PersonDAO.storePerson();
		}
	}

	/**
	 * Checks if is saved.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if is saved
	 */
	public static boolean isSaved(final AbstractPersonEntity entity)
	{
		return entity != null || !DB.root().getPersonEntities().contains(entity);
	}
	
	/**
	 * Find person.
	 *
	 * @param personId
	 *            the person id
	 * @return the abstract person entity
	 */
	public static AbstractPersonEntity findPerson(final String personId)
	{
		return PersonDAO.findAll().stream().filter(p -> p.getViewId().equals(personId)).findFirst().get();
	}
	
	/**
	 * Find all medicals.
	 *
	 * @return the list
	 */
	public static List<MedicalEntity> findAllMedicals()
	{
		return PersonDAO.findAll().stream()
			.filter(MedicalEntity.class::isInstance)
			.map(MedicalEntity.class::cast)
			.sorted()
			.collect(Collectors.toList());
	}
	
	/**
	 * Count all medicals.
	 *
	 * @return the int
	 */
	public static int countAllMedicals()
	{
		return PersonDAO.findAllMedicals().size();
	}
	
	/**
	 * Find all patients.
	 *
	 * @return the list
	 */
	public static List<PatientEntity> findAllPatients()
	{
		return PersonDAO.findAll().stream()
			.filter(PatientEntity.class::isInstance)
			.map(PatientEntity.class::cast)
			.sorted()
			.collect(Collectors.toList());
	}
	
	/**
	 * Count all patients.
	 *
	 * @return the int
	 */
	public static int countAllPatients()
	{
		return PersonDAO.findAllPatients().size();
	}
	
	/**
	 * Store person.
	 */
	private static void storePerson()
	{
		DB.storageManager().store(DB.root().getPersonEntities());
	}
	
	/**
	 * Store entities.
	 *
	 * @param entities
	 *            the entities
	 */
	public static void storeMedicals(final List<MedicalEntity> entities)
	{
		DB.storageManager().store(DB.root().getPersonEntities().addAll(entities));
		
	}
	
	/**
	 * Store entities.
	 *
	 * @param entities
	 *            the entities
	 */
	public static void storePatients(final List<PatientEntity> entities)
	{
		DB.storageManager().store(DB.root().getPersonEntities().addAll(entities));
	}
	
}
