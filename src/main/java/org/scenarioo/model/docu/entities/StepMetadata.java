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

import org.scenarioo.model.docu.entities.generic.Details;

/**
 * Metadata for a step. This is a container for all additional detail data about a step that is only displayed on
 * details page for a step.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class StepMetadata implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String visibleText;
	
	private Details details = new Details();
	
	public String getVisibleText() {
		return visibleText;
	}
	
	/**
	 * (optional) You can set all visible text of a step here to provide possibility to search inside visible step text.
	 * But currently the scenarioo webapplication does not yet support full text search anyway and also not to display
	 * this visible text anywhere in the webapplication.
	 * 
	 * @param visibleText
	 */
	public void setVisibleText(final String visibleText) {
		this.visibleText = visibleText;
	}
	
	public Details getDetails() {
		return details;
	}
	
	/**
	 * Additional application specific details with additional metadata informations.
	 * 
	 * See {@link Details}
	 */
	public void setDetails(final Details details) {
		this.details = details;
	}
	
	/**
	 * Add application specific details as key-value-data-items.
	 * 
	 * See {@link Details} for what can be used as values.
	 */
	public void addDetail(final String key, final Object value) {
		details.put(key, value);
	}
	
}
