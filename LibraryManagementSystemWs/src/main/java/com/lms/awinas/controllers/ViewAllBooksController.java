package com.lms.awinas.controllers;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
public class ViewAllBooksController {

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(ViewAllBooksController.class);

	@RequestMapping(value = LmsUrlConstants.GTN_WS_LMS_VIEWBOOK_SERVICE, method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse viewbkstu(@RequestBody GtnUIFrameworkWebserviceRequest request)
	{
	logger.info("VIEW BOOK called");
	Lmsdao dao=new Lmsdao();
	GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
	
	response =dao.viewallbooks();
	return response;
	
	}
}
