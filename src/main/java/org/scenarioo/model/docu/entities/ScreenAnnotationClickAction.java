package org.scenarioo.model.docu.entities;

public enum ScreenAnnotationClickAction {

	/**
	 * Let the user navigate to the next step when he clicks on the annotation
	 */
	toNextStep,

	/**
	 * Let the user navigate to a URL specified in property 'clickActionUrl' when he clicks on the annotation.
	 * 
	 * The URL will be opened in a seperate browser tab.
	 */
	toUrl;

}
