package gatech.cs.buzzcar.service;

import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.dto.PartsDto;
import gatech.cs.buzzcar.entity.dto.PartsStatusDto;
import gatech.cs.buzzcar.entity.vo.PartDetailVo;

import java.math.BigDecimal;
import java.util.List;

public interface PartsOrderService {

    List<PartDetailVo> getPartDetailListByVin(String vin);

    int savePartsOrder(PartsDto dto);

    Result updatePartsStatus(PartsStatusDto dto);

    BigDecimal getPartDetailCostByVin(String vin);
}
