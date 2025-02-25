package gatech.cs.buzzcar.service;

import gatech.cs.buzzcar.entity.dto.VehicleDto;
import gatech.cs.buzzcar.entity.param.PublicSearchParam;
import gatech.cs.buzzcar.entity.vo.VehicleCustomerVo;
import gatech.cs.buzzcar.entity.vo.VehicleVo;

import java.math.BigDecimal;
import java.util.List;

public interface VehicleService {
    List<VehicleVo> search(PublicSearchParam publicSearchParam);

    int queryVehicleAvailableQuantityForSale();

    int saveVehicle(VehicleDto vehicleDto);

    VehicleVo getVehicleByVin(String vin);

    int updateSalePrice(String vin,
                        BigDecimal updateSalePrice);

    int getVehiclePendingCount();

    VehicleCustomerVo getVehicleCustomerInfo(String vin);
}
