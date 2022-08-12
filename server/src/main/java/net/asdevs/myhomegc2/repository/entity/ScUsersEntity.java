package net.asdevs.myhomegc2.repository.entity;

import java.sql.Date;

import lombok.Data;

@Data
public class ScUsersEntity {
    private Integer Id;
    private String name;
    private String password;
    private Integer delFlg;
    private Date created;
    private String createdUser;
    private Date modified;
    private String modifiedUser;
}
