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

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import com.github.javafaker.Address;
import com.github.javafaker.Bool;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import com.github.javafaker.Medical;
import com.github.javafaker.Number;
import com.zillus.coronadiary.domain.MedicalEntity;
import com.zillus.coronadiary.domain.MedicationEntity;
import com.zillus.coronadiary.domain.PatientEntity;
import com.zillus.coronadiary.domain.SymptomEntity;
import com.zillus.coronadiary.domain.TestingEntity;
import com.zillus.coronadiary.domain.VaccinationEntity;
import com.zillus.coronadiary.domain.enums.Gender;
import com.zillus.coronadiary.domain.enums.Medication;
import com.zillus.coronadiary.domain.enums.Profession;
import com.zillus.coronadiary.domain.enums.Symptoms;
import com.zillus.coronadiary.domain.enums.Testprocedure;
import com.zillus.coronadiary.domain.enums.Vaccine;


/**
 * The Class RandomData.
 */
public class RandomDAO
{

	/** The Constant RND. */
	private static final Random RND = ThreadLocalRandom.current();

	/** The faker. */
	private static final Faker FAKER = Faker.instance();

	/** The start corona. */
	static LocalDate startCorona = LocalDate.of(2020, 1, 1);
	
	/** The Constant DAYS_ATMOST. */
	private static final int DAYS_ATMOST = (int)RandomDAO.startCorona.until(LocalDate.now(), ChronoUnit.DAYS);

	/**
	 * Creates the vaccinations.
	 *
	 * @param numVaccination
	 *            the num vaccination
	 * @param patienId
	 * @param medicals
	 * @return the list
	 */
	public static List<VaccinationEntity>
		createVaccinations(final int numVaccination, final String patienId, final List<MedicalEntity> medicals)
	{
		final List<VaccinationEntity> vaccinations = new ArrayList<>();

		final DateAndTime date    = RandomDAO.FAKER.date();
		final Medical     medical = RandomDAO.FAKER.medical();

		final Bool                bool       = RandomDAO.FAKER.bool();
		final RandomEnum<Vaccine> randomEnum = new RandomEnum<>(Vaccine.class);

		for(int i = 0; i < numVaccination; i++)
		{
			final MedicalEntity randomMedical = RandomDAO.randomMedical(medicals);
			final String        medicalId     = randomMedical.getViewId();
			
			vaccinations.add(new VaccinationEntity(patienId, medicalId,
				LocalDate.ofInstant(date.past(RandomDAO.DAYS_ATMOST, TimeUnit.DAYS).toInstant(),
					ZoneId.systemDefault()),
				medical.medicineName(), Boolean.valueOf(bool.bool()),
				randomEnum.random()));
		}
		return vaccinations;
	}

	/**
	 * Creates the testings.
	 *
	 * @param numTesting
	 *            the num testing
	 * @param patienId
	 * @param medicals
	 * @return the list
	 */
	public static List<TestingEntity>
		createTestings(final int numTesting, final String patienId, final List<MedicalEntity> medicals)
	{
		final List<TestingEntity> testings = new ArrayList<>();

		final DateAndTime date    = RandomDAO.FAKER.date();
		final Medical     medical = RandomDAO.FAKER.medical();

		final Bool                      bool       = RandomDAO.FAKER.bool();
		final RandomEnum<Testprocedure> randomEnum = new RandomEnum<>(Testprocedure.class);

		for(int i = 0; i < numTesting; i++)
		{
			final MedicalEntity randomMedical = RandomDAO.randomMedical(medicals);
			final String        medicalId     = randomMedical.getViewId();
			
			testings.add(new TestingEntity(patienId, medicalId,
				LocalDate.ofInstant(date.past(RandomDAO.DAYS_ATMOST, TimeUnit.DAYS).toInstant(),
					ZoneId.systemDefault()),
				medical.hospitalName(), Boolean.valueOf(bool.bool()),
				randomEnum.random()));
		}
		return testings;
	}

