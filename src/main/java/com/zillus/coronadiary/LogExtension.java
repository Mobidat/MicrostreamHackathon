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
package com.zillus.coronadiary;

import com.rapidclipse.framework.server.RapServletService;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinSession;


/**
 * Servlet error handler, prints stacktraces by default. 
 */
public class LogExtension implements RapServletService.Extension
{
	@Override
	public void sessionCreated(
		final RapServletService service,
		final VaadinSession session,
		final VaadinRequest request)
	{
		session.setErrorHandler(event -> {
			
			event.getThrowable().printStackTrace();
			
		});
	}
}
