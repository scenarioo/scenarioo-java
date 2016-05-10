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

import com.fasterxml.jackson.annotation.JsonInclude;
import org.scenarioo.model.format3.docu.entities.screenAnnotation.ScreenAnnotation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all the data collected from a webtest for one step of one scenario/webtest (except for the step image, which
 * has to be stored separately).
 */
public class Step implements Serializable {

	private int index;
	private String id;
	private String title;
	private String status;

	private Page page;
	private String visibleText;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<String> labels = new ArrayList<String>();

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<DocuObject> properties = new ArrayList<DocuObject>();

	// TODO: check if something like this is possible to easily serialize and deserialize:
	// private Map<String, DocuObject> properties2 = new LinkedHashMap<>();

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<DocuObject> propertyGroups = new ArrayList<DocuObject>();

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ScreenAnnotation> screenAnnotations = new ArrayList<ScreenAnnotation>();

	public Page getPage() {
		return page;
	}

	/**
	 * Information about the page this step belongs to (usually there are several steps that show the same UI page).
	 * <p/>
	 * This information is optional in case you do not have a page concept in your application.
	 */
	public void setPage(final Page page) {
		this.page = page;
	}

	/**
	 * Add a screen annotation to visualy mark a rectangular region inside the current step's screenshot with additional
	 * information (e.g. about the event that triggers the next step or other additional information).
	 */
	final
	public void addScreenAnnotation(final ScreenAnnotation screenAnnotation) {
		this.screenAnnotations.add(screenAnnotation);
	}

	public List<ScreenAnnotation> getScreenAnnotations() {
		return screenAnnotations;
	}

	public void setScreenAnnotations(final List<ScreenAnnotation> screenAnnotations) {
		this.screenAnnotations = screenAnnotations;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVisibleText() {
		return visibleText;
	}

	public void setVisibleText(String visibleText) {
		this.visibleText = visibleText;
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

	public void addProperty(DocuObject property) {
		this.properties.add(property);
	}

	public List<DocuObject> getPropertyGroups() {
		return propertyGroups;
	}

	public void addPropertyGroup(String labelKey, List<DocuObject> properties) {
		DocuObject propertyGroup = new DocuObject(labelKey);
		propertyGroup.addProperties(properties);
		this.propertyGroups.add(propertyGroup);
	}
}
