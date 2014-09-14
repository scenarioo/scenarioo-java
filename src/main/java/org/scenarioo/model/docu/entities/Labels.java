package org.scenarioo.model.docu.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.scenarioo.api.rules.CharacterChecker;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Labels implements Set<String> {
	
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
		if (labels == null) {
			throw new NullPointerException("Labels must not be null.");
		}
		for (String label : labels) {
			CharacterChecker.checkLabel(label);
		}
	}
	
	@Override
	public int size() {
		return labels.size();
	}
	
	@Override
	public boolean isEmpty() {
		return labels.isEmpty();
	}
	
	@Override
	public boolean contains(final Object o) {
		return labels.contains(o);
	}
	
	@Override
	public Iterator<String> iterator() {
		return labels.iterator();
	}
	
	@Override
	public Object[] toArray() {
		return labels.toArray();
	}
	
	@Override
	public <T> T[] toArray(final T[] a) {
		return labels.toArray(a);
	}
	
	@Override
	public boolean add(final String e) {
		return labels.add(e);
	}
	
	@Override
	public boolean remove(final Object o) {
		return labels.remove(o);
	}
	
	@Override
	public boolean containsAll(final Collection<?> c) {
		return labels.containsAll(c);
	}
	
	@Override
	public boolean addAll(final Collection<? extends String> c) {
		return labels.addAll(c);
	}
	
	@Override
	public boolean retainAll(final Collection<?> c) {
		return labels.retainAll(c);
	}
	
	@Override
	public boolean removeAll(final Collection<?> c) {
		return labels.removeAll(c);
	}
	
	@Override
	public void clear() {
		labels.clear();
	}
	
	@Override
	public boolean equals(final Object o) {
		return labels.equals(o);
	}
	
	@Override
	public int hashCode() {
		return labels.hashCode();
	}
	
}
