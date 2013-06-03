package org.openmrs.module.webservices.rest.resource;

import org.openmrs.annotation.Handler;
import org.openmrs.module.openhmis.commons.api.entity.IObjectDataService;
import org.openmrs.module.openhmis.inventory.api.model.StockRoomItem;
import org.openmrs.module.openhmis.inventory.web.ModuleRestConstants;
import org.openmrs.module.webservices.rest.web.annotation.Resource;
import org.openmrs.module.webservices.rest.web.representation.Representation;
import org.openmrs.module.webservices.rest.web.resource.impl.DelegatingResourceDescription;

@Resource(name = ModuleRestConstants.STOCK_ROOM_ITEM_RESOURCE, supportedClass=StockRoomItem.class, supportedOpenmrsVersions={"1.9"})
@Handler(supports = { StockRoomItem.class }, order = 0)
public class StockRoomItemResource extends BaseRestObjectResource<StockRoomItem> {
	@Override
	public StockRoomItem newDelegate() {
		return new StockRoomItem();
	}

	@Override
	public Class<? extends IObjectDataService<StockRoomItem>> getServiceClass() {
		return null;
	}

	@Override
	protected DelegatingResourceDescription getDefaultRepresentationDescription() {
		DelegatingResourceDescription description = super.getDefaultRepresentationDescription();

		description.addProperty("stockRoom", Representation.DEFAULT);
		description.addProperty("importTransaction", Representation.DEFAULT);
		description.addProperty("item", Representation.DEFAULT);
		description.addProperty("quantity", Representation.DEFAULT);
		description.addProperty("expiration", Representation.DEFAULT);

		return description;
	}
}
