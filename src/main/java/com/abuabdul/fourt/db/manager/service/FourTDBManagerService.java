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
