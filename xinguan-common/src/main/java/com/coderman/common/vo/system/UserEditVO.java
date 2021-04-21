package com.coderman.common.vo.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wuwenze.poi.annotation.ExcelField;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @Author zhangyukang
 * @Date 2020/3/9 11:06
 * @Version 1.0
 **/
@Data
public class UserEditVO {
    private Long id;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    @Email(message = "请输入正确的邮箱格式")
    private String email;

    @NotBlank(message = "电话号码不能为空")
    private String phoneNumber;

    @NotNull(message = "性别不能为空")
    private Integer sex;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy年MM月dd日")
    @NotNull(message = "生日不能为空")
    private Date birth;

//    @NotNull(message = "部门不能为空")
    private Long departmentId;

    @NotNull(message = "角色不能为空")
    private Long roleId;



    private String province; //地址 省
    private String provinceValue;//地址 省 code


    private String city; //地址 市
    private String cityValue; //地址 市 code

    private String origin; //地址 区
    private String originValue; //地址 区 code

    private String address; //详细地址

}
