package enum_arg;

public enum TagEnum {
    //枚举对象实现抽象方法
    SCIENCE{
        public String getTag(){
            return "科学";
        }
    },TECHNOLOGY{
        public String getTag(){
            return "技术";
        }
    };

    public abstract String getTag();
}
