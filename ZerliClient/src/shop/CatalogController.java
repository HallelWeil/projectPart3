package shop;

import java.util.ArrayList;
import catalog.*;
import client.ClientController;
import client.MsgController;
import msg.MsgType;

public class CatalogController {
	private ClientController clientController = ClientController.getInstance();
	/**
	 * change the category, get the category page(default should be 1)
	 * 
	 * @param category
	 */
	public ArrayList<Product> chooseCategory(String category) {
		MsgController msgController = clientController.sendMsg(MsgController.createGET_CATALOG_PAGEMsg(0,category));
		if(msgController.getType()==MsgType.RETURN_CATALOG_PAGE)
			return msgController.getCatalogPage();
		return null;

	}
	/**
	 * update the catalog send to server and return true when the product is updated
	 * @param product
	 * @return true- when the product is updated , false- when can't update a product
	 */

	public boolean updateCatalog(Product product) {
		
		MsgController msgController = clientController.sendMsg(MsgController.createUPDATE_CATALOGMsg(product));
		if(msgController.getType()==MsgType.COMPLETED)
			return true;
		return false;

	}
}
