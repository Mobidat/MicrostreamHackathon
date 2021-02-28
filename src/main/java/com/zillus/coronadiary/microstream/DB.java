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
package com.zillus.coronadiary.microstream;

import one.microstream.storage.configuration.Configuration;
import one.microstream.storage.types.EmbeddedStorageManager;


/**
 * The Class MicroStream.
 */
public final class DB
{
	
	/** The Constant root. */
	private final static DataRoot root = new DataRoot();
	
	/** The Constant storageManager. */
	private final static EmbeddedStorageManager storageManager;
	static
	{
		storageManager = Configuration.Default()
			.setBaseDirectoryInUserHome("microstream-data/CoronaDiary")
			.createEmbeddedStorageFoundation()
			.createEmbeddedStorageManager(DB.root)
			.start();
	}
	
	/**
	 * Root.
	 *
	 * @return the data root
	 */
	public static DataRoot root()
	{
		return DB.root;
	}
	
	/**
	 * Storage manager.
	 *
	 * @return the embedded storage manager
	 */
	public static EmbeddedStorageManager storageManager()
	{
		return DB.storageManager;
	}
	
	/**
	 * Shut down.
	 */
	public static void shutDown()
	{
		DB.storageManager.shutdown();
	}
	
	/**
	 * Instantiates a new micro stream.
	 */
	private DB()
	{
	}
}
