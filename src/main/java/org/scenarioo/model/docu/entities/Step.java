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
import org.scenarioo.model.docu.entities.base.LabelableScenariooEntity;
import org.scenarioo.model.docu.entities.screenAnnotations.ScreenAnnotation;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains all the data collected from a webtest for one step of one scenario/webtest (except for the step image, which
 * has to be stored separately).
 */
public class Step extends LabelableScenariooEntity<Step> implements Serializable {

	private int index;
	private String title;
	private String status;

	private Page page;

	private String visibleText;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<ScreenAnnotation> screenAnnotations = new ArrayList<ScreenAnnotation>();

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private DocuObjectMap sections = new DocuObjectMap();

	public Page getPage() {
		return page;
	}

	/**
	 * Information about the page this step belongs to (usually there are several steps that show the same UI page).
	 * <p/>
	 * This information is optional in case you do not have a page concept in your application.
	 */
	public Step setPage(final Page page) {
		this.page = page;
		return current();
	}

	/**
	 * Add a screen annotation to visualy mark a rectangular region inside the current step's screenshot with additional
	 * information (e.g. about the event that triggers the next step or other additional information).
	 */
	final
	public Step addScreenAnnotation(final ScreenAnnotation screenAnnotation) {
		this.screenAnnotations.add(screenAnnotation);
		return current();
	}

	public List<ScreenAnnotation> getScreenAnnotations() {
		return screenAnnotations;
	}

	public Step setScreenAnnotations(final List<ScreenAnnotation> screenAnnotations) {
		this.screenAnnotations = screenAnnotations;
		return current();
	}

	public int getIndex() {
		return index;
	}

	public Step setIndex(int index) {
		this.index = index;
		return current();
	}

	public String getTitle() {
		return title;
	}

	public Step setTitle(String title) {
		this.title = title;
		return current();
	}

	public String getStatus() {
		return status;
	}

	public Step setStatus(String status) {
		this.status = status;
		return current();
	}

	public String getVisibleText() {
		return visibleText;
	}

	public Step setVisibleText(String visibleText) {
		this.visibleText = visibleText;
		return current();
	}

	public Step setSections(DocuObjectMap sections) {
		this.sections = sections;
		return current();
	}

	public DocuObjectMap getSections() {
		return sections;
	}

	/**
	 * Add a docu object as a section to this entity.
	 * @param section a docu objetc with a required `labelKey` as section title
	 * @return this entity to chain further calls on it
     */
	public Step addSection(DocuObject section) {
		sections.add(section);
		return current();
	}



}
