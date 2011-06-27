/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

package com.liferay.counter.model.impl;

import com.liferay.counter.model.Counter;
import com.liferay.counter.model.CounterModel;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.impl.BaseModelImpl;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

/**
 * The base model implementation for the Counter service. Represents a row in the &quot;Counter&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.counter.model.CounterModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link CounterImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see CounterImpl
 * @see com.liferay.counter.model.Counter
 * @see com.liferay.counter.model.CounterModel
 * @generated
 */
public class CounterModelImpl extends BaseModelImpl<Counter>
	implements CounterModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a counter model instance should use the {@link com.liferay.counter.model.Counter} interface instead.
	 */
	public static final String TABLE_NAME = "Counter";
	public static final Object[][] TABLE_COLUMNS = {
			{ "name", Types.VARCHAR },
			{ "currentId", Types.BIGINT }
		};
	public static final String TABLE_SQL_CREATE = "create table Counter (name VARCHAR(75) not null primary key,currentId LONG)";
	public static final String TABLE_SQL_DROP = "drop table Counter";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.counter.model.Counter"),
			false);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.counter.model.Counter"),
			false);

	public Class<?> getModelClass() {
		return Counter.class;
	}

	public String getModelClassName() {
		return Counter.class.getName();
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.counter.model.Counter"));

	public CounterModelImpl() {
	}

	public String getPrimaryKey() {
		return _name;
	}

	public void setPrimaryKey(String primaryKey) {
		setName(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return _name;
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey((String)primaryKeyObj);
	}

	public String getName() {
		if (_name == null) {
			return StringPool.BLANK;
		}
		else {
			return _name;
		}
	}

	public void setName(String name) {
		_name = name;

		_escapedModelProxy = null;
	}

	public long getCurrentId() {
		return _currentId;
	}

	public void setCurrentId(long currentId) {
		_currentId = currentId;

		_escapedModelProxy = null;
	}

	@Override
	public Counter toEscapedModel() {
		if (isEscapedModel()) {
			return (Counter)this;
		}
		else {
			if (_escapedModelProxy == null) {
				_escapedModelProxy = (Counter)Proxy.newProxyInstance(_classLoader,
						_escapedModelProxyInterfaces,
						new AutoEscapeBeanHandler(this));
			}

			return _escapedModelProxy;
		}
	}

	@Override
	public Object clone() {
		CounterImpl counterImpl = new CounterImpl();

		counterImpl.setName(getName());
		counterImpl.setCurrentId(getCurrentId());

		counterImpl.resetOriginalValues();

		return counterImpl;
	}

	public int compareTo(Counter counter) {
		String primaryKey = counter.getPrimaryKey();

		return getPrimaryKey().compareTo(primaryKey);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Counter counter = null;

		try {
			counter = (Counter)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		String primaryKey = counter.getPrimaryKey();

		if (getPrimaryKey().equals(primaryKey)) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return getPrimaryKey().hashCode();
	}

	@Override
	public void resetOriginalValues() {
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{name=");
		sb.append(getName());
		sb.append(", currentId=");
		sb.append(getCurrentId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(10);

		sb.append("<model><model-name>");
		sb.append("com.liferay.counter.model.Counter");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>name</column-name><column-value><![CDATA[");
		sb.append(getName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentId</column-name><column-value><![CDATA[");
		sb.append(getCurrentId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Counter.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Counter.class
		};
	private String _name;
	private long _currentId;
	private Counter _escapedModelProxy;
}