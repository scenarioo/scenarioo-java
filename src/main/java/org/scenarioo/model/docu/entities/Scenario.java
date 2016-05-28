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

import com.fasterxml.jackson.annotation.JsonInclude;
import org.scenarioo.model.docu.entities.base.DocuObjectMap;
import org.scenarioo.model.docu.entities.base.ScenariooLabeledEntity;
import org.scenarioo.model.docu.entities.base.Status;

import java.io.Serializable;

/**
 * Informations to store and display for one test scenario (one webtest).
 * <p/>
 * It is important that each scenario gets a unique 'name' inside its belonging usecase.
 */
public class Scenario extends ScenariooLabeledEntity<Scenario> implements Serializable {

	private String status;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private DocuObjectMap sections = new DocuObjectMap();

	public Scenario() {
		super();
	}

	public Scenario(String name) {
		super(name);
	}

	public Scenario(final String name, final String description) {
		super(name, description);
	}

	/**
	 * A unique name for this scenario inside the {@link UseCase} it belongs to.
	 * <p/>
	 * Make sure to use descriptive names that stay stable as much as possible, such that you can compare scenarios
	 * between different builds.
	 */
	public Scenario setName(final String name) {
		return super.setName(name);
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

	/**
	 * Sections are special additional docu object data for additional details data of your object,
	 * that are visualized as special collapsable sections on the right side details view.
	 *
	 * Every section must have a `labelKey` as section title and can have as much other data as all other DocuObjects can typically have.
	 *
	 * @param sections the sections to add
	 * @return the current entity to chain further methods
	 */
	public Scenario setSections(DocuObjectMap sections) {
		this.sections = sections;
		return current();
	}

	public DocuObjectMap getSections() {
		return sections;
	}

	/**
	 * Add a docu object as a section with additional additional application specific data to this entity.
	 * @param section a docu object with a required `labelKey` as section title and key for this section
	 * @return this entity to chain further calls on it
	 */
	public Scenario addSection(DocuObject section) {
		sections.add(section);
		return current();
	}

	/**
	 * Create and add a docu object as a section with additional application specific data to this entity.
	 * @param labelKeyAsSectionTitle a docu object with a required `labelKey` as section title and key for this section
	 * @return the created section object to attach data to the section
	 */
	public DocuObject addSection(String labelKeyAsSectionTitle) {
		return sections.add(labelKeyAsSectionTitle);
	}


}
