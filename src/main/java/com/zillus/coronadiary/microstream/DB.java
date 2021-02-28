
package com.zillus.coronadiary.microstream;

import one.microstream.storage.configuration.Configuration;
import one.microstream.storage.types.EmbeddedStorageManager;


// TODO: Auto-generated Javadoc
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
