package net.asdevs.myhomegc2.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.RequiredArgsConstructor;
import net.asdevs.myhomegc2.controller.model.GetEventInModel;
import net.asdevs.myhomegc2.controller.model.UpdateEventInModel;
import net.asdevs.myhomegc2.service.GetEventService;
import net.asdevs.myhomegc2.service.UpdateEventService;
import net.asdevs.myhomegc2.service.dto.GetEventInDto;
import net.asdevs.myhomegc2.service.dto.GetEventOutDto;
import net.asdevs.myhomegc2.service.dto.UpdateEventInDto;

@RequiredArgsConstructor
@ResponseBody
@Controller
public class Gc2AppController {

    // コンストラクタインジェクション
    private final GetEventService getEventService;
    private final UpdateEventService updateEventService;

    @GetMapping("/api/test")
    public String test() {
        return "success!";
    }

    @RequestMapping(value = "/api/crypt", method = RequestMethod.POST)
    public String crypt(@RequestBody String body) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(body);
    }

    @RequestMapping(value = "/api/get_event", method = RequestMethod.POST)
    public List<GetEventOutDto> getEvent(@RequestBody GetEventInModel inModel) throws Exception {
        List<GetEventOutDto> outModel = new ArrayList<>();

        GetEventInDto inDto = new GetEventInDto();
        inDto.setDate(inModel.getDate());

        outModel = getEventService.main(inDto);

        return outModel;
    }

    @RequestMapping(value = "/api/update_event", method = RequestMethod.POST)
    public void updateEvent(@RequestBody List<UpdateEventInModel> inModel) throws Exception {

        List<UpdateEventInDto> inDtoList = new ArrayList<>();

        for (UpdateEventInModel event : inModel) {
            UpdateEventInDto inDto = new UpdateEventInDto();
            inDto.setDate(event.getDate());
            inDto.setId(event.getId());
            inDto.setProcFlg(event.getProcFlg());
            inDtoList.add(inDto);
        }

        updateEventService.main(inDtoList);

    }
}
