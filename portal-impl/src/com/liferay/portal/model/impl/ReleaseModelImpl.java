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

package com.liferay.portal.model.impl;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.Release;
import com.liferay.portal.model.ReleaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;
import com.liferay.portlet.expando.util.ExpandoBridgeFactoryUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.sql.Types;

import java.util.Date;

/**
 * The base model implementation for the Release service. Represents a row in the &quot;Release_&quot; database table, with each column mapped to a property of this class.
 *
 * <p>
 * This implementation and its corresponding interface {@link com.liferay.portal.model.ReleaseModel} exist only as a container for the default property accessors generated by ServiceBuilder. Helper methods and all application logic should be put in {@link ReleaseImpl}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ReleaseImpl
 * @see com.liferay.portal.model.Release
 * @see com.liferay.portal.model.ReleaseModel
 * @generated
 */
public class ReleaseModelImpl extends BaseModelImpl<Release>
	implements ReleaseModel {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. All methods that expect a release model instance should use the {@link com.liferay.portal.model.Release} interface instead.
	 */
	public static final String TABLE_NAME = "Release_";
	public static final Object[][] TABLE_COLUMNS = {
			{ "releaseId", Types.BIGINT },
			{ "createDate", Types.TIMESTAMP },
			{ "modifiedDate", Types.TIMESTAMP },
			{ "servletContextName", Types.VARCHAR },
			{ "buildNumber", Types.INTEGER },
			{ "buildDate", Types.TIMESTAMP },
			{ "verified", Types.BOOLEAN },
			{ "testString", Types.VARCHAR }
		};
	public static final String TABLE_SQL_CREATE = "create table Release_ (releaseId LONG not null primary key,createDate DATE null,modifiedDate DATE null,servletContextName VARCHAR(75) null,buildNumber INTEGER,buildDate DATE null,verified BOOLEAN,testString VARCHAR(1024) null)";
	public static final String TABLE_SQL_DROP = "drop table Release_";
	public static final String DATA_SOURCE = "liferayDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";
	public static final boolean ENTITY_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.entity.cache.enabled.com.liferay.portal.model.Release"),
			true);
	public static final boolean FINDER_CACHE_ENABLED = GetterUtil.getBoolean(com.liferay.portal.util.PropsUtil.get(
				"value.object.finder.cache.enabled.com.liferay.portal.model.Release"),
			true);

	public Class<?> getModelClass() {
		return Release.class;
	}

	public String getModelClassName() {
		return Release.class.getName();
	}

	public static final long LOCK_EXPIRATION_TIME = GetterUtil.getLong(com.liferay.portal.util.PropsUtil.get(
				"lock.expiration.time.com.liferay.portal.model.Release"));

	public ReleaseModelImpl() {
	}

	public long getPrimaryKey() {
		return _releaseId;
	}

	public void setPrimaryKey(long primaryKey) {
		setReleaseId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_releaseId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	public long getReleaseId() {
		return _releaseId;
	}

	public void setReleaseId(long releaseId) {
		_releaseId = releaseId;

		_escapedModelProxy = null;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		_escapedModelProxy = null;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		_escapedModelProxy = null;
	}

	public String getServletContextName() {
		if (_servletContextName == null) {
			return StringPool.BLANK;
		}
		else {
			return _servletContextName;
		}
	}

	public void setServletContextName(String servletContextName) {
		if (_originalServletContextName == null) {
			_originalServletContextName = _servletContextName;
		}

		_servletContextName = servletContextName;

		_escapedModelProxy = null;
	}

	public String getOriginalServletContextName() {
		return GetterUtil.getString(_originalServletContextName);
	}

	public int getBuildNumber() {
		return _buildNumber;
	}

	public void setBuildNumber(int buildNumber) {
		_buildNumber = buildNumber;

		_escapedModelProxy = null;
	}

	public Date getBuildDate() {
		return _buildDate;
	}

	public void setBuildDate(Date buildDate) {
		_buildDate = buildDate;

		_escapedModelProxy = null;
	}

	public boolean getVerified() {
		return _verified;
	}

	public boolean isVerified() {
		return _verified;
	}

	public void setVerified(boolean verified) {
		_verified = verified;

		_escapedModelProxy = null;
	}

	public String getTestString() {
		if (_testString == null) {
			return StringPool.BLANK;
		}
		else {
			return _testString;
		}
	}

	public void setTestString(String testString) {
		_testString = testString;

		_escapedModelProxy = null;
	}

	@Override
	public Release toEscapedModel() {
		if (isEscapedModel()) {
			return (Release)this;
		}
		else {
			if (_escapedModelProxy == null) {
				_escapedModelProxy = (Release)Proxy.newProxyInstance(_classLoader,
						_escapedModelProxyInterfaces,
						new AutoEscapeBeanHandler(this));
			}

			return _escapedModelProxy;
		}
	}

	@Override
	public ExpandoBridge getExpandoBridge() {
		if (_expandoBridge == null) {
			_expandoBridge = ExpandoBridgeFactoryUtil.getExpandoBridge(0,
					Release.class.getName(), getPrimaryKey());
		}

		return _expandoBridge;
	}

	@Override
	public void setExpandoBridgeAttributes(ServiceContext serviceContext) {
		getExpandoBridge().setAttributes(serviceContext);
	}

	@Override
	public Object clone() {
		ReleaseImpl releaseImpl = new ReleaseImpl();

		releaseImpl.setReleaseId(getReleaseId());
		releaseImpl.setCreateDate(getCreateDate());
		releaseImpl.setModifiedDate(getModifiedDate());
		releaseImpl.setServletContextName(getServletContextName());
		releaseImpl.setBuildNumber(getBuildNumber());
		releaseImpl.setBuildDate(getBuildDate());
		releaseImpl.setVerified(getVerified());
		releaseImpl.setTestString(getTestString());

		releaseImpl.resetOriginalValues();

		return releaseImpl;
	}

	public int compareTo(Release release) {
		long primaryKey = release.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		Release release = null;

		try {
			release = (Release)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = release.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public void resetOriginalValues() {
		ReleaseModelImpl releaseModelImpl = this;

		releaseModelImpl._originalServletContextName = releaseModelImpl._servletContextName;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{releaseId=");
		sb.append(getReleaseId());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", servletContextName=");
		sb.append(getServletContextName());
		sb.append(", buildNumber=");
		sb.append(getBuildNumber());
		sb.append(", buildDate=");
		sb.append(getBuildDate());
		sb.append(", verified=");
		sb.append(getVerified());
		sb.append(", testString=");
		sb.append(getTestString());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(28);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.model.Release");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>releaseId</column-name><column-value><![CDATA[");
		sb.append(getReleaseId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>servletContextName</column-name><column-value><![CDATA[");
		sb.append(getServletContextName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>buildNumber</column-name><column-value><![CDATA[");
		sb.append(getBuildNumber());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>buildDate</column-name><column-value><![CDATA[");
		sb.append(getBuildDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>verified</column-name><column-value><![CDATA[");
		sb.append(getVerified());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>testString</column-name><column-value><![CDATA[");
		sb.append(getTestString());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private static ClassLoader _classLoader = Release.class.getClassLoader();
	private static Class<?>[] _escapedModelProxyInterfaces = new Class[] {
			Release.class
		};
	private long _releaseId;
	private Date _createDate;
	private Date _modifiedDate;
	private String _servletContextName;
	private String _originalServletContextName;
	private int _buildNumber;
	private Date _buildDate;
	private boolean _verified;
	private String _testString;
	private transient ExpandoBridge _expandoBridge;
	private Release _escapedModelProxy;
}