package com.abuabdul.fourt.model.converter;

import java.util.List;

import com.abuabdul.fourt.exception.FourTException;

public interface FourTConverter<RT, R, TD, RTDL> {

	public R convert(RT model) throws FourTException;

	public List<RTDL> convert(List<TD> results) throws FourTException;

}
