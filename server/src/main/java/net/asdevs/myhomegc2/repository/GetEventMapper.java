package net.asdevs.myhomegc2.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import net.asdevs.myhomegc2.repository.entity.GetEventInEntity;
import net.asdevs.myhomegc2.repository.entity.GetEventOutEntity;
import net.asdevs.myhomegc2.repository.entity.GetEventThisDayInEntity;
import net.asdevs.myhomegc2.repository.entity.GetEventThisDayOutEntity;

@Mapper
public interface GetEventMapper {
    List<GetEventOutEntity> getEvents(GetEventInEntity inEntity);
    GetEventThisDayOutEntity getEventThisDay(GetEventThisDayInEntity inEntity);
}
