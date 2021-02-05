public class setMeal extends Vaccine implements petShop{
    private int vaccinekind;//编号1到3分别表示禽用疫苗、猫用疫苗、狗用疫苗
    private String animalkindname;
    private String mealname;
    private int mealnumber;
    private int vaccinenumber;
    private int animalnumber;//设置套餐中疫苗和动物的数量
    private double mealprice;
    public setMeal(){

    }
    public setMeal(int vaccinekind,String animalkindname,int vaccinenumber,int animalnumber,double mealprice,String mealname,int mealnumber){
        this.vaccinekind = vaccinekind;
        this.animalkindname = animalkindname;
        this.vaccinenumber = vaccinenumber;
        this.animalnumber = animalnumber;
        this.mealprice = mealprice;
        this.mealname = mealname;
        this.mealnumber = mealnumber;
        switch (vaccinekind){
            case 1->{
                Vaccine.vacbirdinmeal -= vaccinenumber*mealnumber;
                Vaccine.vacbirdnotinmeal += vaccinenumber*mealnumber;
            }
            case 2->{
                Vaccine.vaccatinmeal -= vaccinenumber*mealnumber;
                Vaccine.vaccatnotinmeal += vaccinenumber*mealnumber;
            }
            case 3 ->{
                Vaccine.vacdoginmeal -= vaccinenumber*mealnumber;
                Vaccine.vacdognotinmeal += vaccinenumber*mealnumber;
            }
        }
    }
    @Override
    public String toString(){
        String str = switch (vaccinekind){
            case 1 ->"禽用疫苗";
            case 2 ->"猫用疫苗";
            default ->"狗用疫苗";
        };
        return str+vaccinenumber+"支,"+animalkindname+animalnumber+"只,售价为"+mealprice+"元,套餐数量为"+mealnumber+"个。";
    }
    public int getMealnumber(){
        return mealnumber;
    }
    public double getMealprice(){
        return mealprice;
    }
    public void changeMealnumber(int num){
        mealnumber -= num;
    }
    public int getVaccinekind(){
        return vaccinekind;
    }
    public String getAnimalkindname(){
        return animalkindname;
    }
    public int getAnimalnumber(){
        return animalnumber;
    }
    public int getVaccinenumber(){ return vaccinenumber; }
//    public void reduceVaccine(int vaccinekind,int salemealnumber){
//        switch (vaccinekind){
//            case 1-> Vaccine.vacbirdnotinmeal -= vaccinenumber*salemealnumber;
//            case 2-> Vaccine.vaccatnotinmeal -= vaccinenumber*salemealnumber;
//            default -> Vaccine.vacdognotinmeal -= vaccinenumber*salemealnumber;
//        }
//    }
}
