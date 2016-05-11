/*
 * scenarioo-api
 * Copyright (C) 2016, scenarioo.org Development Team
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * As a special exception, the copyright holders of this library give you
 * permission to link this library with independent modules, according
 * to the GNU General Public License with "Classpath" exception as provided
 * in the LICENSE file that accompanied this code.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */

package org.scenarioo.model.docu.entities;

import org.scenarioo.model.docu.entities.base.DocuObjectMap;
import org.scenarioo.model.docu.entities.base.LabelableScenariooEntity;
import org.scenarioo.model.docu.entities.base.Labels;
import org.scenarioo.model.docu.entities.base.Status;

import java.io.Serializable;

/**
 * Informations to store and display for one test scenario (one webtest).
 * <p/>
 * It is important that each scenario gets a unique 'name' inside its belonging usecase.
 */
public class Scenario extends LabelableScenariooEntity<Scenario> implements Serializable {

	private String name;
	private String description;
	private String status;

	private Labels labels = new Labels();

	private DocuObjectMap properties = new DocuObjectMap();

	public Scenario() {
	}

	public Scenario(String name) {
		this.name = name;
	}

	public Scenario(final String name, final String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	/**
	 * A unique name for this scenario inside the {@link UseCase} it belongs to.
	 * <p/>
	 * Make sure to use descriptive names that stay stable as much as possible, such that you can compare scenarios
	 * between different builds.
	 */
	public Scenario setName(final String name) {
		this.name = name;
		return current();
	}

	public String getDescription() {
		return description;
	}

	/**
	 * (optional but recommended) More detailed description for current scenario (additionally to descriptive name).
	 */
	public Scenario setDescription(final String description) {
		this.description = description;
		return current();
	}

	public String getStatus() {
		return status;
	}

	/**
	 * Set status of current step.
	 * <p/>
	 * See also {@link #setStatus(String)} for setting additional application-specific states.
	 */
	public Scenario setStatus(final Status status) {
		return setStatus(Status.toKeywordNullSafe(status));
	}

	/**
	 * Status of the scenario (did this test fail or succeed?). <br/>
	 * Usual values are "success" or "failed".<br/>
	 * But you can use application specific additional values, like "not implemented", "unknown" etc. where it makes
	 * sense. Those additional values will be displayed in warning-style by the scenarioo webapplication.
	 */
	public Scenario setStatus(final String status) {
		this.status = status;
		return current();
	}

}
