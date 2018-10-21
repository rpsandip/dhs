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

package dhs.common.service.service.persistence.impl;

import com.liferay.portal.kernel.bean.BeanReference;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;

import dhs.common.service.model.Patient;
import dhs.common.service.service.persistence.PatientPersistence;

/**
 * @author sandip.patel
 * @generated
 */
public class PatientFinderBaseImpl extends BasePersistenceImpl<Patient> {
	public PatientFinderBaseImpl() {
		setModelClass(Patient.class);
	}

	/**
	 * Returns the patient persistence.
	 *
	 * @return the patient persistence
	 */
	public PatientPersistence getPatientPersistence() {
		return patientPersistence;
	}

	/**
	 * Sets the patient persistence.
	 *
	 * @param patientPersistence the patient persistence
	 */
	public void setPatientPersistence(PatientPersistence patientPersistence) {
		this.patientPersistence = patientPersistence;
	}

	@BeanReference(type = PatientPersistence.class)
	protected PatientPersistence patientPersistence;
}