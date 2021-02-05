public class Vaccine implements petShop{
    protected static int vacbirdinmeal;
    protected static int vaccatinmeal;
    protected static int vacdoginmeal;
    protected static int vacbirdnotinmeal;
    protected static int vaccatnotinmeal;
    protected static int vacdognotinmeal;
    protected static double price1;
    protected static double price2;
    protected static double price3;//1到3分别对应三种疫苗的单价
    public static void getVaccine(){
        if(vacbirdinmeal != 0)System.out.println("(可用)鸟儿用的疫苗有：" + vacbirdinmeal + "支" + "单价为" + price1 + "元。");
        if(vaccatinmeal != 0)System.out.println("(可用)猫猫用的疫苗有：" + vaccatinmeal + "支" + "单价为" + price2 + "元。");
        if(vacdoginmeal != 0)System.out.println("(可用)狗狗用的疫苗有：" + vacdoginmeal + "支" + "单价为" + price3 + "元。");
        if(vacbirdinmeal+vaccatinmeal+vacdoginmeal==0) System.out.println("还未补进疫苗。");
        if(vacbirdnotinmeal != 0)System.out.println("(在套餐中不可用)鸟儿用的疫苗有：" + vacbirdnotinmeal + "支");
        if(vaccatnotinmeal != 0)System.out.println("(在套餐中不可用)猫猫用的疫苗有：" + vaccatnotinmeal + "支");
        if(vacdognotinmeal != 0)System.out.println("(在套餐中不可用)狗狗用的疫苗有：" + vacdognotinmeal + "支");
    }

    public static int getVaccinenumber(int num){
        switch (num){
            case 1->{
                return vacbirdinmeal;
            }
            case 2->{
                return vaccatinmeal;
            }
            default ->{
                return vacdoginmeal;
            }
        }
    }

    public static void setVacbird(int vacbirdinmeal,double price1) {
        Vaccine.vacbirdinmeal += vacbirdinmeal;
        Vaccine.price1 = price1;
    }

    public static void setVaccat(int vaccatinmeal,double price2) {
        Vaccine.vaccatinmeal += vaccatinmeal;
        Vaccine.price2 = price2;
    }

    public static void setVacdog(int vacdoginmeal,double price3) {
        Vaccine.vacdoginmeal += vacdoginmeal;
        Vaccine.price3 = price3;
    }
    public static void reduceVaccine(int vaccinekind,int vaccinenumber,int salemealnumber){
        switch (vaccinekind){
            case 1-> Vaccine.vacbirdnotinmeal -= vaccinenumber*salemealnumber;
            case 2-> Vaccine.vaccatnotinmeal -= vaccinenumber*salemealnumber;
            default -> Vaccine.vacdognotinmeal -= vaccinenumber*salemealnumber;
        }
    }
    public static void reduceVaccine(int vaccinekind,int vaccinenumber){
        switch (vaccinekind){
            case 1-> Vaccine.vacbirdinmeal -= vaccinenumber;
            case 2-> Vaccine.vaccatinmeal -= vaccinenumber;
            default -> Vaccine.vacdoginmeal -= vaccinenumber;
        }
    }
}
