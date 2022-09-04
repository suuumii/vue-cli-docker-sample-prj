package net.asdevs.myhomegc2.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import net.asdevs.myhomegc2.repository.InsertTrGcCalendarMapper;
import net.asdevs.myhomegc2.repository.entity.TrGcCalendarInEntity;
import net.asdevs.myhomegc2.service.dto.UpdateEventInDto;

@Service
@RequiredArgsConstructor
public class UpdateEventService {

    // コンストラクタインジェクション
    private final InsertTrGcCalendarMapper insertTrGcCalendarMapper;

    public static final Integer INSERT_FLG = 1;
    public static final Integer UPDATE_FLG = 2;
    public static final Integer DELETE_FLG = 3;

    @Transactional
    public void main(List<UpdateEventInDto> inDto) throws Exception {
        for (UpdateEventInDto event : inDto) {
            if (INSERT_FLG.equals(event.getProcFlg())) {
                insertService(event);
            } else if (UPDATE_FLG.equals(event.getProcFlg())) {
                updateService(event);
            } else if (DELETE_FLG.equals(event.getProcFlg())) {
                deleteEvent(event);
            } else {
                throw new RuntimeException("処理フラグの値が不正です");
            }
        }
    }

    private void insertService(UpdateEventInDto insertEvent) throws Exception {
        TrGcCalendarInEntity inEntity = new TrGcCalendarInEntity();
        inEntity.setGcDate(insertEvent.getDate());
        inEntity.setGcId(insertEvent.getId());
        inEntity.setDelFlg(0);

        insertTrGcCalendarMapper.insertEvent(inEntity);
    }

    private void updateService(UpdateEventInDto upateEvent) throws Exception {
        TrGcCalendarInEntity inEntity = new TrGcCalendarInEntity();
        inEntity.setGcDate(upateEvent.getDate());
        inEntity.setGcId(upateEvent.getId());

        Integer cnt = insertTrGcCalendarMapper.updateEvent(inEntity);
        if(!cnt.equals(1)){
            throw new RuntimeException("updateの更新件数が1件でありませんでした。: " + cnt);
        }
    }

    private void deleteEvent(UpdateEventInDto deleteEvent) throws Exception {
        TrGcCalendarInEntity inEntity = new TrGcCalendarInEntity();
        inEntity.setGcDate(deleteEvent.getDate());
        inEntity.setGcId(deleteEvent.getId());

        Integer cnt = insertTrGcCalendarMapper.deleteEvent(inEntity);
        if(!cnt.equals(1)){
            throw new RuntimeException("update(論理削除)の更新件数が1件でありませんでした。: " + cnt);
        }
    }
}
