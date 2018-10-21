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

package dhs.common.service.service.impl;

import java.util.Date;

import com.liferay.portal.kernel.model.User;

import dhs.common.service.model.Patient;
import dhs.common.service.service.PatientLocalServiceUtil;
import dhs.common.service.service.base.PatientLocalServiceBaseImpl;

/**
 * The implementation of the patient local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link dhs.common.service.service.PatientLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author sandip.patel
 * @see PatientLocalServiceBaseImpl
 * @see dhs.common.service.service.PatientLocalServiceUtil
 */
public class PatientLocalServiceImpl extends PatientLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link dhs.common.service.service.PatientLocalServiceUtil} to access the patient local service.
	 */
	public User createPatient(String title,String firstName,String middleName,String lastName,Date dateOfBirth,String mspNo,String emailAddress,String confirmEmail,String password,String confirmPassword,String[] question){
		 User user=patientFinder.createUser(title, firstName, middleName, lastName, dateOfBirth, mspNo, emailAddress, confirmEmail, password, confirmPassword, question);
		 if(user != null){
			 Patient patient=patientLocalService.createPatient(counterLocalService.increment());
			 patient.setLrUserId(user.getUserId());
			 patient.setMspNo("");
			 PatientLocalServiceUtil.updatePatient(patient);
		 }
		 return user;
	}
		
}