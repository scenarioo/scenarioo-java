package org.scenarioo.model.docu.entities;

import javax.xml.bind.annotation.XmlEnumValue;

public enum ScreenAnnotationClickAction {

	/**
	 * Let the user navigate to the next step when he clicks on the annotation
	 */
	@XmlEnumValue(value = "toNextStep")
	TO_NEXT_STEP,

	/**
	 * Let the user navigate to a URL specified in property 'clickActionUrl' when he clicks on the annotation.
	 * 
	 * The URL will be opened in a seperate browser tab.
	 */
	@XmlEnumValue(value = "toUrl")
	TO_URL;

}
