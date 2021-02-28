
package com.zillus.coronadiary.domain;

import java.util.Objects;
import java.util.UUID;


// TODO: Auto-generated Javadoc
/**
 * The Class AbstractPersonEntity.
 */
public abstract class AbstractPersonEntity implements Comparable<AbstractPersonEntity>
{

	/** The id. */
	protected final String viewId;

	/** The name. */
	private String name;
	
	/** The adress 1. */
	private String adress1;
	
	/** The adress 2. */
	private String adress2;
	
	/** The city. */
	private String city;
	
	/** The zip code. */
	private Integer zipCode;
	
	/** The country. */
	private String country;

	/**
	 * Instantiates a new abstract person entity.
	 *
	 * @param viewId
	 *            the view id
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
	 */
	public AbstractPersonEntity(
		final String name,
		final String adress1,
		final String adress2,
		final String city,
		final Integer zipCode,
		final String country)
	{
		super();
		this.viewId  = UUID.randomUUID().toString();
		this.name    = name;
		this.adress1 = adress1;
		this.adress2 = adress2;
		this.city    = city;
		this.zipCode = zipCode;
		this.country = country;
	}
	
	public AbstractPersonEntity()
	{
		super();
		this.viewId = UUID.randomUUID().toString();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName()
	{
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(final String name)
	{
		this.name = name;
	}

	/**
	 * Gets the adress 1.
	 *
	 * @return the adress 1
	 */
	public String getAdress1()
	{
		return this.adress1;
	}

	/**
	 * Sets the adress 1.
	 *
	 * @param adress1
	 *            the new adress 1
	 */
	public void setAdress1(final String adress1)
	{
		this.adress1 = adress1;
	}

	/**
	 * Gets the adress 2.
	 *
	 * @return the adress 2
	 */
	public String getAdress2()
	{
		return this.adress2;
	}

	/**
	 * Sets the adress 2.
	 *
	 * @param adress2
	 *            the new adress 2
	 */
	public void setAdress2(final String adress2)
	{
		this.adress2 = adress2;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity()
	{
		return this.city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(final String city)
	{
		this.city = city;
	}

	/**
	 * Gets the zip code.
	 *
	 * @return the zip code
	 */
	public Integer getZipCode()
	{
		return this.zipCode;
	}

	/**
	 * Sets the zip code.
	 *
	 * @param zipCode
	 *            the new zip code
	 */
	public void setZipCode(final Integer zipCode)
	{
		this.zipCode = zipCode;
	}

	/**
	 * Gets the country.
	 *
	 * @return the country
	 */
	public String getCountry()
	{
		return this.country;
	}

	/**
	 * Sets the country.
	 *
	 * @param country
	 *            the new country
	 */
	public void setCountry(final String country)
	{
		this.country = country;
	}

	@Override
	public String toString()
	{
		return "AbstractPersonEntity [viewId=" + this.viewId + ", name=" + this.name + ", adress1=" + this.adress1
			+ ", adress2=" + this.adress2 + ", city=" + this.city + ", zipCode=" + this.zipCode + ", country="
			+ this.country + "]";
	}

	/**
	 * Compare to.
	 *
	 * @param otherObject
	 *            the other object
	 * @return the int
	 */
	@Override
	public int compareTo(final AbstractPersonEntity otherObject)
	{
		return this.name.compareTo(otherObject.name);
		
	}
	
	/**
	 * Gets the view id.
	 *
	 * @return the view id
	 */
	public String getViewId()
	{
		return this.viewId;
	}

	/**
	 * Hash code.
	 *
	 * @return the int
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		return Objects.hash(this.viewId);
	}

	/**
	 * Equals.
	 *
	 * @param obj
	 *            the obj
	 * @return true, if successful
	 */
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj)
	{
		if(this == obj)
		{
			return true;
		}
		if(obj == null)
		{
			return false;
		}
		if(this.getClass() != obj.getClass())
		{
			return false;
		}
		final AbstractPersonEntity other = (AbstractPersonEntity)obj;
		return Objects.equals(this.viewId, other.viewId);
	}
	
}
