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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import dhs.common.service.exception.NoSuchPatientException;
import dhs.common.service.model.Patient;
import dhs.common.service.model.impl.PatientImpl;
import dhs.common.service.model.impl.PatientModelImpl;
import dhs.common.service.service.persistence.PatientPersistence;

import java.io.Serializable;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the patient service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author sandip.patel
 * @see PatientPersistence
 * @see dhs.common.service.service.persistence.PatientUtil
 * @generated
 */
@ProviderType
public class PatientPersistenceImpl extends BasePersistenceImpl<Patient>
	implements PatientPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link PatientUtil} to access the patient persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = PatientImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(PatientModelImpl.ENTITY_CACHE_ENABLED,
			PatientModelImpl.FINDER_CACHE_ENABLED, PatientImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(PatientModelImpl.ENTITY_CACHE_ENABLED,
			PatientModelImpl.FINDER_CACHE_ENABLED, PatientImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(PatientModelImpl.ENTITY_CACHE_ENABLED,
			PatientModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public PatientPersistenceImpl() {
		setModelClass(Patient.class);
	}

	/**
	 * Caches the patient in the entity cache if it is enabled.
	 *
	 * @param patient the patient
	 */
	@Override
	public void cacheResult(Patient patient) {
		entityCache.putResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
			PatientImpl.class, patient.getPrimaryKey(), patient);

		patient.resetOriginalValues();
	}

	/**
	 * Caches the patients in the entity cache if it is enabled.
	 *
	 * @param patients the patients
	 */
	@Override
	public void cacheResult(List<Patient> patients) {
		for (Patient patient : patients) {
			if (entityCache.getResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
						PatientImpl.class, patient.getPrimaryKey()) == null) {
				cacheResult(patient);
			}
			else {
				patient.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all patients.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(PatientImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the patient.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(Patient patient) {
		entityCache.removeResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
			PatientImpl.class, patient.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<Patient> patients) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (Patient patient : patients) {
			entityCache.removeResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
				PatientImpl.class, patient.getPrimaryKey());
		}
	}

	/**
	 * Creates a new patient with the primary key. Does not add the patient to the database.
	 *
	 * @param patientId the primary key for the new patient
	 * @return the new patient
	 */
	@Override
	public Patient create(long patientId) {
		Patient patient = new PatientImpl();

		patient.setNew(true);
		patient.setPrimaryKey(patientId);

		return patient;
	}

	/**
	 * Removes the patient with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param patientId the primary key of the patient
	 * @return the patient that was removed
	 * @throws NoSuchPatientException if a patient with the primary key could not be found
	 */
	@Override
	public Patient remove(long patientId) throws NoSuchPatientException {
		return remove((Serializable)patientId);
	}

	/**
	 * Removes the patient with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the patient
	 * @return the patient that was removed
	 * @throws NoSuchPatientException if a patient with the primary key could not be found
	 */
	@Override
	public Patient remove(Serializable primaryKey)
		throws NoSuchPatientException {
		Session session = null;

		try {
			session = openSession();

			Patient patient = (Patient)session.get(PatientImpl.class, primaryKey);

			if (patient == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchPatientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(patient);
		}
		catch (NoSuchPatientException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected Patient removeImpl(Patient patient) {
		patient = toUnwrappedModel(patient);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(patient)) {
				patient = (Patient)session.get(PatientImpl.class,
						patient.getPrimaryKeyObj());
			}

			if (patient != null) {
				session.delete(patient);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (patient != null) {
			clearCache(patient);
		}

		return patient;
	}

	@Override
	public Patient updateImpl(Patient patient) {
		patient = toUnwrappedModel(patient);

		boolean isNew = patient.isNew();

		Session session = null;

		try {
			session = openSession();

			if (patient.isNew()) {
				session.save(patient);

				patient.setNew(false);
			}
			else {
				patient = (Patient)session.merge(patient);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
			PatientImpl.class, patient.getPrimaryKey(), patient, false);

		patient.resetOriginalValues();

		return patient;
	}

	protected Patient toUnwrappedModel(Patient patient) {
		if (patient instanceof PatientImpl) {
			return patient;
		}

		PatientImpl patientImpl = new PatientImpl();

		patientImpl.setNew(patient.isNew());
		patientImpl.setPrimaryKey(patient.getPrimaryKey());

		patientImpl.setPatientId(patient.getPatientId());
		patientImpl.setLrUserId(patient.getLrUserId());
		patientImpl.setMspNo(patient.getMspNo());

		return patientImpl;
	}

	/**
	 * Returns the patient with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the patient
	 * @return the patient
	 * @throws NoSuchPatientException if a patient with the primary key could not be found
	 */
	@Override
	public Patient findByPrimaryKey(Serializable primaryKey)
		throws NoSuchPatientException {
		Patient patient = fetchByPrimaryKey(primaryKey);

		if (patient == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchPatientException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return patient;
	}

	/**
	 * Returns the patient with the primary key or throws a {@link NoSuchPatientException} if it could not be found.
	 *
	 * @param patientId the primary key of the patient
	 * @return the patient
	 * @throws NoSuchPatientException if a patient with the primary key could not be found
	 */
	@Override
	public Patient findByPrimaryKey(long patientId)
		throws NoSuchPatientException {
		return findByPrimaryKey((Serializable)patientId);
	}

	/**
	 * Returns the patient with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the patient
	 * @return the patient, or <code>null</code> if a patient with the primary key could not be found
	 */
	@Override
	public Patient fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
				PatientImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		Patient patient = (Patient)serializable;

		if (patient == null) {
			Session session = null;

			try {
				session = openSession();

				patient = (Patient)session.get(PatientImpl.class, primaryKey);

				if (patient != null) {
					cacheResult(patient);
				}
				else {
					entityCache.putResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
						PatientImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
					PatientImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return patient;
	}

	/**
	 * Returns the patient with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param patientId the primary key of the patient
	 * @return the patient, or <code>null</code> if a patient with the primary key could not be found
	 */
	@Override
	public Patient fetchByPrimaryKey(long patientId) {
		return fetchByPrimaryKey((Serializable)patientId);
	}

	@Override
	public Map<Serializable, Patient> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, Patient> map = new HashMap<Serializable, Patient>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			Patient patient = fetchByPrimaryKey(primaryKey);

			if (patient != null) {
				map.put(primaryKey, patient);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
					PatientImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (Patient)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_PATIENT_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((long)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (Patient patient : (List<Patient>)q.list()) {
				map.put(patient.getPrimaryKeyObj(), patient);

				cacheResult(patient);

				uncachedPrimaryKeys.remove(patient.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(PatientModelImpl.ENTITY_CACHE_ENABLED,
					PatientImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the patients.
	 *
	 * @return the patients
	 */
	@Override
	public List<Patient> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the patients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PatientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of patients
	 * @param end the upper bound of the range of patients (not inclusive)
	 * @return the range of patients
	 */
	@Override
	public List<Patient> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the patients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PatientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of patients
	 * @param end the upper bound of the range of patients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of patients
	 */
	@Override
	public List<Patient> findAll(int start, int end,
		OrderByComparator<Patient> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the patients.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link PatientModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of patients
	 * @param end the upper bound of the range of patients (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of patients
	 */
	@Override
	public List<Patient> findAll(int start, int end,
		OrderByComparator<Patient> orderByComparator, boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<Patient> list = null;

		if (retrieveFromCache) {
			list = (List<Patient>)finderCache.getResult(finderPath, finderArgs,
					this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_PATIENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_PATIENT;

				if (pagination) {
					sql = sql.concat(PatientModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<Patient>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<Patient>)QueryUtil.list(q, getDialect(),
							start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the patients from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (Patient patient : findAll()) {
			remove(patient);
		}
	}

	/**
	 * Returns the number of patients.
	 *
	 * @return the number of patients
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_PATIENT);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return PatientModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the patient persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(PatientImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_PATIENT = "SELECT patient FROM Patient patient";
	private static final String _SQL_SELECT_PATIENT_WHERE_PKS_IN = "SELECT patient FROM Patient patient WHERE patientId IN (";
	private static final String _SQL_COUNT_PATIENT = "SELECT COUNT(patient) FROM Patient patient";
	private static final String _ORDER_BY_ENTITY_ALIAS = "patient.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Patient exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(PatientPersistenceImpl.class);
}