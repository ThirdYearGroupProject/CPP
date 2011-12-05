package ic.doc.cpp.client;

import com.google.gwt.core.client.EntryPoint;
import ic.doc.cpp.client.gin.ClientGinjector;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtplatform.mvp.client.DelayedBindRegistry;

public class CPP implements EntryPoint {

	private final ClientGinjector ginjector = GWT.create(ClientGinjector.class);

	@Override
	public void onModuleLoad() {
		// This is required for Gwt-Platform proxy's generator
		DelayedBindRegistry.bind(ginjector);
	
		ginjector.getPlaceManager().revealCurrentPlace();
		
		RootPanel.get("loading") .setVisible(false);
	}
}
