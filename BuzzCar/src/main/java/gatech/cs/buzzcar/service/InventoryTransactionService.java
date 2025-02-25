package gatech.cs.buzzcar.service;

import gatech.cs.buzzcar.entity.pojo.InventoryTransaction;

public interface InventoryTransactionService {

    int updateSaleStatus(String vin, String saleStatus);

    InventoryTransaction getByVin(String vin);

}
