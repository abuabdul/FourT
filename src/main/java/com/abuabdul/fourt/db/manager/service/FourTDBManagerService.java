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
package com.abuabdul.fourt.db.manager.service;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.config.MethodInvokingBean;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public class FourTDBManagerService implements FourTDBManager {

	private final Class<DatabaseManagerSwing> dbmanagerClass;
	private final String mainMethod;
	private final String[] args;

	public FourTDBManagerService(Class<DatabaseManagerSwing> dbmanagerClass, String mainMethod, String[] args) {
		this.dbmanagerClass = dbmanagerClass;
		this.mainMethod = mainMethod;
		this.args = args;
	}

	@Override
	public void runDBManagerTool() throws FourTServiceException {
		try {
			MethodInvokingBean methodInvokingBean = new MethodInvokingBean();
			methodInvokingBean.setTargetClass(dbmanagerClass);
			methodInvokingBean.setTargetMethod(mainMethod);
			methodInvokingBean.setArguments(args);
			methodInvokingBean.prepare();
			methodInvokingBean.invoke();
		} catch (Exception ex) {
			throw new FourTServiceException(ex.getMessage(), ex);
		}
	}

}
