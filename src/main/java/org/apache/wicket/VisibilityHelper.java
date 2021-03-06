/**
 * Copyright 2010 Molindo GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.wicket;

import org.apache.wicket.request.cycle.RequestCycle;

/**
 * utility to call methods with package visibility in org.apache.wicket
 */
public class VisibilityHelper {

	private VisibilityHelper() {
	}

	/**
	 * @deprecated use {@link ThreadContext#setRequestCycle(RequestCycle)}
	 *             directly
	 */
	@Deprecated
	public static void set(RequestCycle requestCycle) {
		ThreadContext.setRequestCycle(null);
	}
}
