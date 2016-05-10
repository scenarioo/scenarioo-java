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

package org.scenarioo.api.format3;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;
import org.scenarioo.model.format3.docu.entities.Branch;
import org.scenarioo.model.format3.docu.entities.Properties;
import org.scenarioo.model.format3.docu.entities.Step;

import java.io.IOException;

/**
 * A test to test that all entities are serialized and deserialized as expected
 */
public class JsonSerializationTest {

	@Test
	public void properties() throws IOException {
		Properties properties = new Properties();
		properties.add("aKey", "a value");
		properties.add("anotherKey", "another value").setType("AType");

		// Serialize
		String json = toJson(properties);

		// Deserialize
		Properties propertiesResult = readJson(json, Properties.class);
		Assert.assertEquals("a value", propertiesResult.get("aKey").getValue());
		Assert.assertEquals("another value", propertiesResult.get("anotherKey").getValue());
		Assert.assertEquals("another value", propertiesResult.get("anotherKey").getValue());
		Assert.assertEquals("AType", propertiesResult.get("anotherKey").getType());
	}


	@Test
	public void branch() throws IOException {

		Branch branch = new Branch("my example branch");
		branch.getProperties().add("aKey", "a generic object").setType("AType");
		branch.getProperties().add("anotherKey", "another object").setType("AType");

		// Serialize
		String json = toJson(branch);
		System.out.println(json);

		// Deserialize
		Branch branchResult = readJson(json, Branch.class);
		Assert.assertEquals("a generic object", branchResult.getProperties().get("aKey").getValue());
		Assert.assertEquals("another object", branchResult.getProperties().get("anotherKey").getValue());
		Assert.assertEquals("AType", branchResult.getProperties().get("anotherKey").getType());

	}

	@Test
	public void step() throws IOException {

		Step step = new Step();
		step.setIndex(5);
		step.getLabels().add("a label");

		// Serialize
		String json = toJson(step);
		System.out.println(json);

		// Deserialize
		Step stepResult = readJson(json, Step.class);
		Assert.assertEquals(5, stepResult.getIndex());
		Assert.assertEquals("a label", stepResult.getLabels().get(0));
	}

	private <E> E readJson(String json, Class<E> entityClass) throws IOException {
		ObjectMapper mapper = createJsonMapper();
		return mapper.readValue(json, entityClass);
	}

	private String toJson(Object object) throws JsonProcessingException {
		ObjectMapper mapper = createJsonMapper();
		return mapper.writeValueAsString(object);
	}

	private ObjectMapper createJsonMapper() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.enable(SerializationFeature.INDENT_OUTPUT);
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
		return mapper;
	}

}
