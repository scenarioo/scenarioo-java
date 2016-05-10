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
import java.util.Collection;
import java.util.List;

public class DocuObject implements Serializable {

	private String labelKey;
	private String value;
	private String id;
	private String type;
	private List<DocuObject> properties = new ArrayList<DocuObject>();
	private List<DocuObject> items = new ArrayList<DocuObject>();

	public DocuObject(String labelKey) {
		this(labelKey, null, null, null);
	}

	public DocuObject(String labelKey, String value) {
		this(labelKey, value, null, null);
	}

	public DocuObject(String labelKey, String value, String type) {
		this(labelKey, value, type, null);
	}

	public DocuObject(String labelKey, String value, String type, String id) {
		this.labelKey = labelKey;
		this.value = value;
		this.type = type;
		// TODO: infer if null
		this.id = id;
	}

	public String getLabelKey() {
		return labelKey;
	}

	public void setLabelKey(String labelKey) {
		this.labelKey = labelKey;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<DocuObject> getProperties() {
		return properties;
	}

	public void addProperty(DocuObject property) {
		this.properties.add(property);
	}

	public void addProperties(Collection<DocuObject> properties) {
		this.properties.addAll(properties);
	}

	public List<DocuObject> getItems() {
		return items;
	}

	public void addItem(DocuObject item) {
		this.items.add(item);
	}
}
