
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
		DB.root().getTreatmentEntities().add(entity);
		TreatmentDAO.storeTreatment();
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public static void removeEntity(final AbstractTreatmentEntity entity)
	{
		DB.root().getTreatmentEntities().remove(entity);
		TreatmentDAO.storeTreatment();
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
	 * Find all medical.
	 *
	 * @return the sets the
	 */
	public static Set<AbstractTreatmentEntity> filterAllMedication()
	{
		return TreatmentDAO.findAll().stream().filter(MedicationEntity.class::isInstance)
			.collect(Collectors.toSet());
	}

	/**
	 * Find all symptom.
	 *
	 * @return the sets the
	 */
	public static Set<AbstractTreatmentEntity> filterAllSymptom()
	{
		return TreatmentDAO.findAll().stream().filter(SymptomEntity.class::isInstance)
			.collect(Collectors.toSet());
	}

	/**
	 * Find all testing.
	 *
	 * @return the sets the
	 */
	public static Set<AbstractTreatmentEntity> filterAllTesting()
	{
		return TreatmentDAO.findAll().stream().filter(TestingEntity.class::isInstance)
			.collect(Collectors.toSet());
	}

	/**
	 * Find all vaccination.
	 *
	 * @return the sets the
	 */
	public static Set<AbstractTreatmentEntity> filterAllVaccination()
	{
		return TreatmentDAO.findAll().stream().filter(VaccinationEntity.class::isInstance)
			.collect(Collectors.toSet());
	}

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
	public static List<AbstractTreatmentEntity> getSortedTreatments(final String patientId)
	{
		return TreatmentDAO.findAll().stream()
			.filter(t -> t.getPatientId() == patientId)
			.sorted()
			.collect(Collectors.toList());
	}
	
	/**
	 * Gets the sorted treatments.
	 *
	 * @return the sorted treatments
	 */
	public static List<AbstractTreatmentEntity> getSortedTreatments()
	{
		return TreatmentDAO.findAll().stream()
			.sorted()
			.collect(Collectors.toList());
	}
}
