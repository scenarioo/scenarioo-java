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
 * Description of the branch of your application that you are documenting. Can be used to have different documentations
 * for different versions or product lines of your software and to document them separately (e.g. different release
 * branches for different releases).
 * <p/>
 * In case you want to document different applications or modules in the same scenarioo webapplication, you could also
 * use the branch as a structuring element to document different applications or modules.
 */
public class Branch implements Serializable {
	private String id;
	private String name;
	private String description;

	private List<DocuObject> properties = new ArrayList<DocuObject>();
	private List<DocuObject> items = new ArrayList<DocuObject>();

	public Branch() {
		this(null, "", "");
	}

	public Branch(final String name) {
		this(null, name, "");
	}

	public Branch(final String name, final String description) {
		this(null, name, description);
	}

	public Branch(final String id, final String name, final String description) {
		this.id = (id == null) ? this.inferId(name) : id;
		this.name = name;
		this.description = description;
	}

	private String inferId(final String name) {
		// TODO: infer intelligent id; make static; refactor out of this class
		return name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	/**
	 * Unique name of the banch. This name is also displayed in the UI to select a branch.
	 */
	public void setName(final String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * More detailed description of a branch.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	public List<DocuObject> getItems() {
		return items;
	}

	public void setItems(List<DocuObject> items) {
		this.items = items;
	}

	public List<DocuObject> getProperties() {
		return properties;
	}

	public void setProperties(List<DocuObject> properties) {
		this.properties = properties;
	}
}
