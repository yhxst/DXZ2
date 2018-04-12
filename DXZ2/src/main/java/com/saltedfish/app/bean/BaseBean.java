package com.saltedfish.app.bean;

import com.saltedfish.app.mapper.BaseMapper;

import java.sql.Timestamp;
import java.util.Random;

public class BaseBean {
    private int id;
    private Timestamp createAt;
    private Timestamp updateAt;
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Timestamp createAt) {
        this.createAt = createAt;
    }

    public Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Timestamp updateAt) {
        this.updateAt = updateAt;
    }

    public void autoCreateAt(){
        createAt = new Timestamp(System.currentTimeMillis());

    }
    public void autoUpdateAt(){
        updateAt = new Timestamp(System.currentTimeMillis());
    }

    public BaseBean(){
    }

    public BaseBean(BaseMapper baseMapper){
        autoCode(baseMapper);
        autoCreateAt();
        autoUpdateAt();
    }

    public void init(BaseMapper baseMapper){
        autoCode(baseMapper);
        autoCreateAt();
        autoUpdateAt();
    }

    public static String getRandomString(int length) { //length表示生成字符串的长度
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    public void autoCode(BaseMapper baseMapper){
        code = BaseBean.getRandomString(20);
        boolean flag = true;
        while (flag){
            if (baseMapper.getBean(code) == null){
                flag = false;
            }else {
                code = BaseBean.getRandomString(20);
            }
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
