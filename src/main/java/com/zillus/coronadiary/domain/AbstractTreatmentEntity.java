
package com.zillus.coronadiary.domain;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;


/**
 * The Class AbstractTreatmentEntity.
 */
public abstract class AbstractTreatmentEntity implements Comparable<AbstractTreatmentEntity>
{

	/** The view id. */
	protected final String viewId;

	/** The patient id. */
	private String patientId;
	
	/** The medical id. */
	private String medicalId;

	/** The date. */
	private LocalDate date;

	/** The name. */
	private String name;

	/** The type. */
	private String type;

	public AbstractTreatmentEntity(final LocalDate date, final String name, final String type)
	{
		super();
		
		this.date   = date;
		this.name   = name;
		this.type   = type;
		this.viewId = UUID.randomUUID().toString();
	}

	/**
	 * Instantiates a new abstract treatment entity.
	 */
	public AbstractTreatmentEntity()
	{
		this.viewId = UUID.randomUUID().toString();
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate()
	{
		return this.date;
	}
	
	/**
	 * Sets the date.
	 *
	 * @param date
	 *            the new date
	 */
	public void setDate(final LocalDate date)
	{
		this.date = date;
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
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType()
	{
		return this.type;
	}
	
	/**
	 * Sets the type.
	 *
	 * @param type
	 *            the new type
	 */
	public void setType(final String type)
	{
		this.type = type;
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString()
	{
		return "AbstractTreatmentEntity [viewId=" + this.viewId + ", patientId=" + this.patientId + ", medicalId="
			+ this.medicalId + ", date=" + this.date + ", name=" + this.name + ", type=" + this.type + "]";
	}
	
	/**
	 * Compare to.
	 *
	 * @param otherObject
	 *            the other object
	 * @return the int
	 */
	@Override
	public int compareTo(final AbstractTreatmentEntity otherObject)
	{
		return this.date.compareTo(otherObject.date);
	}
	
	/**
	 * Gets the patient id.
	 *
	 * @return the patient id
	 */
	public String getPatientId()
	{
		return this.patientId;
	}
	
	/**
	 * Sets the patient id.
	 *
	 * @param patientId
	 *            the new patient id
	 */
	public void setPatientId(final String patientId)
	{
		this.patientId = patientId;
	}

	/**
	 * Gets the medical id.
	 *
	 * @return the medical id
	 */
	public String getMedicalId()
	{
		return this.medicalId;
	}

	/**
	 * Sets the medical id.
	 *
	 * @param medicalId
	 *            the new medical id
	 */
	public void setMedicalId(final String medicalId)
	{
		this.medicalId = medicalId;
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
		final AbstractTreatmentEntity other = (AbstractTreatmentEntity)obj;
		return Objects.equals(this.viewId, other.viewId);
	}
	
}
