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

package org.scenarioo.writer;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.Assert;
import org.junit.Test;
import org.scenarioo.model.docu.entities.Branch;
import org.scenarioo.model.docu.entities.base.DocuObjectMap;
import org.scenarioo.model.docu.entities.Step;
import org.scenarioo.writer.utils.JsonUtil;

import java.io.IOException;

/**
 * A test to test that all entities are serialized and deserialized as expected
 */
public class JsonSerializationTest {

	@Test
	public void properties() throws IOException {
		DocuObjectMap properties = new DocuObjectMap();
		properties.add("aKey", "a value");
		properties.add("anotherKey", "another value").setType("AType");

		// Serialize
		String json = JsonUtil.stringify(properties);
		System.out.println("Properties:\n" + json + "\n====\n");

		// Deserialize
		DocuObjectMap propertiesResult = JsonUtil.parse(json, DocuObjectMap.class);
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
		String json = JsonUtil.stringify(branch);
		System.out.println("Branch:\n" + json + "\n====\n");

		// Deserialize
		Branch branchResult = JsonUtil.parse(json, Branch.class);
		Assert.assertEquals("a generic object", branchResult.getProperties().get("aKey").getValue());
		Assert.assertEquals("another object", branchResult.getProperties().get("anotherKey").getValue());
		Assert.assertEquals("AType", branchResult.getProperties().get("anotherKey").getType());
		Assert.assertEquals("empty properties on property object expected", 0, branchResult.getProperties().get("anotherKey").getProperties().size());
		Assert.assertEquals("empty items on property object expected", 0, branchResult.getProperties().get("anotherKey").getItems().size());
	}

	@Test
	public void step_minimal() throws IOException {

		Step step = new Step();
		step.setIndex(5);

		// Serialize
		String json = JsonUtil.stringify(step);
		System.out.println("Step minimal:\n" + json + "\n====\n");

		// Deserialize
		Step stepResult = JsonUtil.parse(json, Step.class);
		Assert.assertEquals(5, stepResult.getIndex());
		Assert.assertTrue(stepResult.getLabels().size() == 0);
	}


	@Test
	public void step() throws IOException {

		Step step = new Step();
		step.setIndex(5);
		step.getLabels().add("a label");

		// Serialize
		String json = JsonUtil.stringify(step);
		System.out.println("Step:\n" + json + "\n====\n");

		// Deserialize
		Step stepResult = JsonUtil.parse(json, Step.class);
		Assert.assertEquals(5, stepResult.getIndex());
		Assert.assertTrue(stepResult.getLabels().contains("a label"));
	}




}
