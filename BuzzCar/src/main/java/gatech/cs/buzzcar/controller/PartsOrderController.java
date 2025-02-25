package gatech.cs.buzzcar.controller;


import gatech.cs.buzzcar.common.annotation.HasPermission;
import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.dto.PartsDto;
import gatech.cs.buzzcar.entity.dto.PartsStatusDto;
import gatech.cs.buzzcar.service.PartsOrderService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PartsOrderController {

    @Resource
    private PartsOrderService partsOrderService;

    @PostMapping(value = "/api/v1/parts")
    @HasPermission(value = "InventoryClerks,owner")
    public Result savePartsOrder(@RequestBody @Validated PartsDto dto){
        int rows = partsOrderService.savePartsOrder(dto);
        return Result.dataByEffectRows(rows);
    }

    @PostMapping(value = "/api/v1/parts/status")
    @HasPermission(value = "InventoryClerks,owner")
    public Result updatePartsStatus(@RequestBody @Validated PartsStatusDto dto){
        return partsOrderService.updatePartsStatus(dto);
    }
}
