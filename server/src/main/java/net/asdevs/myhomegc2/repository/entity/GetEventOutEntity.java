package net.asdevs.myhomegc2.repository.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class GetEventOutEntity {
    private Date gcDate;
    private Integer gcId;
}
