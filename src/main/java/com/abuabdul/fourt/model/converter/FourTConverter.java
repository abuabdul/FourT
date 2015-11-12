package com.abuabdul.fourt.model.converter;

import com.abuabdul.fourt.exception.FourTException;

public interface FourTConverter<M, D> {

	public D convert(M model) throws FourTException;

}
