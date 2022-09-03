package net.asdevs.myhomegc2.repository.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class TrGcCalendarInEntity extends CommonColumnEntity{
    private Integer id;
    private String gcDate;
    private Integer gcId;
    private Integer delFlg;
}
