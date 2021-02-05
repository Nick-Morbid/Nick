import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Animal extends setMeal implements petShop{
    protected int number;
    protected int age;
    protected String kind;
    protected int kindnumber;//这个是品种编号，1到3分别表示鸟、猫、狗
//    protected String character;
    protected double price;
    protected String time1;
    protected String state1;
    protected String state2;//表示是否接种疫苗
    protected static ArrayList<String> information = new ArrayList<>();

    public Animal(int number,int age,String kind,double price,String state1,int kindnumber,String state2){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        time1 = sdf.format(date);
        this.number = number;
        this.age = age;
        this.kind = kind;
        this.price = price;
        this.state1 = state1;
        this.kindnumber = kindnumber;
        this.state2 = state2;
        setInformation();
    }
    public int getNumber(){
        return number;
    }
    public String getState1(){
        return state1;
    }
    public int getKindnumber(){
        return kindnumber;
    }
    public int getAge(){
        return age;
    }
    public double getPrice(){
        return price;
    }
    public void reduceNumber(int num){
        number -= num;
    }
    public static void printInformation(){
        for(String x : information){
            System.out.println(x);
        }
    }
    public String getState2(){
        return state2;
    }
    public void setInformation(){
        String infor;
        if(state2.equals("否")){
            if (state1.equals("买入")) {
                infor = "在" + time1 + "您以"+price+"元/只的价格买入了" + number + "只年龄为" + age + "的" + kind;
            }else if(state1.equals("在套餐中")){
                infor = "在" + time1 + "您把" + number + "只年龄为" + age + "的" + kind+"设为套餐";
            }else if (state1.equals("寄养")){
                infor = "在" + time1 + "您以"+price+"元/只的价格接受寄养了" + number + "只年龄为" + age + "的" + kind;
            }else if (state1.equals("已售")){
                infor = "在" + time1 + "您售出了" + number + "只年龄为" + age + "的" + kind+",售价为"+price+"元/只";
            }else if(state1.equals("在套餐中已售")){
                infor = "在" + time1 + "您在套餐中售出了" + number + "只年龄为" + age + "的" + kind+"，共赚得"+price+"元";
            }else {
                infor = "在" + time1 + "您归还了" + number + "只年龄为" + age + "的" + kind;
            }
        }else {
            infor = "在" + time1 + "您给" + number + "只年龄为" + age + "状态为"+state1+"的" + kind+"打了疫苗";
        }
        information.add(infor);
    }
}
