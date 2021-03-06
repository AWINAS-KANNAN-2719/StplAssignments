package com.lms.awinas.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.lms.LmsUrlConstants;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsModel;
import com.stpl.gtn.gtn2o.ws.lms.StudentLmsRequest;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.vaadin.ui.Notification;

public class GtnFrameworkLendBookAction implements  GtnUIFrameWorkAction , GtnUIFrameworkDynamicClass{
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkLendBookAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// SS
		
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {


		gtnLogger.info("issue student action");
		
		StudentLmsModel slm =new StudentLmsModel();
		
		
		slm.setBookid(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("lendbookid").getIntegerFromField());
		slm.setStuid(GtnUIFrameworkGlobalUI.getVaadinBaseComponent("lendbookstuid").getIntegerFromField());
		
		
		
		StudentLmsRequest StudentLmsRequest =new StudentLmsRequest();
		StudentLmsRequest.setStudentLmsModel(slm);
		
		GtnUIFrameworkWebserviceRequest request=new GtnUIFrameworkWebserviceRequest();
		request.setStudentLmsRequest(StudentLmsRequest);
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnlmsWebServiceUrl(
				LmsUrlConstants.GTN_WS_LMS_BOOKISSUELMS_SERVICE, request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());


		
		
			Notification.show(response.getBookLmsResponse().getBookResult());
	
		
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
