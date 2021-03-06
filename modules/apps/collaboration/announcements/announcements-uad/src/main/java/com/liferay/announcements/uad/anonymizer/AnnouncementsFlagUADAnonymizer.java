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

package com.liferay.announcements.uad.anonymizer;

import com.liferay.announcements.kernel.model.AnnouncementsFlag;
import com.liferay.announcements.kernel.service.AnnouncementsFlagLocalService;
import com.liferay.announcements.uad.constants.AnnouncementsUADConstants;
import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.user.associated.data.anonymizer.DynamicQueryUADAnonymizer;
import com.liferay.user.associated.data.anonymizer.UADAnonymizer;

import java.util.Collections;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Noah Sherrill
 */
@Component(
	immediate = true,
	property = "model.class.name=" + AnnouncementsUADConstants.CLASS_NAME_ANNOUNCEMENTS_FLAG,
	service = UADAnonymizer.class
)
public class AnnouncementsFlagUADAnonymizer
	extends DynamicQueryUADAnonymizer<AnnouncementsFlag> {

	@Override
	public void autoAnonymize(
			AnnouncementsFlag announcementsFlag, long userId,
			User anonymousUser)
		throws PortalException {

		announcementsFlag.setUserId(anonymousUser.getUserId());

		_announcementsFlagLocalService.updateAnnouncementsFlag(
			announcementsFlag);
	}

	@Override
	public void delete(AnnouncementsFlag announcementsFlag) {
		_announcementsFlagLocalService.deleteFlag(announcementsFlag);
	}

	@Override
	public List<String> getNonanonymizableFieldNames() {
		return Collections.emptyList();
	}

	@Override
	protected ActionableDynamicQuery doGetActionableDynamicQuery() {
		return _announcementsFlagLocalService.getActionableDynamicQuery();
	}

	@Override
	protected String[] doGetUserIdFieldNames() {
		return AnnouncementsUADConstants.USER_ID_FIELD_NAMES_ANNOUNCEMENTS_FLAG;
	}

	@Reference
	private AnnouncementsFlagLocalService _announcementsFlagLocalService;

}