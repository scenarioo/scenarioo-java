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

package org.scenarioo.model.docu.entities.generic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

/**
 * Collection of application specific additional information to store and display in the scenarioo documentation.
 * <p>
 * A client of the scenarioo-API can add any key-value-data to this detail informations.
 * <p>
 * Following type of objects are possible values inside the details:
 * <ul>
 * <li>{@link String}: for usual text information</li>
 * <li>{@link ObjectDescription}: for describing an object (described through a type and a name and additional
 * key-value-details again).</li>
 * <li>{@link ObjectReference}: for referencing an {@link ObjectDescription} that was already stored elsewhere, only
 * through its type and name without storing all the details information again.</li>
 * <li>{@link ObjectList}: for a list of such value items (same types allowed as content types again).</li>
 * <li>{@link ObjectTreeNode}: for complex tree structures (containing same content types as possible node objects
 * again).</li>
 * </ul>
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Details implements Map<String, Object>, Serializable {

	private final Map<String, Object> properties = new LinkedHashMap<>();

	public Map<String, Object> getProperties() {
		return properties;
	}

	/**
	 * Add a key value pair as a information item to this details. The details will be presented in same order as added
	 * to the details object in the documentation.
	 * <p>
	 * See class description of {@link Details} about possible types to use as values.
	 */
	public Details addDetail(final String key, final Object value) {
		if (value != null) {
			properties.put(key, value);
		} else {
			properties.remove(key);
		}
		return this;
	}

	/**
	 * Use to describe an object (described through a type and a name and additional
	 * key-value-details again).
	 *
	 * <p>
	 * See class description of {@link Details} about other possible types to use.
	 */
	public Details addDetail(final String key, final ObjectDescription objectDescription) {
		return this.addDetail(key, (Object) objectDescription);
	}

	/**
	 * Use to reference an {@link ObjectDescription} that was already stored elsewhere, only
	 * through its type and name without storing all the details information again.
	 *
	 * <p>
	 * See class description of {@link Details} about other possible types to use.
	 */
	public Details addDetail(final String key, final ObjectReference objectReference) {
		return this.addDetail(key, (Object) objectReference);
	}

	/**
	 * Use for complex tree structures containing same content types as possible node objects.
	 *
	 * <p>
	 * See class description of {@link Details} about other possible types to use.
	 */
	public Details addDetail(final String key, final ObjectTreeNode<?> objectTreeNode) {
		return this.addDetail(key, (Object) objectTreeNode);
	}

	/**
	 * Use for a list of value items (same types allowed as content types again).
	 *
	 * <p>
	 * See class description of {@link Details} about other possible types to use.
	 */
	public Details addDetail(final String key, final ObjectList<?> objectList) {
		return this.addDetail(key, (Object) objectList);
	}

	public Object getDetail(final String key) {
		return properties.get(key);
	}

	@Override
	public int size() {
		return properties.size();
	}

	@Override
	public boolean isEmpty() {
		return properties.isEmpty();
	}

	@Override
	public boolean containsKey(final Object key) {
		return properties.containsKey(key);
	}

	@Override
	public boolean containsValue(final Object value) {
		return properties.containsValue(value);
	}

	@Override
	public Object get(final Object key) {
		return properties.get(key);
	}

	@Override
	public Object put(final String key, final Object value) {
		return properties.put(key, value);
	}

	@Override
	public Object remove(final Object key) {
		return properties.remove(key);
	}

	@Override
	public void putAll(final Map<? extends String, ? extends Object> m) {
		properties.putAll(m);
	}

	@Override
	public void clear() {
		properties.clear();
	}

	@Override
	public Set<String> keySet() {
		return properties.keySet();
	}

	@Override
	public Collection<Object> values() {
		return properties.values();
	}

	@Override
	public Set<java.util.Map.Entry<String, Object>> entrySet() {
		return properties.entrySet();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + properties.hashCode();
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Details other = (Details) obj;
		return properties.equals(other.properties);
	}

}
