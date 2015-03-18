package com.example.switchyard.foo;

import javax.inject.Inject;

import org.switchyard.component.bean.Reference;
import org.switchyard.component.bean.Service;

import com.nielsen.engineering.mediaworks.panelcollectionsquery.CTGPanelSearch;
import com.nielsen.engineering.mediaworks.panelcollectionsquery.model.CTGPanelContext;

@Service(Panel.class)
public class PanelBean implements Panel {
	
	@Reference
	@Inject
	private CTGPanelSearch ctgPanelService;
	
	public String invoke( String service )
	{
		ctgPanelService.getPanel(new CTGPanelContext().toString());
		return "done";
	}

}
