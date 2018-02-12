package com.jony.base;

public class SystemConfigure {

    public enum commonEnum {

        RESULT_SUCESS("sucess","成功"),

        RESULT_FAIL("fail","失败");

        private String value;

        private String describe;

        commonEnum(String value, String describe) {
            this.value = value;
            this.describe = describe;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
    }

    public enum errorEnum {

        TOKEN_EXPIRE("0008","token不存在或已失效");

        private String value;

        private String describe;

        errorEnum(String value, String describe) {
            this.value = value;
            this.describe = describe;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getDescribe() {
            return describe;
        }

        public void setDescribe(String describe) {
            this.describe = describe;
        }
        }

}
