
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
	 * Checks if is person DB.
	 *
	 * @return true, if is person DB
	 */
	public static boolean isPersonDB()
	{
		return DB.root().isPersons();
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
		if(entity != null || !DB.root().getPersonEntities().contains(entity))
		{
			PersonDAO.findAll().remove(entity);
			PersonDAO.storePerson();
		}
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
	 * Store person.
	 */
	private static void storePerson()
	{
		DB.storageManager().store(DB.root().getPersonEntities());
	}
	
}
