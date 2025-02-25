package gatech.cs.buzzcar.service;

import gatech.cs.buzzcar.entity.dto.VendorDto;
import gatech.cs.buzzcar.entity.vo.VendorVo;

import java.util.List;

public interface VendorService {
    int saveVendor(VendorDto vendorDto);

    List<VendorVo> queryVendorByName(String name);
}
