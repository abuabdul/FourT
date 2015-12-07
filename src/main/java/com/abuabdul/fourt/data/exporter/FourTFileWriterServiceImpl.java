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
package com.abuabdul.fourt.data.exporter;

import static org.springframework.util.CollectionUtils.isEmpty;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.support.RequestContext;

import com.abuabdul.fourt.exception.FourTServiceException;

/**
 * @author abuabdul
 *
 * @param <U>
 * @param <V>
 */
public class FourTFileWriterServiceImpl<U, V> implements FourTFileWriterService<U> {

	private final FourTFileExporter<U, V> fileExporter;

	public FourTFileWriterServiceImpl(FourTFileExporter<U, V> fileExporter) {
		this.fileExporter = fileExporter;
	}

	@Override
	public void exportDataAsFile(RequestContext context, HttpServletResponse response, U u)
			throws FourTServiceException {
		fileExporter.setExportType(response, context);

		if (fileExporter.isEmptyInput(u)) {
			fileExporter.handleEmptyInput(response, context);
			return;
		}

		List<V> fetchResults = fileExporter.fetchResults(u);
		if (isEmpty(fetchResults)) {
			fileExporter.writeIfNoResults(response, context);
			return;
		}

		fileExporter.writeFetchResults(response, fetchResults);
	}
}
