
package com.zillus.coronadiary.ui.utils;

import java.io.File;
import java.net.URL;

import javax.servlet.ServletContext;

import com.vaadin.flow.server.VaadinServlet;


/**
 * The Class ServletUtils.
 */
public abstract class ServletUtils
{

	/**
	 * Gets the base directory.
	 *
	 * @param servlet
	 *            the servlet
	 * @return the base directory
	 */
	public static File getBaseDirectory(final VaadinServlet servlet)
	{
		final String realPath = ServletUtils.getResourcePath(servlet.getServletContext(), "/");
		if(realPath == null)
		{
			return null;
		}
		return new File(realPath);
	}

	/**
	 * Gets the resource path.
	 *
	 * @param servletContext
	 *            the servlet context
	 * @param path
	 *            the path
	 * @return the resource path
	 */
	protected static String getResourcePath(
		final ServletContext servletContext,
		final String path)
	{
		String resultPath = null;
		resultPath = servletContext.getRealPath(path);
		if(resultPath != null)
		{
			return resultPath;
		}
		else
		{
			try
			{
				final URL url = servletContext.getResource(path);
				resultPath = url.getFile();
			}
			catch(final Exception e)
			{
				System.out.println(
					"Could not find resource path " + path + e);
			}
		}
		return resultPath;
	}
}
