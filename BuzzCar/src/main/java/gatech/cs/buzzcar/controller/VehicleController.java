package gatech.cs.buzzcar.controller;

import gatech.cs.buzzcar.common.annotation.HasPermission;
import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.dto.VehicleDto;
import gatech.cs.buzzcar.entity.vo.PartDetailVo;
import gatech.cs.buzzcar.entity.vo.VehicleCustomerVo;
import gatech.cs.buzzcar.entity.vo.VehicleVo;
import gatech.cs.buzzcar.service.PartsOrderService;
import gatech.cs.buzzcar.service.VehicleService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class VehicleController {

    @Resource
    private VehicleService vehicleService;

    @Resource
    private PartsOrderService partsOrderService;

    @PostMapping(value = "/api/v1/vehicle")
    @HasPermission(value = "InventoryClerks,owner")
    public Result saveVehicle(@RequestBody @Validated VehicleDto vehicleDto){
        int rows = vehicleService.saveVehicle(vehicleDto);
        if(rows == -1){
            return Result.fail("vin already exists");
        }else if(rows == 0){
            return Result.fail("Add failure");
        }else{
            return Result.success("New success");
        }
    }

    @GetMapping(value = {"/api/v1/vehicle", "/public/vehicle"})
    @HasPermission(value = "InventoryClerks,owner")
    public Result getVehicleByVin(@RequestParam("vin") String vin){
        VehicleVo vo = vehicleService.getVehicleByVin(vin);
        return Result.successData(vo);
    }

    @GetMapping(value = "/api/v1/vehicle/parts")
    @HasPermission(value = "InventoryClerks,owner")
    public Result getPartDetailListByVin(@RequestParam(value = "vin") String vin){
        List<PartDetailVo> list = partsOrderService.getPartDetailListByVin(vin);
        return Result.successData(list);
    }


    @GetMapping(value = {"/api/v1/vehicle/pending", "/public/v1/vehicle/pending"})
    @HasPermission(value = "InventoryClerks,owner")
    public Result getVehiclePendingCount(){
        int quantity = vehicleService.getVehiclePendingCount();
        return Result.successData(quantity);
    }

    @GetMapping(value = "/api/v1/vehicle/parts/cost")
    @HasPermission(value = "manager,owner")
    public Result getPartDetailCostByVin(@RequestParam(value = "vin") String vin){
        BigDecimal cost = partsOrderService.getPartDetailCostByVin(vin);
        return Result.successData(cost);
    }

    @GetMapping(value = "/api/v1/vehicle/customers/info")
    @HasPermission(value = "manager,owner")
    public Result getVehicleCustomerInfo(@RequestParam(value = "vin") String vin){
        VehicleCustomerVo vo = vehicleService.getVehicleCustomerInfo(vin);
        return Result.successData(vo);
    }

}
