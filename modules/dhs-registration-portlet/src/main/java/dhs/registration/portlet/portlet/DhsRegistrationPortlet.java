package dhs.registration.portlet.portlet;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;

/**
 * @author ronak.dhruv
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true",
		"javax.portlet.display-name=dhs-registration-portlet Portlet",
		"javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.resource-bundle=content.Language",
		"javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class DhsRegistrationPortlet extends MVCPortlet {
	
	 @Override
	  public void doView(RenderRequest renderRequest, RenderResponse renderResponse) throws IOException, PortletException {
	   renderRequest.setAttribute("questionlist", UserLocalServiceUtil.getUsers(0, -1)); 
	    include(viewTemplate, renderRequest, renderResponse);
	  }
	 
}