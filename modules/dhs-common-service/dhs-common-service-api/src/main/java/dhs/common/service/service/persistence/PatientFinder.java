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

package dhs.common.service.service.persistence;

import aQute.bnd.annotation.ProviderType;

/**
 * @author sandip.patel
 * @generated
 */
@ProviderType
public interface PatientFinder {
	public com.liferay.portal.kernel.model.User createUser(
		java.lang.String title, java.lang.String firstName,
		java.lang.String middleName, java.lang.String lastName,
		java.util.Date dateOfBirth, java.lang.String mspNo,
		java.lang.String emailAddress, java.lang.String confirmEmail,
		java.lang.String password, java.lang.String confirmPassword,
		java.lang.String[] question);
}