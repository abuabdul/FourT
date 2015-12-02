package com.abuabdul.fourt.db.manager.service;

public abstract class FourTAbstractDBManager<T> implements FourTDBManager<T> {

	protected final Class<T> dbmanagerClass;
	protected final String mainMethod;
	protected final String[] args;

	public FourTAbstractDBManager(Class<T> dbmanagerClass, String mainMethod, String[] args) {
		this.dbmanagerClass = dbmanagerClass;
		this.mainMethod = mainMethod;
		this.args = args;
	}
}
