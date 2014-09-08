package org.scenarioo.model.docu.entities;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.scenarioo.api.rules.CharacterChecker;

import com.google.common.base.Preconditions;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Labels {
	
	@XmlElement(name = "label")
	private Set<String> labels = new LinkedHashSet<String>();
	
	/**
	 * <p>
	 * Add a single label to the set of labels. This method can be used in a chained way:
	 * </p>
	 * <p>
	 * <code>Labels labels = new Labels().addLabel("first label").addLabel("second label");</code>
	 * </p>
	 */
	public Labels addLabel(final String label) {
		CharacterChecker.checkLabel(label);
		labels.add(label);
		return this;
	}
	
	/**
	 * Replaces all labels with the supplied set of labels.
	 */
	public void setLabels(final Set<String> labels) {
		checkLabels(labels);
		this.labels = new LinkedHashSet<String>(labels);
	}
	
	/**
	 * @return All labels as a set, never <code>null</code>.
	 */
	public Set<String> getLabels() {
		if (labels == null) {
			labels = new HashSet<String>();
		}
		return labels;
	}
	
	private void checkLabels(final Set<String> labels) {
		Preconditions.checkNotNull(labels, "Labels must not be null.");
		for (String label : labels) {
			CharacterChecker.checkLabel(label);
		}
	}
	
}
