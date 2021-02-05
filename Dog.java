public class Dog extends Animal{
    public Dog(int number,int age,String kind,double price,String state1,int kindnumber,String state2){
        super(number,age,kind,price,state1,kindnumber,state2);
    }
    @Override
    public String toString(){
        if(state1.equals("买入")||state1.equals("在套餐中"))
            return "有"+state1+"的"+number+"条年龄为"+age+"岁、买入价格为"+price+"元/条的"+kind+" 是否打疫苗："+state2;
        return "有"+state1+"的"+number+"条年龄为"+age+"岁、寄养价格为"+price+"元/条的"+kind+" 是否打疫苗："+state2;
    }
}
