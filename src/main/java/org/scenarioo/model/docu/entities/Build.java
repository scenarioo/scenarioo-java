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

import org.scenarioo.model.docu.entities.base.ScenariooEntity;
import org.scenarioo.model.docu.entities.base.Status;

import java.io.Serializable;
import java.util.Date;

/**
 * Simple description for a documentation build. Simply contains most important properties of a build, like when it was
 * generated and whether all tests succeeded and what revision of the software was built and documented.
 */
public class Build extends ScenariooEntity<Build> implements Serializable {

	private String name;
	private String description;
	private String revision;
	private Date date;
	private String status;

	public Build() {
	}

	public Build(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * A unique name for this build.
	 * <p/>
	 * Name of a build defines its key under which aou can access it and especially defines the directory in the
	 * filepath where this build is stored. Apart from that the name for a build is not important.
	 * <p/>
	 * Name is not displayed in the web application user interface, there the revision and date as the unique label of a
	 * build is displayed.
	 */
	public Build setName(final String name) {
		this.name = name;
		return current();
	}

	public String getRevision() {
		return revision;
	}

	/**
	 * This is a unique identifier (usually a number) to identify which version in your version control system was
	 * built.
	 */
	public Build setRevision(final String revision) {
		this.revision = revision;
		return current();
	}

	public Date getDate() {
		return date;
	}

	/**
	 * The date and time when this documentation build was built.
	 */
	public Build setDate(final Date date) {
		this.date = date;
		return current();
	}

	public String getStatus() {
		return status;
	}

	/**
	 * Set status of current build.
	 * <p/>
	 * See also {@link #setStatus(String)} for setting additional application-specific states.
	 */
	public Build setStatus(final Status status) {
		return setStatus(Status.toKeywordNullSafe(status));
	}

	/**
	 * (optional) Status of the whole build: did all tests for this build succeed or not?<br/>
	 * Usual values are "success" or "failed".<br/>
	 * But you can use application specific additional value where it makes sense. Those additional values will be
	 * displayed in warning-style by the scenarioo webapplication.
	 */
	public Build setStatus(final String status) {
		this.status = status;
		return current();
	}

	public String getDescription() {
		return description;
	}

	public Build setDescription(String description) {
		this.description = description;
		return current();
	}

}
