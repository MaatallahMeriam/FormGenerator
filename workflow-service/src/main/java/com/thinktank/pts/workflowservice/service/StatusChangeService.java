package com.thinktank.pts.workflowservice.service;

/**
 * 
 * @author hedfim
 * @since 2023-11-20
 *
 */
public interface StatusChangeService {

	/**
	 * returns status diagram by the type
	 * 
	 * @return string contain xml
	 */
	public String getStatusChangeDiagram(String statusType);

	/**
	 * creates or updates status and status change
	 * 
	 * @param xmlString
	 *            the bpmn diagram contain changes
	 * @param statusType
	 *            the sub type of status
	 * @return
	 */
	public String createOrUpdateStatus(String xmlString, Long typeId);

	/**
	 * returns status diagram by the given type id
	 * 
	 * @return string contain xml
	 */
	public String getStatusChangeDiagramByType(Long typeId);

}
