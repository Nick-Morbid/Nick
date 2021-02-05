import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Main {
    public static void main(String[] args) {
        double money = 3000;
        int mealnumber = 1;
        Scanner in = new Scanner(System.in);
        System.out.println("Dear店长!欢迎回到西二宠物店视察！");
        System.out.println("请下达您要进行的操作：");
        System.out.println("[1]查看宠物 [2]查看疫苗 [3]设置套餐 [4]出售套餐 [5]批量进货 [6]接受寄养\n" +
                "[7]结束寄养 [8]宠物进货 [9]宠物出售 [10]打疫苗 [11]打印近日交易信息 [12]资金结算\n" +
                "[13]冲钱 [14]查看套餐 [15]退出");
        Map<String ,Animal> animalArrayMap1 = new HashMap<>();//不在套餐中的宠物
        Map<String ,Animal> animalArrayMap2 = new HashMap<>();//在套餐中的宠物
        Map<String ,Animal> animalArrayMap3 = new HashMap<>();//在寄养中的宠物
        Map<String ,Animal> animalArrayMap4 = new HashMap<>();//归还的宠物
        Map<String ,Animal> animalArrayMap5 = new HashMap<>();//卖出的宠物
        Map<String,setMeal>setMealMap = new HashMap<>();
        int t = in.nextInt();
        while (t != 15){
            switch (t){
                case 1-> {
                    if (animalArrayMap1.size() == 0 && animalArrayMap2.size() == 0 &&animalArrayMap3.size() == 0 && animalArrayMap4.size() == 0) {
                        System.out.println("还未购置与接收寄养宠物。");
                    } else {
                        petShop.printfAnimalinformation(animalArrayMap1, animalArrayMap2, animalArrayMap3, animalArrayMap4);
                    }
                }
                case 2-> Vaccine.getVaccine();
                case 3->{
                    try {
                        System.out.println("请您输入创建的套餐种数(输入为非正数直接退出)");
                        int num10 = in.nextInt();
                        while (num10-->0){
                            String mealname = "套餐"+mealnumber;
                            System.out.println("套餐"+mealnumber);
                            System.out.print("请输入套餐数量：");
                            int num18 = in.nextInt();
                            System.out.print("请输入宠物品种：");
                            String kind = in.next();
                            if(animalArrayMap1.containsKey(kind)){
                                System.out.println(animalArrayMap1.get(kind).toString());
                                System.out.print("请输入套餐中所含的宠物数：");
                                int num11 = in.nextInt();
                                if (num11*num18 > animalArrayMap1.get(kind).getNumber()){
                                    System.out.println("宠物数量输入有误！");
                                    break;
                                }
                                System.out.println("请选择套餐中的疫苗");
                                System.out.println("[1]禽类疫苗 [2]猫用疫苗 [3]狗用疫苗");
                                int num12 = in.nextInt();
                                if(num12 != 1 && num12 !=2 && num12 !=3){
                                    System.out.println("请正确选择疫苗！");
                                    break;
                                }
                                System.out.println("该疫苗的数量(显示可用)为："+Vaccine.getVaccinenumber(num12));
                                System.out.print("请输入单个套餐中疫苗数量：");
                                int num13 = in.nextInt();
                                if(num13*num18 > Vaccine.getVaccinenumber(num12)){
                                    System.out.println("疫苗输入数量有误！");
                                    break;
                                }
                                System.out.print("请输入套餐售价：");
                                int num14 = in.nextInt();
                                if (num14 <= 0){
                                    System.out.println("请输入合理的价格！");
                                    break;
                                }
                                double price = animalArrayMap1.get(kind).getPrice();
                                petShop.setAnimal(num11*num18,animalArrayMap1,animalArrayMap2,kind,"在套餐中",price);
                                setMealMap.put(mealname,new setMeal(num12,kind,num13,num11,num14,mealname,num18));
                                if (num11*num18 == animalArrayMap1.get(kind).getNumber()) animalArrayMap1.remove(kind);
                                else animalArrayMap1.get(kind).reduceNumber(num11*num18);
                                System.out.println("套餐设置成功!");
                            }else {
                                System.out.println("您所选的品种本店尚未引入或已在套餐中。");
                                break;
                            }
                            mealnumber++;
                        }
                    }catch (InputMismatchException e){
                        Log log = LogFactory.getLog(Main.class);
                        log.error("go wrong!",e);
                    }
                }
                case 4->{
                    System.out.print("请输入您想出售的套餐(如：套餐1)：");
                    String salemealname = in.next();
                    if(!setMealMap.containsKey(salemealname)){
                        System.out.println("未找到您输入的套餐！");
                        break;
                    }
                    System.out.println("该套餐的数量为："+setMealMap.get(salemealname).getMealnumber()+"个,价格为："+ setMealMap.get(salemealname).getMealprice() + "元。");
                    System.out.print("请输入您要出售的数量：");
                    int num19 = in.nextInt();
                    if(num19 > setMealMap.get(salemealname).getMealnumber()){
                        System.out.println("您输入的数量有误！");
                        break;
                    }
                    double earnmoney = petShop.saleMeal(setMealMap,animalArrayMap2,animalArrayMap5,salemealname,num19);
                    money += earnmoney;
                    System.out.println("您收入了"+earnmoney+"元。");
                }
                case 5->{
                    try {
                        System.out.println("请问您要批量进货疫苗还是批量进货宠物?");
                        System.out.println("[1]批量进货疫苗 [2]批量进货宠物 [3]退出" );
                        int num1 = in.nextInt();
                        while (num1 != 3) {
                            switch (num1) {
                                case 1 -> {
                                    System.out.println("请选择您要进货的疫苗种类");
                                    System.out.println("[1]禽用疫苗 [2]猫用疫苗 [3]狗用疫苗");
                                    int num2 = in.nextInt();
                                    if (num2 != 1 && num2 != 2 && num2 != 3) {
                                        System.out.println("请正确输入！");
                                        break;
                                    }
                                    System.out.print("请输入疫苗的单价与购买数量(单位：10剂/次)：");
                                    int num3 = in.nextInt();
                                    int num9 = 10 * in.nextInt();
                                    if (num3 <= 0 || num9 <= 0) {
                                        System.out.println("请正确输入！");
                                        break;
                                    }
                                    if (num3 * num9 > money) {
                                        System.out.println("本店的资金不足，还差" + (num3 * num9 - money) + "元。");
                                    } else {
                                        petShop.getVaccine(num2, num9, num3);
                                        money -= num3 * num9;
                                        System.out.println("购买成功！");
                                    }
                                }
                                case 2 -> {
                                    System.out.print("请选择您要进的宠物种类");
                                    System.out.println("[1]鸟儿 [2]猫猫 [3]狗狗");
                                    int num5 = in.nextInt();
                                    if (num5 != 1 && num5 != 2 && num5 != 3) {
                                        System.out.println("请正确输入！");
                                        break;
                                    }
                                    System.out.print("请您输入品种名与购买数量(单位：10只(条)/次)：");
                                    String kindname = in.next();
                                    int num6 = 10 * in.nextInt();
                                    if (num6 <= 0) {
                                        System.out.println("请正确输入！");
                                        break;
                                    }
                                    System.out.println("请输入宠物打疫苗情况(输入”是“或”否“)：");
                                    String state2 = in.next();
                                    if (!state2.equals("是") && !state2.equals("否")) {
                                        System.out.println("请正确输入！");
                                        break;
                                    }
                                    System.out.print("请输入宠物的年龄、购买价格：");
                                    int num7 = in.nextInt();
                                    double num8 = in.nextDouble();
                                    if (num7 <= 0 || num8 < 0) {
                                        System.out.println("请正确输入！");
                                        break;
                                    }
                                    if (num6 * num8 > money) {
                                        System.out.println("本店的资金不足，还差" + (num6 * num8 - money) + "元。");
                                    } else {
                                        animalArrayMap1.put(kindname, petShop.setAnimal(num6, num5, kindname, num7, num8, "买入", state2));
                                        money -= num6 * num8;
                                        System.out.println("购买成功！");
                                    }
                                }
                                default -> System.out.println("请正确输入！");
                            }
                            System.out.println("请问您要批量进货疫苗还是批量进货宠物?");
                            System.out.println("[1]批量进货疫苗 [2]批量进货宠物 [3]退出" );
                            num1 = in.nextInt();
                        }
                    }catch (InputMismatchException e){
                        Log log = LogFactory.getLog(Main.class);
                        log.error("go wrong!",e);
                    }
                    }
                case 6->{
                    try {
                        System.out.println("寄养的宠物属于：");
                        System.out.println("[1]鸟儿 [2]猫猫 [3]狗狗");
                        int num19 = in.nextInt();
                        if (num19 !=1 && num19 != 2 && num19 !=3){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.print("请输入接受寄养的宠物的品种：");
                        String fosteranimalname = in.next();
                        System.out.println("请输入寄养宠物的相关信息：");
                        System.out.print("年龄：");
                        int fosteranimalage = in.nextInt();
                        if (fosteranimalage <= 0){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.println("宠物打疫苗情况(输入”是“或”否“)：");
                        String state2 = in.next();
                        if (!state2.equals("是") && !state2.equals("否")){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.print("数量：");
                        int fosteranimalnumber = in.nextInt();
                        if (fosteranimalnumber <= 0){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.print("收费标准(元/只)：");
                        double charges = in.nextDouble();
                        if(charges < 0){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.println("您收入了"+fosteranimalnumber*charges+"元。");
                        money += fosteranimalnumber*charges;
                        animalArrayMap3.put(fosteranimalname,petShop.setAnimal(fosteranimalnumber,num19,fosteranimalname,fosteranimalage,charges,"寄养",state2));
                    }catch (InputMismatchException e){
                        Log log = LogFactory.getLog(Main.class);
                        log.error("go wrong!",e);
                    }
                }
                case 7->{
                    try {
                        System.out.println("请输入您要结束寄养的宠物品种：");
                        String fosteranimalname = in.next();
                        if(!animalArrayMap3.containsKey(fosteranimalname)){
                            System.out.println("未在寄养的宠物中找到该品种！");
                            break;
                        }
                        System.out.print("请输入要结束寄养的宠物的数量:");
                        int fosteranimalnumber1 = in.nextInt();
                        if(fosteranimalnumber1 <= 0){
                            System.out.println("请正确输入！");
                            break;
                        }
                        double price = animalArrayMap3.get(fosteranimalname).getPrice();
                        petShop.setAnimal(fosteranimalnumber1,animalArrayMap3,animalArrayMap4,fosteranimalname,"归还",price);
                        int fosteranimalnumber2 = animalArrayMap3.get(fosteranimalname).getNumber();
                        if(fosteranimalnumber1 == fosteranimalnumber2) animalArrayMap3.remove(fosteranimalname);
                        else animalArrayMap3.get(fosteranimalname).reduceNumber(fosteranimalnumber1);
                    }catch (InputMismatchException e){
                        Log log = LogFactory.getLog(Main.class);
                        log.error("go wrong!",e);
                    }
                }
                case 8->{
                    try {
                        System.out.print("请选择您要进的宠物种类");
                        System.out.println("[1]鸟儿 [2]猫猫 [3]狗狗");
                        int num20 = in.nextInt();
                        if (num20 != 1 && num20 != 2 && num20 != 3){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.print("请您输入品种名与购买数量：");
                        String kindname = in.next();
                        int num21 = in.nextInt();
                        if (num21 <= 0){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.println("请输入宠物打疫苗情况(输入”是“或”否“)：");
                        String state2 = in.next();
                        if (!state2.equals("是") && !state2.equals("否")){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.print("请输入宠物的年龄、购买价格：");
                        int num22 = in.nextInt();
                        double num23 = in.nextDouble();
                        if (num22 <= 0 || num23 < 0){
                            System.out.println("请正确输入！");
                            break;
                        }
                        if(num21*num23>money){
                            System.out.println("本店的资金不足，还差"+(num21*num23 - money)+"元。");
                        }
                        else {
                            animalArrayMap1.put(kindname,petShop.setAnimal(num21,num20,kindname,num22,num23,"买入",state2));
                            money -= num21*num23;
                            System.out.println("购买成功！");
                        }
                    }catch (InputMismatchException e){
                        Log log = LogFactory.getLog(Main.class);
                        log.error("go wrong!",e);
                    }
                }
                case 9->{
                    try {
                        System.out.println("输入您要出售的宠物品种：");
                        String saleanimalkindname = in.next();
                        if (!animalArrayMap1.containsKey(saleanimalkindname) && !animalArrayMap1.containsKey(saleanimalkindname+"*")){
                            System.out.println("店里非套餐与寄养的宠物中找不到该品种！");
                            break;
                        }
                        System.out.println(animalArrayMap1.get(saleanimalkindname).toString());
                        System.out.print("请输入您要出售的数量：");
                        int saleanimalnumber = in.nextInt();
                        if (saleanimalnumber < animalArrayMap1.get(saleanimalkindname).getNumber()){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.print("请输入售价：");
                        double saleprice = in.nextDouble();
                        if (saleprice <= 0){
                            System.out.println("请正确输入！");
                            break;
                        }
                        double earnmoney = petShop.saleAnimal(animalArrayMap1,animalArrayMap5,saleanimalkindname,saleanimalnumber,saleprice);
                        System.out.println("您收入了"+earnmoney+"元。");
                        money += earnmoney;
                    }catch (InputMismatchException e){
                        Log log = LogFactory.getLog(Main.class);
                        log.error("go wrong!",e);
                    }
                }
                case 10->{
                    try {
                        int flag1 = 0;
                        System.out.println("未打疫苗的宠物如下：");
                        for (String key : animalArrayMap1.keySet()) {
                            if (animalArrayMap1.get(key).getState2().equals("否")){
                                System.out.println(animalArrayMap1.get(key).toString());
                                flag1 = 1;
                            }
                        }
                        for (String key : animalArrayMap2.keySet()) {
                            if (animalArrayMap2.get(key).getState2().equals("否")){
                                System.out.println(animalArrayMap2.get(key).toString());
                                flag1 = 1;
                            }
                        }
                        for (String key : animalArrayMap3.keySet()) {
                            if (animalArrayMap3.get(key).getState2().equals("否")){
                                System.out.println(animalArrayMap3.get(key).toString());
                                flag1 = 1;
                            }
                        }
                        if (flag1 == 0){
                            System.out.println("好像没有宠物需要打疫苗。");
                            break;
                        }
                        System.out.println("您要给那些宠物打疫苗：");
                        System.out.println("[1]买入代售 [2]在套餐中 [3]接受寄养");
                        int num21 = in.nextInt();
                        if(num21 !=1 && num21 !=2 &&num21 != 3){
                            System.out.println("请正确输入！");
                            break;
                        }
                        System.out.print("请输入要打疫苗的品种(直接给同一状态下该品种的所有宠物打疫苗)：");
                        String getvaccinedanimalkindname = in.next();
                        int flag = switch (num21){
                            case 1->petShop.getVaccined(animalArrayMap1,getvaccinedanimalkindname);
                            case 2->petShop.getVaccined(animalArrayMap2,getvaccinedanimalkindname);
                            default->petShop.getVaccined(animalArrayMap3,getvaccinedanimalkindname);
                        };
                        if(flag == 0){
                            System.out.println("出错啦！出错原因可能为：[1]疫苗数量不够 [2]本店没有该品种的宠物！");
                        }else {
                            System.out.println("打疫苗成功！");
                        }
                    }catch (InputMismatchException e){
                        Log log = LogFactory.getLog(Main.class);
                        log.error("go wrong!",e);
                    }
                }
                case 11->Animal.printInformation();
                case 12-> System.out.println("本店的资产为："+money+"元。");
                case 13->{
                        System.out.println("请输入您要冲入的金额（单位：元）:");
                        double money1 = in.nextDouble();
                        if (money1 < 0){
                            System.out.println("请正确输入！");
                            break;
                        }
                        money = money1;
                }
                case 14->{
                    if (setMealMap.size() == 0) System.out.println("还未设置套餐。");
                    for (String key : setMealMap.keySet()){
                        System.out.println(key + ":" + setMealMap.get(key).toString());
                    }
                }
                default ->System.out.println("请正确输入！");
            }
            System.out.println("[1]查看宠物 [2]查看疫苗 [3]设置套餐 [4]出售套餐 [5]批量进货 [6]接受寄养\n" +
                    "[7]结束寄养 [8]宠物进货 [9]宠物出售 [10]打疫苗 [11]打印近日交易信息 [12]资金结算\n" +
                    "[13]冲钱 [14]查看套餐 [15]退出");
            t = in.nextInt();
        }
        System.out.println("店长慢走不送~");
    }
}
