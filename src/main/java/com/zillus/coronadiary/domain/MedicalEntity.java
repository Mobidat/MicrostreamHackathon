
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
		final Integer zipCode,
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
