package org.scenarioo.model.docu.entities.base;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.scenarioo.model.docu.format.LabelFormat;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;


/**
 * Collection of labels.
 *
 * A label is a string to categorize objects. Their goal is an easy distinction of use cases, scenarios etc. in the UI.
 *
 * Format see {@link org.scenarioo.model.docu.format.LabelFormat}
 *
 * Each object should only have a small number of labels, ideally around 2 to 5.
 * Comprehensive additional docu data should be added to the `properties` of an object as `DocuObject`.
 *
 * Labels can be visualized with colors, configurable in the UI.
 */
public class Labels implements Set<String> {

	private Set<String> labels = new LinkedHashSet<String>();
	
	/**
	 * <p>
	 * Add a single label to the set of labels. This method can be used in a chained way:
	 * </p>
	 * <p>
	 * <code>Labels labels = new Labels().addLabel("first label").addLabel("second label");</code>
	 * </p>
	 * @param label *  A label string, which must only contain alpha-numeric characters, spaces, underscores and dashes. See
	 *            {@link org.scenarioo.model.docu.format.LabelFormat}.
	 */
	public Labels addLabel(final String label) {
		add(label);
		return this;
	}
	
	/**
	 * Replaces all labels with the supplied set of labels.
	 */
	public void setLabels(final Set<String> labels) {
		if (labels == null) {
			throw new NullPointerException("Labels must not be null.");
		}
		this.labels = new LinkedHashSet<String>();
		addAll(labels);
	}
	
	/**
	 * @return All labels as a set, never <code>null</code>.
	 */
	public Set<String> getLabels() {
		return labels;
	}

	public int size() {
		return labels.size();
	}
	
	public boolean isEmpty() {
		return labels.isEmpty();
	}
	
	public boolean contains(final Object o) {
		return labels.contains(sanitize(o));
	}

	public Iterator<String> iterator() {
		return labels.iterator();
	}
	
	public Object[] toArray() {
		return labels.toArray();
	}
	
	public <T> T[] toArray(final T[] a) {
		return labels.toArray(a);
	}
	
	public boolean add(final String label) {
		return labels.add(LabelFormat.sanitize(label));
	}
	
	public boolean remove(final Object o) {
		return labels.remove(sanitize(o));
	}
	
	public boolean containsAll(final Collection<?> c) {
		return labels.containsAll(sanitizeAll(c));
	}
	
	public boolean addAll(final Collection<? extends String> c) {
		return labels.addAll(LabelFormat.sanitizeAll(c));
	}
	
	public boolean retainAll(final Collection<?> c) {
		return labels.retainAll(sanitizeAll(c));
	}
	
	public boolean removeAll(final Collection<?> c) {
		return labels.removeAll(sanitizeAll(c));
	}
	
	public void clear() {
		labels.clear();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((labels == null) ? 0 : labels.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		Labels other = (Labels) obj;
		if (labels == null) {
			if (other.labels != null) return false;
		} else if (!labels.equals(other.labels)) return false;
		return true;
	}

	private Object sanitize(Object label) {
		if (label == null || !(label instanceof String)) {
			return label;
		}
		return LabelFormat.sanitize((String)label);
	}

	private List<Object> sanitizeAll(Collection<?> c) {
		List<Object> result = new LinkedList<Object>();
		for (Object o : c) {
			result.add(sanitize(o));
		}
		return result;
	}

}
