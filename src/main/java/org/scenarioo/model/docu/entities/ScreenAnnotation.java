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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.scenarioo.model.docu.entities.generic.Details;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ScreenAnnotation implements Detailable {

	@XmlElement(required = true)
	private ScreenRegion region;

	private ScreenAnnotationStyle style = ScreenAnnotationStyle.DEFAULT;

	private String text = "";

	private String description = "";

	private Details details = new Details();

	private ScreenAnnotationClickAction clickAction = null;

	private String clickActionUrl = null;

	/**
	 * Default constructor, only for use by JAXB.
	 */
	public ScreenAnnotation() {
	}

	/**
	 * Constructor.
	 * 
	 * @param x
	 *            distance in pixels from left inside the screenshot
	 * @param y
	 *            distance in pixels from top inside the screenshot
	 * @param width
	 *            width in pixels inside he screenshot
	 * @param height
	 *            height in pixels inside he screenshot
	 */
	public ScreenAnnotation(final int x, final int y, final int width, final int height) {
		this.region = new ScreenRegion(x, y, width, height);
	}

	/**
	 * Set the rectangular region inside the screenshot to highlight and put an annotation on
	 * 
	 * @param x
	 *            distance in pixels from left inside the screenshot
	 * @param y
	 *            distance in pixels from top inside the screenshot
	 * @param width
	 *            width in pixels inside he screenshot
	 * @param height
	 *            height in pixels inside he screenshot
	 */
	public void setRegion(final int x, final int y, final int width, final int height) {
		setRegion(new ScreenRegion(x, y, width, height));
	}

	/**
	 * Set the rectangular region inside the screenshot to highlight and put an annotation on
	 */
	public void setRegion(final ScreenRegion region) {
		this.region = region;
	}

	public ScreenRegion getRegion() {
		return region;
	}

	public ScreenAnnotationStyle getStyle() {
		return style;
	}

	/**
	 * (optional) Set the visual style of the annotation (if not set, the same style as 'neutral' will be used).
	 */
	public void setStyle(final ScreenAnnotationStyle style) {
		this.style = style;
	}

	public String getText() {
		return text;
	}

	/**
	 * (optional) Set the text to display inside the screen on the annotation (should be short, use description for
	 * longer texts).
	 * Too long texts might be truncated inside the screenshot view in the documentation.
	 */
	public void setText(final String text) {
		this.text = text;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * (optional) Set a longer textual description for this annotation, this annotation is displayed below the shorter
	 * 'text'
	 * inside an info popup that can be opened on the annotation.
	 */
	public void setDescription(final String description) {
		this.description = description;
	}

	public ScreenAnnotationClickAction getClickAction() {
		return clickAction;
	}

	/**
	 * (optional) Set a click action to define what happens, when the annotation is clicked on.
	 */
	public void setClickAction(final ScreenAnnotationClickAction clickAction) {
		this.clickAction = clickAction;
	}

	public String getClickActionUrl() {
		return clickActionUrl;
	}

	/**
	 * (optional, but mandatory in case that clickAction is {@link ScreenAnnotationClickAction#toUrl})
	 * The URL to browse to when the annotation is clicked on. The URL will be opened in a separate browser tab.
	 */
	public void setClickActionUrl(final String clickActionUrl) {
		this.clickActionUrl = clickActionUrl;
	}

	@Override
	public Details addDetail(final String key, final Object value) {
		details.put(key, value);
		return details;
	}

	@Override
	public Details getDetails() {
		return details;
	}

	@Override
	public void setDetails(final Details details) {
		this.details = details;
	}

}
