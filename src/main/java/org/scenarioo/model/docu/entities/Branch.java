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

import org.scenarioo.model.docu.entities.base.ScenariooNamedEntity;

import java.io.Serializable;

/**
 * Description of the branch of your application that you are documenting. Can be used to have different documentations
 * for different versions or product lines of your software and to document them separately (e.g. different release
 * branches for different releases).
 * <p/>
 * In case you want to document different applications or modules in the same scenarioo web application, you could also
 * use the branch as a structuring element to document different applications or modules.
 */
public class Branch extends ScenariooNamedEntity<Branch> implements Serializable {

	public Branch() {
		super("");
	}

	public Branch(final String name) {
		super(name);
	}

	public Branch(final String name, final String description) {
		super(name,description);
	}

	/**
	 * Unique name of the branch. This name is also displayed in the UI to select a branch.
	 */
	public Branch setName(final String name) {
		return super.setName(name);
	}

}
