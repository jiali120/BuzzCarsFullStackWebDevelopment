package gatech.cs.buzzcar.controller;

import gatech.cs.buzzcar.common.annotation.HasPermission;
import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.dto.SaleTransactionDto;
import gatech.cs.buzzcar.service.SaleTransactionService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaleTransactionController {

    @Resource
    private SaleTransactionService saleTransactionService;

    @PostMapping(value = "/api/v1/sale-transaction")
    @HasPermission(value = "salespeople,owner")
    public Result saveSaleTransaction(@RequestBody @Validated SaleTransactionDto dto){
        int row = saleTransactionService.saveSaleTransaction(dto);
        return Result.dataByEffectRows(row);
    }



}
