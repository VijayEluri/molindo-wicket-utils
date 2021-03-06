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

package at.molindo.wicketutils.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Locale;

import org.apache.wicket.Application;
import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.junit.Test;

import at.molindo.wicketutils.utils.DummyApplication.HomePage;

public class MockUtilsTest {

	@Test
	public void withSession() throws Exception {
		DummyApplication testApp = new DummyApplication();
		try {

			assertFalse(Application.exists());
			assertFalse(Session.exists());
			assertFalse(RequestCycle.get() != null);

			String stringResource = MockUtils.withRequest(testApp, new MockRequestCallback<String>() {

				@Override
				public String call() {
					// some basic testing
					assertTrue(Application.exists());
					assertFalse(Session.exists());
					assertTrue(RequestCycle.get() != null);

					return new StringResourceModel("someResource", (IModel<?>) null, Model.of("default value"))
							.getString();
				}

			});
			assertEquals("default value", stringResource);

			String url = MockUtils.withRequest(testApp, new MockRequestCallback<String>() {

				@Override
				public String call() {
					return RequestCycle.get().urlFor(HomePage.class, null).toString();
				}

			});
			assertEquals(".", url);

			Locale locale = MockUtils.withRequest(testApp, new IMockRequestCallback<Locale>() {

				@Override
				public void configure(MockRequest request) {
					request.setLocale(Locale.GERMAN);
				}

				@Override
				public Locale call() {
					return Session.get().getLocale();
				}

			});
			assertEquals(Locale.GERMAN, locale);

			assertFalse(Application.exists());
			assertFalse(Session.exists());
			assertFalse(RequestCycle.get() != null);
		} finally {
			testApp.destroy();
		}
	}

}
