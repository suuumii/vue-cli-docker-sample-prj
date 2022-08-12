package net.asdevs.myhomegc2.repository;

import org.apache.ibatis.annotations.Mapper;

import net.asdevs.myhomegc2.repository.entity.ScUsersEntity;

@Mapper
public interface ScUsersMapper {
    ScUsersEntity findByName(String name);
}
