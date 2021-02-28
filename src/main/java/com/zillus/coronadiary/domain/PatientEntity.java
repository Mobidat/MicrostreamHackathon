
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

	/**
	 * Instantiates a new patient entity.
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
	 * @param birthday
	 *            the birthday
	 * @param gender
	 *            the gender
	 */
	public PatientEntity(
		final String name,
		final String adress1,
		final String adress2,
		final String city,
		final int zipCode,
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
