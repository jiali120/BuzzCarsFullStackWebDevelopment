package gatech.cs.buzzcar.service;

import gatech.cs.buzzcar.entity.dto.SaleTransactionDto;
import gatech.cs.buzzcar.entity.pojo.SaleTransaction;

public interface SaleTransactionService {
    int saveSaleTransaction(SaleTransactionDto dto);

    SaleTransaction getByVin(String vin);
}
