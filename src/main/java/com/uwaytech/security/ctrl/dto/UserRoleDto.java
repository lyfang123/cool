package com.uwaytech.security.ctrl.dto;

import com.uwaytech.security.domain.RoleInfo;
import com.uwaytech.security.domain.UserRole;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lyf on 2016/6/30.
 */
public class UserRoleDto {
    private Long accountId;

    private String name;

    private String phone;

    private String photo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public static Object userRoleList(List<UserRole> list) {
        List<UserRoleDto> dtoList = new ArrayList<UserRoleDto>();
        if(!list.isEmpty()){
            for (UserRole userRole:list){
               UserRoleDto dto = new UserRoleDto();
                dto.setAccountId(userRole.getUserId());
                dto.setPhone(userRole.getPhone());
                dto.setName(userRole.getName());
                dtoList.add(dto);
            }
        }
        return dtoList;
    }
}
