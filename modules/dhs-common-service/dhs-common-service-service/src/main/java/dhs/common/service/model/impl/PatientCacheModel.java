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

package dhs.common.service.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import dhs.common.service.model.Patient;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Patient in entity cache.
 *
 * @author sandip.patel
 * @see Patient
 * @generated
 */
@ProviderType
public class PatientCacheModel implements CacheModel<Patient>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof PatientCacheModel)) {
			return false;
		}

		PatientCacheModel patientCacheModel = (PatientCacheModel)obj;

		if (patientId == patientCacheModel.patientId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, patientId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{patientId=");
		sb.append(patientId);
		sb.append(", lrUserId=");
		sb.append(lrUserId);
		sb.append(", mspNo=");
		sb.append(mspNo);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Patient toEntityModel() {
		PatientImpl patientImpl = new PatientImpl();

		patientImpl.setPatientId(patientId);
		patientImpl.setLrUserId(lrUserId);

		if (mspNo == null) {
			patientImpl.setMspNo(StringPool.BLANK);
		}
		else {
			patientImpl.setMspNo(mspNo);
		}

		patientImpl.resetOriginalValues();

		return patientImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		patientId = objectInput.readLong();

		lrUserId = objectInput.readLong();
		mspNo = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(patientId);

		objectOutput.writeLong(lrUserId);

		if (mspNo == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(mspNo);
		}
	}

	public long patientId;
	public long lrUserId;
	public String mspNo;
}