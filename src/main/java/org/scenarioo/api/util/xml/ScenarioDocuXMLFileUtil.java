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


package org.scenarioo.api.util.xml;

import org.scenarioo.api.exception.ResourceNotFoundException;
import org.scenarioo.api.files.ObjectFromDirectory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Writing or reading of all ScenarioDocu entities to XML files and back
 */
public class ScenarioDocuXMLFileUtil {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScenarioDocuXMLFileUtil.class);

	public static <T> void marshal(final T object, final File destFile) {
		try (FileOutputStream fos = new FileOutputStream(destFile)) {
			ScenarioDocuXMLUtil.marshal(object, fos);
		} catch (Exception e) {
			throw new RuntimeException("Could not marshall Object of type " + object.getClass().getName()
					+ " into file: " + destFile.getAbsolutePath(), e);
		}
	}

	public static <T> T unmarshal(final Class<T> targetClass, final File srcFile) {
		if (!srcFile.exists()) {
			throw new ResourceNotFoundException(srcFile.getAbsolutePath());
		}
		try (FileInputStream fis = new FileInputStream(srcFile)) {
			return ScenarioDocuXMLUtil.unmarshal(targetClass, fis);
		} catch (Exception e) {
			throw new RuntimeException("Could not unmarshall " + srcFile.getAbsolutePath(), e);
		}
	}

	public static <T> List<T> unmarshalListOfFilesIgnoreInvalid(final Class<T> targetClass, final List<File> files) {
		List<T> result = new ArrayList<T>();
		for (File file : files) {
			try {
				result.add(unmarshal(targetClass, file));
			} catch (Throwable e) {
				LOGGER.error("Could not parse invalid File: " + file.getAbsolutePath() + " - will ignore this object.");
			}
		}
		return result;
	}

	public static <T> List<T> unmarshalListOfFiles(final Class<T> targetClass, final List<File> files) {
		List<T> result = new ArrayList<T>();
		for (File file : files) {
			result.add(unmarshal(targetClass, file));
		}
		return result;
	}

	/**
	 * Read all passed files and unmarshall these to the given object type, also return the name of the directory the
	 * object was loaded from.
	 */
	public static <T> List<ObjectFromDirectory<T>> unmarshalListOfFilesWithDirNames(
			final List<File> files, final Class<T> targetClass, final Class<?>... classesToBind) {
		List<ObjectFromDirectory<T>> result = new ArrayList<ObjectFromDirectory<T>>();
		for (File file : files) {
			result.add(new ObjectFromDirectory<T>(unmarshal(targetClass, file), file
					.getParentFile().getName()));
		}
		return result;
	}

	/**
	 * Read all passed files and unmarshall these to the given object type, also return the name of the directory the
	 * object was loaded from.
	 */
	public static <T> List<ObjectFromDirectory<T>> unmarshalListOfFilesWithDirNamesIgnoreInvalid(
			final List<File> files, final Class<T> targetClass, final Class<?>... classesToBind) {
		List<ObjectFromDirectory<T>> result = new ArrayList<ObjectFromDirectory<T>>();
		for (File file : files) {
			try {
				result.add(new ObjectFromDirectory<T>(unmarshal(targetClass, file), file
						.getParentFile().getName()));
			} catch (Throwable e) {
				LOGGER.error("Could not parse invalid File: " + file.getAbsolutePath() + " - will ignore this object.");
			}
		}
		return result;
	}

}
