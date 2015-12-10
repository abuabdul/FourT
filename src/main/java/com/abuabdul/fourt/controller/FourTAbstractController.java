/*
 * Copyright 2013-2016 abuabdul.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.abuabdul.fourt.controller;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;

import com.abuabdul.fourt.domain.RefDetail;
import com.abuabdul.fourt.exception.FourTServiceException;
import com.abuabdul.fourt.service.FourTVetoService;
import com.google.common.collect.Lists;

/**
 * @author abuabdul
 *
 */
public abstract class FourTAbstractController {

	// Define a static logger variable so that it references the
	// Logger instance named "FourTAbstractController".
	private static final Logger log = LogManager.getLogger(FourTAbstractController.class.getName());

	@Autowired
	protected FourTVetoService fourTVetoService;

	public void bootstrapRefData(HttpSession session, ModelMap model) throws FourTServiceException {
		log.debug("Entering bootstrapRefData() in the FourTAbstractController");
		if (session.getAttribute("resourceNameList") == null) {
			List<String> resourceNameList = Lists.newArrayList();
			List<RefDetail> refDetailList = fourTVetoService.findAllRefDetails(true);
			if (!isEmpty(refDetailList)) {
				for (RefDetail refDetail : refDetailList) {
					resourceNameList.add(refDetail.getRefValue());
				}
			}
			session.setAttribute("resourceNameList", resourceNameList);
		}
		model.addAttribute("resourceNameList", session.getAttribute("resourceNameList"));
	}
}
