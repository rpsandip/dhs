/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package dhs.common.service.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.expando.kernel.model.ExpandoBridge;

import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.service.ServiceContext;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * <p>
 * This class is a wrapper for {@link Patient}.
 * </p>
 *
 * @author sandip.patel
 * @see Patient
 * @generated
 */
@ProviderType
public class PatientWrapper implements Patient, ModelWrapper<Patient> {
	public PatientWrapper(Patient patient) {
		_patient = patient;
	}

	@Override
	public Class<?> getModelClass() {
		return Patient.class;
	}

	@Override
	public String getModelClassName() {
		return Patient.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("patientId", getPatientId());
		attributes.put("lrUserId", getLrUserId());
		attributes.put("mspNo", getMspNo());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long patientId = (Long)attributes.get("patientId");

		if (patientId != null) {
			setPatientId(patientId);
		}

		Long lrUserId = (Long)attributes.get("lrUserId");

		if (lrUserId != null) {
			setLrUserId(lrUserId);
		}

		String mspNo = (String)attributes.get("mspNo");

		if (mspNo != null) {
			setMspNo(mspNo);
		}
	}

	@Override
	public boolean isCachedModel() {
		return _patient.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _patient.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _patient.isNew();
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		return _patient.getExpandoBridge();
	}

	@Override
	public com.liferay.portal.kernel.model.CacheModel<dhs.common.service.model.Patient> toCacheModel() {
		return _patient.toCacheModel();
	}

	@Override
	public dhs.common.service.model.Patient toEscapedModel() {
		return new PatientWrapper(_patient.toEscapedModel());
	}

	@Override
	public dhs.common.service.model.Patient toUnescapedModel() {
		return new PatientWrapper(_patient.toUnescapedModel());
	}

	@Override
	public int compareTo(dhs.common.service.model.Patient patient) {
		return _patient.compareTo(patient);
	}

	@Override
	public int hashCode() {
		return _patient.hashCode();
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _patient.getPrimaryKeyObj();
	}

	@Override
	public java.lang.Object clone() {
		return new PatientWrapper((Patient)_patient.clone());
	}

	/**
	* Returns the lr user uuid of this patient.
	*
	* @return the lr user uuid of this patient
	*/
	@Override
	public java.lang.String getLrUserUuid() {
		return _patient.getLrUserUuid();
	}

	/**
	* Returns the msp no of this patient.
	*
	* @return the msp no of this patient
	*/
	@Override
	public java.lang.String getMspNo() {
		return _patient.getMspNo();
	}

	@Override
	public java.lang.String toString() {
		return _patient.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _patient.toXmlString();
	}

	/**
	* Returns the lr user ID of this patient.
	*
	* @return the lr user ID of this patient
	*/
	@Override
	public long getLrUserId() {
		return _patient.getLrUserId();
	}

	/**
	* Returns the patient ID of this patient.
	*
	* @return the patient ID of this patient
	*/
	@Override
	public long getPatientId() {
		return _patient.getPatientId();
	}

	/**
	* Returns the primary key of this patient.
	*
	* @return the primary key of this patient
	*/
	@Override
	public long getPrimaryKey() {
		return _patient.getPrimaryKey();
	}

	@Override
	public void persist() {
		_patient.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_patient.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ExpandoBridge expandoBridge) {
		_patient.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.kernel.model.BaseModel<?> baseModel) {
		_patient.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		_patient.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the lr user ID of this patient.
	*
	* @param lrUserId the lr user ID of this patient
	*/
	@Override
	public void setLrUserId(long lrUserId) {
		_patient.setLrUserId(lrUserId);
	}

	/**
	* Sets the lr user uuid of this patient.
	*
	* @param lrUserUuid the lr user uuid of this patient
	*/
	@Override
	public void setLrUserUuid(java.lang.String lrUserUuid) {
		_patient.setLrUserUuid(lrUserUuid);
	}

	/**
	* Sets the msp no of this patient.
	*
	* @param mspNo the msp no of this patient
	*/
	@Override
	public void setMspNo(java.lang.String mspNo) {
		_patient.setMspNo(mspNo);
	}

	@Override
	public void setNew(boolean n) {
		_patient.setNew(n);
	}

	/**
	* Sets the patient ID of this patient.
	*
	* @param patientId the patient ID of this patient
	*/
	@Override
	public void setPatientId(long patientId) {
		_patient.setPatientId(patientId);
	}

	/**
	* Sets the primary key of this patient.
	*
	* @param primaryKey the primary key of this patient
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_patient.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		_patient.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PatientWrapper)) {
			return false;
		}

		PatientWrapper patientWrapper = (PatientWrapper)obj;

		if (Objects.equals(_patient, patientWrapper._patient)) {
			return true;
		}

		return false;
	}

	@Override
	public Patient getWrappedModel() {
		return _patient;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _patient.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _patient.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_patient.resetOriginalValues();
	}

	private final Patient _patient;
}