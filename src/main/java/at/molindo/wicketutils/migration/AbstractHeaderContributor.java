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
package at.molindo.wicketutils.migration;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.markup.html.IHeaderContributor;
import org.apache.wicket.markup.html.IHeaderResponse;

/**
 * This class is meant for easy migration only. Don't use it for new classes
 */
public abstract class AbstractHeaderContributor extends Behavior {

	private static final long serialVersionUID = 1L;

	@Override
	public final void renderHead(Component component, IHeaderResponse response) {
		IHeaderContributor[] contributors = getHeaderContributors();
		// do nothing if we don't need to
		if (contributors == null) {
			return;
		}

		for (int i = 0; i < contributors.length; i++) {
			if (response.wasRendered(contributors[i]) == false) {
				contributors[i].renderHead(response);
				response.markRendered(contributors[i]);
			}
		}
	}

	public abstract IHeaderContributor[] getHeaderContributors();
}
