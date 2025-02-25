package gatech.cs.buzzcar.controller;


import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.dto.VendorDto;
import gatech.cs.buzzcar.entity.vo.VendorVo;
import gatech.cs.buzzcar.service.VendorService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VendorController {

    @Resource
    private VendorService vendorService;

    @PostMapping(value = "/api/v1/vendor")
    public Result saveVendor(@RequestBody @Validated VendorDto vendorDto){
        int rows = vendorService.saveVendor(vendorDto);
        if(rows == -1){
            return Result.fail("vendor already exists");
        }else if(rows == 0){
            return Result.fail("Add failure");
        }else{
            return Result.success("New success");
        }
    }

    @GetMapping(value = "/api/v1/vendor/query")
    public Result queryVendorByName(@RequestParam(value = "name", required = false) String name){
        List<VendorVo> list = vendorService.queryVendorByName(name);
        return Result.successData(list);
    }

}
