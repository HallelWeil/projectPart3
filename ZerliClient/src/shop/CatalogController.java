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
		MsgController msgController = clientController.sendMsg(MsgController.createGET_CATALOG_PAGEMsg(0, category));
		if (msgController.getType() == MsgType.RETURN_CATALOG_PAGE)
			return msgController.getCatalogPage();
		return null;

	}

	/**
	 * update the catalog send to server and return true when the product is updated
	 * 
	 * @param product
	 * @throws Exception - if failed -throw exception with the error msg
	 */

	public void updateCatalog(Product product) throws Exception {

		MsgController msgController = clientController.sendMsg(MsgController.createUPDATE_CATALOGMsg(product));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * save new product to the catalog
	 * 
	 * @param product
	 * @throws Exception - if failed -throw exception with the error msg
	 */
	public void saveNewProduct(Product product) throws Exception {
		MsgController msgController = clientController.sendMsg(MsgController.createADD_TO_CATALOGMsg(product));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}

	/**
	 * delete product from the catalog
	 * 
	 * @param productNumber
	 * @throws Exception - if failed -throw exception with the error msg
	 */
	public void removeFromCatalog(int productNumber) throws Exception {
		MsgController msgController = clientController
				.sendMsg(MsgController.createREMOVE_FROM_CATALOGMsg(productNumber));
		if (msgController.getType() == MsgType.ERROR)
			throw new Exception(msgController.getErrorMsg());
	}
}
