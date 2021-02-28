
package com.zillus.coronadiary.dal;

import java.util.HashSet;
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
	 * Adds the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public static void addEntity(final AbstractPersonEntity entity)
	{
		DB.root().getPersonEntities().add(entity);
		PersonDAO.storePerson();
	}
	
	/**
	 * Removes the entity.
	 *
	 * @param entity
	 *            the entity
	 */
	public static void removeEntity(final AbstractPersonEntity entity)
	{
		DB.root().getPersonEntities().remove(entity);
		PersonDAO.storePerson();
	}
	
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
	 * Find person.
	 *
	 * @param personId
	 *            the person id
	 * @return the abstract person entity
	 */
	public static AbstractPersonEntity findPerson(final String personId)
	{
		return PersonDAO.findAll().stream().filter(p -> p.getViewId() == personId).findFirst().get();
	}
	
	/**
	 * Filter all medical.
	 *
	 * @return the sets the
	 */
	public static Set<MedicalEntity> filterAllMedicals()
	{
		return PersonDAO.findAll().stream().filter(MedicalEntity.class::isInstance)
			.map(MedicalEntity.class::cast)
			.collect(Collectors.toSet());
	}
	
	/**
	 * Filter all patients.
	 *
	 * @return the sets the
	 */
	public static Set<PatientEntity> filterAllPatients()
	{
		return PersonDAO.findAll().stream().filter(PatientEntity.class::isInstance)
			.map(PatientEntity.class::cast)
			.collect(Collectors.toCollection(HashSet::new));
	}
	
	/**
	 * Gets the sorted patients.
	 *
	 * @return the sorted patients
	 */
	public Set<PatientEntity> getSortedPatients()
	{
		return PersonDAO.findAll().stream()
			.filter(PatientEntity.class::isInstance)
			.map(PatientEntity.class::cast)
			.sorted()
			.collect(Collectors.toSet());
	}

	/**
	 * Store person.
	 */
	private static void storePerson()
	{
		DB.storageManager().store(DB.root().getPersonEntities());
	}
	
	/**
	 * Gets the sorted medicals.
	 *
	 * @return the sorted medicals
	 */
	public List<MedicalEntity> getSortedMedicals()
	{
		return PersonDAO.findAll().stream()
			.filter(MedicalEntity.class::isInstance)
			.map(MedicalEntity.class::cast)
			.sorted()
			.collect(Collectors.toList());
	}
}
