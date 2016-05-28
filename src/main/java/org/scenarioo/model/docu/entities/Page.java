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

import org.scenarioo.model.docu.entities.base.LabelableScenariooEntity;

import java.io.Serializable;

/**
 * Representation of a unique page of the application under test.
 */
public class Page extends LabelableScenariooEntity<Page> implements Serializable {

	private String name;

	private String description;

	public Page(final String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	/**
	 * Unique name of the page. For a webpage you usualy use the relative application internal url-path to that page or
	 * the relative-file-path of the template file rendering this page. Try to use names that are as short as possible
	 * and do not include any url parameters. Names should be as stable as possible, do not use names that might change
	 * on each new build.
	 */
	public Page setName(final String name) {
		this.name = name;
		return current();
	}

	public String getDescription() {
		return description;
	}

	public Page setDescription(String description) {
		this.description = description;
		return current();
	}

}
