package gatech.cs.buzzcar.service;

import gatech.cs.buzzcar.entity.dto.CustomerDto;
import gatech.cs.buzzcar.entity.vo.CustomerVo;

import java.util.List;

public interface CustomerService {


    int saveCustomer(CustomerDto customerDto);

    List<CustomerVo> queryCustomersByNum(String num);

    CustomerVo getCustomerByCid(String cid);
}
