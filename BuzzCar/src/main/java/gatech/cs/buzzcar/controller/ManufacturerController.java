package gatech.cs.buzzcar.controller;

import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.service.ManufacturerService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManufacturerController {


    @Resource
    public ManufacturerService manufacturerService;

    @GetMapping(value = "/public/manufacturers")
    public Result queryManufacturerList(){
        return Result.successData(manufacturerService.queryList());
    }
}
