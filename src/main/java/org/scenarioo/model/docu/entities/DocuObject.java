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
import org.scenarioo.model.docu.entities.base.ScenariooEntity;
import org.scenarioo.model.docu.format.IdentifierFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DocuObject extends ScenariooEntity<DocuObject> implements Serializable {

	private String labelKey;
	private String value;
	private String type;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<DocuObject> items = new ArrayList<DocuObject>();

	public DocuObject() {
	}

	public DocuObject(String value) {
		this.value = value;
	}

	public String getLabelKey() {
		return labelKey;
	}

	public DocuObject setLabelKey(String labelKey) {
		this.labelKey = labelKey;
		return current();
	}

	public String getValue() {
		return value;
	}

	public DocuObject setValue(String value) {
		this.value = value;
		return current();
	}

	public String getType() {
		return type;
	}

	public DocuObject setType(String type) {
		this.type = IdentifierFormat.sanitize(type);
		return current();
	}

	public List<DocuObject> getItems() {
		return items;
	}

	public DocuObject setItems(List<DocuObject> items) {
		this.items = items;
		return current();
	}

	public DocuObject addItem(DocuObject object) {
		this.items.add(object);
		return current();
	}

	/**
	 * Add a simple String value
	 * @param value the value (display text)
	 * @return the createed docu object that was added to the items list (for setting additional poperties on it)
     */
	public DocuObject addItem(String value) {
		DocuObject object = DocuObject.value(value);
		addItem(object);
		return object;
	}

	/**
	 * Create a simple value object
	 */
	public static DocuObject value(String value) {
		return new DocuObject(value);
	}

	/**
	 * Create a typed object.
	 * @param type the type identifier to identify objects of same type
	 * @param value a unique display value for this object (will also be used as identifier for the object in a sanitized form, unless setId is used)
	 */
	public static DocuObject object(String type, String value) {
		return new DocuObject(value).setType(type);
	}

	/**
	 * Create a DocuObject to represent a `section` with a label Key as a title of the section.
	 *
	 * Use other setter and add methods to attach data tot the section.
	 *
	 * @param sectionTitleAsLabelKey the unique key and title to use for this section
	 * @return the DocuObject for this section
     */
	public static DocuObject section(String sectionTitleAsLabelKey) {
		DocuObject object = new DocuObject();
		object.setLabelKey(sectionTitleAsLabelKey);
		return object;
	}

}
