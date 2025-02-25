package gatech.cs.buzzcar.controller;

import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.param.PublicSearchParam;
import gatech.cs.buzzcar.entity.vo.VehicleVo;
import gatech.cs.buzzcar.enums.UserRoleEnum;
import gatech.cs.buzzcar.service.VehicleService;
import gatech.cs.buzzcar.utils.UserUtils;
import io.swagger.annotations.Api;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
@Api(tags = "Public APIs")
@AllArgsConstructor
public class PublicSearchController {


    @Resource
    private VehicleService vehicleService;

    @PostMapping(value = "/search")
    public Result search(@RequestBody PublicSearchParam publicSearchParam){

        String userRole = UserUtils.getUserRole();
        if(StringUtils.equalsIgnoreCase(userRole, UserRoleEnum.Manager.value())){
            String saleStatus = publicSearchParam.getSaleStatus();
            if(StringUtils.equalsIgnoreCase("sold", saleStatus)){
                //unsold  saleable/forbidden/sold
                publicSearchParam.setSaleStatus("sold");
            }else if(StringUtils.equalsIgnoreCase("unsold", saleStatus)){
                publicSearchParam.setSaleStatus("saleable,forbidden");
            }
        }

//        else if(StringUtils.equalsIgnoreCase(userRole, UserRoleEnum.InventoryClerks.value())){
//            publicSearchParam.setSaleStatus("saleable,forbidden");
//        }
        List<VehicleVo> vehicleVoList = vehicleService.search(publicSearchParam);
        return Result.successData(vehicleVoList);
    }

    @GetMapping(value = "/available-quantity-for-sale")
    public Result queryVehicleAvailableQuantityForSale(){
        int quantity = vehicleService.queryVehicleAvailableQuantityForSale();
        return Result.successData(quantity);
    }


}
