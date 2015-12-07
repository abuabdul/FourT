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
package com.abuabdul.fourt.exception;

/**
 * An Application level exception, captures exception message and confine it as
 * RuntimeException so application can handle the exception message delicately
 * using exception resolvers
 * 
 * @author abuabdul
 *
 */
public class FourTException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 23434324L;

	public FourTException() {

	}

	public FourTException(String message) {
		super(message);
	}

	public FourTException(Throwable cause) {
		super(cause);
	}

	public FourTException(String message, Throwable cause) {
		super(message, cause);
	}

	public FourTException(String message, Error error) {
		super(message, error);
	}

}
