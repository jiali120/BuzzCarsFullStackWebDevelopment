package gatech.cs.buzzcar.controller;


import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.dto.CustomerDto;
import gatech.cs.buzzcar.entity.vo.CustomerVo;
import gatech.cs.buzzcar.service.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Resource
    private CustomerService customerService;

    @PostMapping(value = "/api/v1/customer")
    public Result saveCustomer(@RequestBody @Validated CustomerDto customerDto) {
        int rows = customerService.saveCustomer(customerDto);
        return Result.dataByEffectRows(rows);
    }

    @GetMapping(value = "/api/v1/customer/query")
    public Result queryCustomersByNum(@RequestParam(value = "num", required = false) String num) {
        List<CustomerVo> list = customerService.queryCustomersByNum(num);
        return Result.successData(list);
    }
}
