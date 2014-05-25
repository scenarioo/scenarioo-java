/* scenarioo-api
 * Copyright (C) 2014, scenarioo.org Development Team
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
 */

package org.scenarioo.model.docu.entities;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.scenarioo.api.ScenarioDocuWriter;
import org.scenarioo.model.docu.entities.generic.Details;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StepDescription implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int index = 0;
	private String title = "";
	private String status = "";
	
	/**
	 * TODO #195: Remove screenshot file name from API:<br/>
	 * Instead the image filename should always be calculated from step index.
	 */
	private String screenshotFileName;
	
	private final Details details = new Details();
	
	/**
	 * TODO #73: remove deprecated data fields<br/>
	 * all the following fields can be removed from the API, because we do not need them internaly anymore, and these
	 * fields have no effect anymore. The same data is calculated now by the webapplication internaly and saved in a
	 * separated data structure.
	 * 
	 * Why are these fields still here then? Why haven't they been removed yet?<br/>
	 * Removing this fields means a change in the data format and we need to ensure, that no project already using
	 * scenarioo has a problem afterwards with reading its step data into scenarioo, therefore we should provide a
	 * migration script, that removes these fields from existing files on importing builds.
	 */
	@Deprecated
	private int occurence = 0;
	@Deprecated
	private int relativeIndex = 0;
	@Deprecated
	private int variantIndex = 0;
	@Deprecated
	private StepIdentification previousStepVariant;
	@Deprecated
	private StepIdentification nextStepVariant;
	
	public int getIndex() {
		return index;
	}
	
	public void setIndex(final int index) {
		this.index = index;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(final String title) {
		this.title = title;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(final String status) {
		this.status = status;
	}
	
	@Deprecated
	public String getScreenshotFileName() {
		return screenshotFileName;
	}
	
	/**
	 * Do not use this method anymore, the image file name will be calculated for you. Use
	 * {@link ScenarioDocuWriter#getScreenshotFile(String, String, int)} to get the file where you should store your
	 * image for this step, or better use {@link ScenarioDocuWriter#saveScreenshot} directly to save your image for this
	 * step.
	 */
	@Deprecated
	public void setScreenshotFileName(final String screenshotFileName) {
		this.screenshotFileName = screenshotFileName;
	}
	
	public Details getDetails() {
		return details;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public StepIdentification getPreviousStepVariant() {
		return previousStepVariant;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public void setPreviousStepVariant(final StepIdentification previousStepVariant) {
		this.previousStepVariant = previousStepVariant;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public StepIdentification getNextStepVariant() {
		return nextStepVariant;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public void setNextStepVariant(final StepIdentification nextStepVariant) {
		this.nextStepVariant = nextStepVariant;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public int getOccurence() {
		return occurence;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public void setOccurence(final int occurence) {
		this.occurence = occurence;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public int getRelativeIndex() {
		return relativeIndex;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public void setRelativeIndex(final int relativeIndex) {
		this.relativeIndex = relativeIndex;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public int getVariantIndex() {
		return variantIndex;
	}
	
	/**
	 * Only for internal use, has no effect when setting it manually, will be removed in next API version.
	 */
	@Deprecated
	public void setVariantIndex(final int variantIndex) {
		this.variantIndex = variantIndex;
	}
	
	public void addDetails(final String key, final Object value) {
		details.addDetail(key, value);
	}
	
}
