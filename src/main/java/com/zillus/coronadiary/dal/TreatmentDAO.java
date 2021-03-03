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

import com.zillus.coronadiary.domain.AbstractTreatmentEntity;
import com.zillus.coronadiary.domain.MedicationEntity;
import com.zillus.coronadiary.domain.SymptomEntity;
import com.zillus.coronadiary.domain.TestingEntity;
import com.zillus.coronadiary.domain.VaccinationEntity;
import com.zillus.coronadiary.microstream.DB;


public class TreatmentDAO
{

	/**
	 * Adds the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public static void addEntity(final AbstractTreatmentEntity entity)
	{
		if(entity != null)
		{
			TreatmentDAO.findAll().add(entity);
			TreatmentDAO.storeTreatment();
		}
	}

	/**
	 * Store entities.
	 *
	 * @param entities
	 *            the entities
	 */
	public static void storeEntities(final List<AbstractTreatmentEntity> entities)
	{
		DB.storageManager().store(DB.root().getTreatmentEntities().addAll(entities));
	}

	/**
	 * Removes the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public static void removeEntity(final AbstractTreatmentEntity entity)
	{
		if(TreatmentDAO.isSaved(entity))
		{
			TreatmentDAO.findAll().remove(entity);
			TreatmentDAO.storeTreatment();
		}
	}

	/**
	 * Checks if is saved.
	 *
	 * @param entity
	 *            the entity
	 * @return true, if is saved
	 */
	public static boolean isSaved(final AbstractTreatmentEntity entity)
	{
		return entity != null || !DB.root().getTreatmentEntities().contains(entity);
	}

	/**
	 * Find all.
	 *
	 * @return the sets the
	 */
	public static Set<AbstractTreatmentEntity> findAll()
	{
		return DB.root().getTreatmentEntities();
	}

	/**
	 * Count all treatments.
	 *
	 * @return the int
	 */
	public static int countAllTreatments()
	{
		return TreatmentDAO.findAll().size();
	}

	/**
	 * Find all medical.
	 *
	 * @return the sets the
	 */
	public static List<AbstractTreatmentEntity> filterAllMedication()
	{
		return TreatmentDAO.findAll().stream().filter(MedicationEntity.class::isInstance)
			.collect(Collectors.toList());
	}

	/**
	 * Find all symptom.
	 *
	 * @return the sets the
	 */
	public static List<AbstractTreatmentEntity> findAllSymptom()
	{
		return TreatmentDAO.findAll().stream()
			.filter(SymptomEntity.class::isInstance)
			.sorted()
			.collect(Collectors.toList());
	}

	/**
	 * Find all testing.
	 *
	 * @return the sets the
	 */
	public static List<AbstractTreatmentEntity> findAllTesting()
	{
		return TreatmentDAO.findAll().stream()
			.filter(TestingEntity.class::isInstance)
			.sorted()
			.collect(Collectors.toList());
	}

	/**
	 * Find all vaccination.
	 *
	 * @return the sets the
	 */
	public static List<AbstractTreatmentEntity> findAllVaccination()
	{
		return TreatmentDAO.findAll().stream()
			.filter(VaccinationEntity.class::isInstance)
			.sorted()
			.collect(Collectors.toList());
	}

	/**
	 * Store treatment.
	 */
	private static void storeTreatment()
	{
		DB.storageManager().store(DB.root().getTreatmentEntities());
	}

	/**
	 * Gets the sorted treatment.
	 *
	 * @param patientId
	 *            the patient id
	 * @return the sorted treatment
	 */
	public static List<AbstractTreatmentEntity> getPatientTreatments(final String patientId)
	{
		return TreatmentDAO.findAll().stream()
			.filter(t -> t.getPatientId().equals(patientId))
			.sorted()
			.collect(Collectors.toList());
	}

	/**
	 * Gets the sorted treatments.
	 *
	 * @return the sorted treatments
	 */
	public static List<AbstractTreatmentEntity> getAllTreatments()
	{
		return TreatmentDAO.findAll().stream()
			.sorted()
			.collect(Collectors.toList());
	}

	/**
	 * Store entities.
	 *
	 * @param entities
	 *            the entities
	 */
	public static void storeSymptoms(final List<SymptomEntity> entities)
	{
		DB.storageManager().store(DB.root().getTreatmentEntities().addAll(entities));
	}

	/**
	 * Store medications.
	 *
	 * @param entities
	 *            the entities
	 */
	public static void storeMedications(final List<MedicationEntity> entities)
	{
		DB.storageManager().store(DB.root().getTreatmentEntities().addAll(entities));
	}

	/**
	 * Store testings.
	 *
	 * @param entities
	 *            the entities
	 */
	public static void storeTestings(final List<TestingEntity> entities)
	{
		DB.storageManager().store(DB.root().getTreatmentEntities().addAll(entities));
	}

	/**
	 * Store vaccinations.
	 *
	 * @param entities
	 *            the entities
	 */
	public static void storeVaccinations(final List<VaccinationEntity> entities)
	{
		DB.storageManager().store(DB.root().getTreatmentEntities().addAll(entities));
	}
	
	/**
	 * Removes the all treatments.
	 */
	public static void removeAllTreatments()
	{
		final Set<AbstractTreatmentEntity> treatmentEntities = DB.root().getTreatmentEntities();
		treatmentEntities.clear();

		DB.storageManager().store(treatmentEntities);
	}
}
