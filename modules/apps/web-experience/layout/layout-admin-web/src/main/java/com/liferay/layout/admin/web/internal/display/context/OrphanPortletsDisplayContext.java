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

package com.liferay.layout.admin.web.internal.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItem;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.NavigationItemList;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.model.Layout;
import com.liferay.portal.kernel.model.LayoutConstants;
import com.liferay.portal.kernel.model.LayoutTypePortlet;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.PortletPreferences;
import com.liferay.portal.kernel.portlet.LiferayPortletRequest;
import com.liferay.portal.kernel.portlet.LiferayPortletResponse;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletLocalServiceUtil;
import com.liferay.portal.kernel.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.PortletKeys;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.util.comparator.PortletTitleComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Eudaldo Alonso
 */
public class OrphanPortletsDisplayContext {

	public OrphanPortletsDisplayContext(
		LiferayPortletRequest liferayPortletRequest,
		LiferayPortletResponse liferayPortletResponse) {

		_liferayPortletRequest = liferayPortletRequest;
		_liferayPortletResponse = liferayPortletResponse;
	}

	public String getDisplayStyle() {
		if (Validator.isNotNull(_displayStyle)) {
			return _displayStyle;
		}

		_displayStyle = ParamUtil.getString(
			_liferayPortletRequest, "displayStyle", "list");

		return _displayStyle;
	}

	public List<NavigationItem> getNavigationItems() {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			_liferayPortletRequest);

		return new NavigationItemList() {
			{
				add(
					navigationItem -> {
						navigationItem.setActive(true);
						navigationItem.setHref(
							_liferayPortletResponse.createRenderURL());
						navigationItem.setLabel(
							LanguageUtil.get(request, "orphan-portlets"));
					});
			}
		};
	}

	public String getOrderByCol() {
		if (Validator.isNotNull(_orderByCol)) {
			return _orderByCol;
		}

		_orderByCol = ParamUtil.getString(
			_liferayPortletRequest, "orderByCol", "modified-date");

		return _orderByCol;
	}

	public String getOrderByType() {
		if (Validator.isNotNull(_orderByType)) {
			return _orderByType;
		}

		_orderByType = ParamUtil.getString(
			_liferayPortletRequest, "orderByType", "asc");

		return _orderByType;
	}

	public List<Portlet> getOrphanPortlets() {
		Layout selLayout = getSelLayout();

		return getOrphanPortlets(selLayout);
	}

	public List<Portlet> getOrphanPortlets(Layout layout) {
		if (!layout.isSupportsEmbeddedPortlets()) {
			return Collections.emptyList();
		}

		ThemeDisplay themeDisplay =
			(ThemeDisplay)_liferayPortletRequest.getAttribute(
				WebKeys.THEME_DISPLAY);

		LayoutTypePortlet selLayoutTypePortlet =
			(LayoutTypePortlet)layout.getLayoutType();

		List<Portlet> explicitlyAddedPortlets =
			selLayoutTypePortlet.getExplicitlyAddedPortlets();

		List<String> explicitlyAddedPortletIds = new ArrayList<>();

		for (Portlet explicitlyAddedPortlet : explicitlyAddedPortlets) {
			explicitlyAddedPortletIds.add(
				explicitlyAddedPortlet.getPortletId());
		}

		List<Portlet> orphanPortlets = new ArrayList<>();

		List<PortletPreferences> portletPreferences =
			PortletPreferencesLocalServiceUtil.getPortletPreferences(
				PortletKeys.PREFS_OWNER_ID_DEFAULT,
				PortletKeys.PREFS_OWNER_TYPE_LAYOUT, getSelPlid());

		for (PortletPreferences portletPreference : portletPreferences) {
			String portletId = portletPreference.getPortletId();

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				themeDisplay.getCompanyId(), portletId);

			if (portlet.isSystem()) {
				continue;
			}

			if (explicitlyAddedPortletIds.contains(portletId)) {
				continue;
			}

			orphanPortlets.add(portlet);
		}

		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			_liferayPortletRequest);

		PortletTitleComparator portletTitleComparator =
			new PortletTitleComparator(
				request.getServletContext(), themeDisplay.getLocale());

		orphanPortlets = ListUtil.sort(orphanPortlets, portletTitleComparator);

		return orphanPortlets;
	}

	public Layout getSelLayout() {
		if (_selLayout != null) {
			return _selLayout;
		}

		if (getSelPlid() != LayoutConstants.DEFAULT_PLID) {
			_selLayout = LayoutLocalServiceUtil.fetchLayout(getSelPlid());
		}

		return _selLayout;
	}

	public Long getSelPlid() {
		if (_selPlid != null) {
			return _selPlid;
		}

		_selPlid = ParamUtil.getLong(
			_liferayPortletRequest, "selPlid", LayoutConstants.DEFAULT_PLID);

		return _selPlid;
	}

	public String getStatus(Portlet portlet) {
		HttpServletRequest request = PortalUtil.getHttpServletRequest(
			_liferayPortletRequest);

		if (!portlet.isActive()) {
			return LanguageUtil.get(request, "inactive");
		}
		else if (!portlet.isReady()) {
			return LanguageUtil.format(request, "is-not-ready", "portlet");
		}
		else if (portlet.isUndeployedPortlet()) {
			return LanguageUtil.get(request, "undeployed");
		}

		return LanguageUtil.get(request, "active");
	}

	private String _displayStyle;
	private final LiferayPortletRequest _liferayPortletRequest;
	private final LiferayPortletResponse _liferayPortletResponse;
	private String _orderByCol;
	private String _orderByType;
	private Layout _selLayout;
	private Long _selPlid;

}