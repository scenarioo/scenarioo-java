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

package org.scenarioo.model.format3.docu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Description and other informations to store and display for one use case (usualy tested in same use case test class).
 * <p/>
 * It is important that each usecase gets a unique 'name'.
 */
public class UseCase implements Serializable {

	private String id;
	private String name;
	private String description;
	private String status;
	private List<String> labels = new ArrayList<String>();

	private List<DocuObject> properties = new ArrayList<DocuObject>();

	public UseCase() {
	}

	public UseCase(final String name, final String description) {
		this();
		this.name = name;
		this.description = description;
		this.status = "";
	}

	public String getName() {
		return name;
	}

	/**
	 * A unique name for this usecase.
	 * <p/>
	 * Make sure to use descriptive names that stay stable as much as possible between multiple builds, such that you
	 * can compare usecases and its scenarios between different builds.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * (optional but recommended) More detailed description for current scenario (additionally to descriptive name).
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	/**
	 * Set status of current step.
	 * <p/>
	 * See also {@link #setStatus(String)} for setting additional application-specific states.
	 */
	public void setStatus(final Status status) {
		setStatus(Status.toKeywordNullSafe(status));
	}

	/**
	 * (optional) Status of the whole use case: did all tests for this use case succeed or not?<br/>
	 * Usual values are "success" or "failed".<br/>
	 * But you can use application specific additional values, like "not implemented", "unknown" etc. where it makes
	 * sense. Those additional values will be displayed in warning-style by the scenarioo webapplication.
	 * <p/>
	 * You do not have to set this value, if not set the value will be calculated from all contained scenarios, if no
	 * scenario is marked as "failed" the use case will be marked as "success" otherwise as "failed".
	 */
	public void setStatus(final String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}

	public List<DocuObject> getProperties() {
		return properties;
	}

	public void setProperties(List<DocuObject> properties) {
		this.properties = properties;
	}
}