	/**
	 * Creates the symptoms.
	 *
	 * @param numSymptom
	 *            the num symptom
	 * @param patienId
	 * @return the list
	 */
	public static List<SymptomEntity> createSymptoms(final int numSymptom, final String patienId)
	{
		final List<SymptomEntity> symptoms = new ArrayList<>();

		final DateAndTime          date       = RandomDAO.FAKER.date();
		final Medical              medical    = RandomDAO.FAKER.medical();
		final Number               number     = RandomDAO.FAKER.number();
		final RandomEnum<Symptoms> randomEnum = new RandomEnum<>(Symptoms.class);

		for(int i = 0; i < numSymptom; i++)
		{
			symptoms.add(new SymptomEntity(patienId, null,
				LocalDate.ofInstant(date.past(RandomDAO.DAYS_ATMOST, TimeUnit.DAYS).toInstant(),
					ZoneId.systemDefault()),
				medical.symptoms(), Integer.valueOf(number.numberBetween(1, 14)),
				Integer.valueOf(number.numberBetween(1, 10)), randomEnum.random()));
		}
		return symptoms;
	}

	/**
	 * Creates the medications.
	 *
	 * @param numMedication
	 *            the num medication
	 * @param patienId
	 * @param medicals
	 * @return the list
	 */
	public static List<MedicationEntity>
		createMedications(final int numMedication, final String patienId, final List<MedicalEntity> medicals)
	{
		final List<MedicationEntity> medications = new ArrayList<>();

		final DateAndTime            date       = RandomDAO.FAKER.date();
		final Medical                medical    = RandomDAO.FAKER.medical();
		final Number                 number     = RandomDAO.FAKER.number();
		final RandomEnum<Medication> randomEnum = new RandomEnum<>(Medication.class);

		for(int i = 0; i < numMedication; i++)
		{
			final MedicalEntity randomMedical = RandomDAO.randomMedical(medicals);
			final String        medicalId     = randomMedical.getViewId();

			medications.add(new MedicationEntity(patienId, medicalId,
				LocalDate.ofInstant(date.past(RandomDAO.DAYS_ATMOST, TimeUnit.DAYS).toInstant(),
					ZoneId.systemDefault()),
				medical.medicineName(), Integer.valueOf(number.numberBetween(1, 10)), randomEnum.random()));
		}
		return medications;
	}

	/**
	 * Creates the medicals.
	 *
	 * @param numMedicals
	 *            the num medicals
	 * @return the list
	 */
	public static List<MedicalEntity> createMedicals(final int numMedicals)
	{

		final List<MedicalEntity> medicals = new ArrayList<>();

		final Address                add        = RandomDAO.FAKER.address();
		final RandomEnum<Profession> randomEnum = new RandomEnum<>(Profession.class);

		for(int i = 0; i < numMedicals; i++)
		{
			medicals.add(new MedicalEntity(add.lastName(), add.streetAddress(), add.secondaryAddress(), add.city(),
				add.zipCode(), add.country(), randomEnum.random()));
		}
		return medicals;
	}

	/**
	 * Creates the patients.
	 *
	 * @param numPatients
	 *            the num patients
	 * @return
	 * @return the list
	 */
	public static List<PatientEntity> createPatients(final int numPatients)
	{
		/** The patients. */
		final List<PatientEntity> patients = new ArrayList<>();

		final Address            add        = RandomDAO.FAKER.address();
		final DateAndTime        date       = RandomDAO.FAKER.date();
		final RandomEnum<Gender> randomEnum = new RandomEnum<>(Gender.class);

		for(int i = 0; i < numPatients; i++)
		{

			patients.add(new PatientEntity(add.lastName(), add.streetAddress(), add.secondaryAddress(), add.city(),
				add.zipCode(), add.country(),
				LocalDate.ofInstant(date.birthday(20, 70).toInstant(), ZoneId.systemDefault()), randomEnum.random()));
		}
		return patients;
	}
	
	/**
	 * Any medical.
	 *
	 * @param medicals
	 *            the medicals
	 * @return the medical entity
	 */
	private static MedicalEntity randomMedical(final List<MedicalEntity> medicals)
	{
		final int           index   = RandomDAO.RND.nextInt(medicals.size());
		final MedicalEntity medical = medicals.get(index);

		return medical;
	}

	/**
	 * The Class RandomEnum.
	 *
	 * @param <E>
	 *            the element type
	 */
	private static class RandomEnum<E extends Enum<E>>
	{

		/** The values. */
		private final E[] values;

		/**
		 * Instantiates a new random enum.
		 *
		 * @param token
		 *            the token
		 */
		public RandomEnum(final Class<E> token)
		{
			this.values = token.getEnumConstants();
		}

		/**
		 * Random.
		 *
		 * @return the e
		 */
		public E random()
		{
			return this.values[RandomDAO.RND.nextInt(this.values.length)];
		}
	}
}
