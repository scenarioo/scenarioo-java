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

package org.scenarioo.org.scenarioo.model.format3.docu.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a unique page of the application under test.
 */
public class Page implements Serializable {

	private String id;
	private String name;

	private List<DocuObject> properties = new ArrayList<DocuObject>();
	private  List<String> labels = new ArrayList<String>();

	public Page(final String name) {
		this(name, null);
	}

	public Page(final String name, final String id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	/**
	 * Unique name of the page. For a webpage you usualy use the relative application intenal url-path to that page or
	 * the relative-file-path of the template file rendering this page. Try to use names that are as short as possible
	 * and do not include any url parameters. Names should be as stable as possible, do not use names that might change
	 * on each new build.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<DocuObject> getProperties() {
		return properties;
	}

	public void setProperties(List<DocuObject> properties) {
		this.properties = properties;
	}

	public List<String> getLabels() {
		return labels;
	}

	public void setLabels(List<String> labels) {
		this.labels = labels;
	}
}
