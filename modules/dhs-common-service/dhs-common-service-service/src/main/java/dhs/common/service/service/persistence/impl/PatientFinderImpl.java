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

import java.util.Date;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;

import aQute.bnd.annotation.ProviderType;
import dhs.common.service.service.persistence.PatientFinder;
import dhs.common.service.service.persistence.PatientPersistence;

/**
 * The persistence implementation for the patient service.
 *
 * <p>
 * Caching information and settings can be found in
 * <code>portal.properties</code>
 * </p>
 *
 * @author sandip.patel
 * @see PatientPersistence
 * @see dhs.common.service.service.persistence.PatientUtil
 * @generated
 */
@ProviderType
public class PatientFinderImpl extends PatientFinderBaseImpl implements PatientFinder {

	private static final Log LOGGER = LogFactoryUtil.getLog(PatientFinderImpl.class);

	public User createUser(String title, String firstName, String middleName, String lastName, Date dateOfBirth,
			String mspNo, String emailAddress, String confirmEmail, String password, String confirmPassword,
			String[] question) {

		User user = null;

		String builtQuery = userCreateQuery(title, firstName, middleName, lastName, dateOfBirth, mspNo, emailAddress,
				confirmEmail, password, confirmPassword, question);
		Session session = null;

		try {
			session = openSession();

			SQLQuery queryObject = session.createSQLQuery(builtQuery);
			queryObject.setCacheable(false);

			return (User) QueryUtil.list(queryObject, getDialect(), 0, 0);
		} catch (Exception e) {
			LOGGER.error("Unable to fetch kits by all criteria.", e);
		} finally {
			closeSession(session);
		}
		return user;
	}

	private String userCreateQuery(String title, String firstName, String middleName, String lastName, Date dateOfBirth,
			String mspNo, String emailAddress, String confirmEmail, String password, String confirmPassword,
			String[] question) {

		return null;
	}

}