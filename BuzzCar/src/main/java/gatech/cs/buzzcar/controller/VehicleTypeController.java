package gatech.cs.buzzcar.controller;

import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.pojo.VehicleType;
import gatech.cs.buzzcar.service.VehicleTypeService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VehicleTypeController {

    @Resource
    private VehicleTypeService vehicleTypeService;

    @GetMapping(value = {"/api/v1/vehicle/types", "/public/vehicle/types"})
    public Result queryVehicleTypes(){
        List<VehicleType> list = vehicleTypeService.queryVehicleTypes();
        return Result.successData(list);
    }
}
