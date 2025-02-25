package gatech.cs.buzzcar.controller;

import gatech.cs.buzzcar.common.model.Result;
import gatech.cs.buzzcar.entity.pojo.Color;
import gatech.cs.buzzcar.service.ColorService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ColorController {

    @Resource
    private ColorService colorService;

    @GetMapping(value = {"/api/v1/colors", "/public/colors"})
    public Result queryColors(){
        List<Color> colors = colorService.queryColors();
        return Result.successData(colors);
    }
}
