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
package com.zillus.coronadiary.domain.enums;

import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Enum Symptoms.
 */
public enum Symptoms implements Serializable
{

	/** The cough. */
	COUGH,

	/** The fever. */
	FEVER,

	/** The fatigue. */
	FATIGUE,

	/** The rhinitis. */
	RHINITIS,

	/** The sore throat. */
	SORE_THROAT,

	/** The shortness of breath. */
	SHORTNESS_OF_BREATH,

	/** The diarrhea. */
	DIARRHEA,

	/** The loss of smell. */
	LOSS_OF_SMELL,

	/** The loss of taste. */
	LOSS_OF_TASTE;
	
	/**
	 * Gets the caption.
	 *
	 * @return the caption
	 */
	public String getCaption()
	{
		return this.name().substring(0, 1).toUpperCase() + this.name().substring(1).toLowerCase();
	}
}
