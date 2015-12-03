package com.abuabdul.fourt.db.manager.service;

import org.hsqldb.util.DatabaseManagerSwing;
import org.springframework.beans.factory.config.MethodInvokingBean;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 */
public class FourTDBManagerService extends FourTAbstractDBManager<DatabaseManagerSwing>
		implements FourTDBManager<DatabaseManagerSwing> {

	public FourTDBManagerService(Class<DatabaseManagerSwing> dbmanagerClass, String mainMethod, String[] args) {
		super(dbmanagerClass, mainMethod, args);
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
