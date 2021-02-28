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


/**
 * The Enum Vaccine.
 */
public enum Vaccine implements Serializable
{

	/** The biontech bnt162b2. */
	BIONTECH_BNT162B2,

	/** The moderna mrna1273. */
	MODERNA_MRNA1273,

	/** The astra zeneca azd1222. */
	ASTRA_ZENECA_AZD1222,

	/** The johnson johnson. */
	JOHNSON_JOHNSON,

	/** The curevac ad26. */
	CUREVAC_AD26,

	/** The sputnik v. */
	SPUTNIK_V,

	/** The corona vac. */
	CORONA_VAC;

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
