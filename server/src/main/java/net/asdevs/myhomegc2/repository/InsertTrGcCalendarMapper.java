package net.asdevs.myhomegc2.repository;

import org.apache.ibatis.annotations.Mapper;

import net.asdevs.myhomegc2.repository.entity.TrGcCalendarInEntity;

@Mapper
public interface InsertTrGcCalendarMapper {
    Integer insertEvent(TrGcCalendarInEntity inEntity);

    Integer updateEvent(TrGcCalendarInEntity inEntity);

    Integer deleteEvent(TrGcCalendarInEntity inEntity);
}
