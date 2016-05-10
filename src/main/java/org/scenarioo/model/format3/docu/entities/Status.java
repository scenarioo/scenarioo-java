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

/**
 * Those are the default status values that scenarioo knows and supports out of the box to set for all status fields in
 * scenarioo.
 * 
 * But for all statuses there is also a more generic setter method where you can use any other application-specific
 * status simply as a string. All such unknown states will be interpreted and displayed as a warning status.
 */
public enum Status {
	
	/**
	 * Status for successfully succeeded and tested without errors.
	 */
	SUCCESS("success"),
	
	/**
	 * Status for failed testing the corresponding step, scenario, use case or build.
	 */
	FAILED("failed");
	
	Status(final String keyword) {
		this.keyword = keyword;
	}
	
	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}
	
	public static String toKeywordNullSafe(final Status status) {
		if (status == null) {
			return null;
		}
		else {
			return status.getKeyword();
		}
	}
}
