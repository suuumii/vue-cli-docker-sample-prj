package net.asdevs.myhomegc2.service;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.asdevs.myhomegc2.repository.GetEventMapper;
import net.asdevs.myhomegc2.repository.entity.GetEventInEntity;
import net.asdevs.myhomegc2.repository.entity.GetEventOutEntity;
import net.asdevs.myhomegc2.service.dto.GetEventInDto;
import net.asdevs.myhomegc2.service.dto.GetEventOutDto;

@Service
@Transactional
@RequiredArgsConstructor
public class GetEventService {

    // コンストラクタインジェクション
    private final GetEventMapper getEventMapper;

    public List<GetEventOutDto> main(GetEventInDto inDto) throws Exception {
        List<GetEventOutDto> outDtoList = new ArrayList<>();

        GetEventInEntity inEntity = new GetEventInEntity();

        // バリデーションチェック
        if (!inDto.getDate().matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
            throw new RuntimeException("dateのフォーマット(yyyy-MM-dd)が異なる");
        }

        LocalDate date = LocalDate.parse(inDto.getDate());
        // yyyy-MM-を抜き出し
        String str = inDto.getDate().substring(0, 8);
        // 取得する範囲を指定
        inEntity.setStartDate(str + date.range(ChronoField.DAY_OF_MONTH ).getMinimum());
        inEntity.setEndDate(str + date.range(ChronoField.DAY_OF_MONTH ).getMaximum());

        // クエリ実行
        List<GetEventOutEntity> outEntityList = getEventMapper.getEvents(inEntity);

        for (GetEventOutEntity outEntity : outEntityList) {
            GetEventOutDto outDto = new GetEventOutDto();
            outDto.setId(outEntity.getGcId());
            outDto.setStart(outEntity.getGcDate().toString());
            outDtoList.add(outDto);
        }

        return outDtoList;
    }
}
