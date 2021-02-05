import java.util.Map;

public interface petShop {
    static void printfAnimalinformation(Map<String,Animal>animalArrayMap1,Map<String,Animal>animalArrayMap2,Map<String,Animal>animalArrayMap3,Map<String,Animal>animalArrayMap4){
        if (animalArrayMap1.size() > 0) {
            System.out.println("不在套餐中的宠物：");
            for (String key : animalArrayMap1.keySet()) {
                System.out.println(animalArrayMap1.get(key).toString());
            }
        }
        if (animalArrayMap2.size() > 0) {
            System.out.println("在套餐中的宠物：");
            for (String key : animalArrayMap2.keySet()) {
                System.out.println(animalArrayMap2.get(key).toString());
            }
        }
        if (animalArrayMap3.size() > 0) {
            System.out.println("寄养的宠物：");
            for (String key : animalArrayMap3.keySet()) {
                System.out.println(animalArrayMap3.get(key).toString());
            }
        }
        if (animalArrayMap4.size() > 0) {
            System.out.println("归还的宠物：");
            for (String key : animalArrayMap4.keySet()) {
                System.out.println(animalArrayMap4.get(key).toString());
            }
        }
    }
    static void getVaccine(int vaccinekind,int vaccinenumber,int vaccineprice){
        switch (vaccinekind){
            case 1->Vaccine.setVacbird(vaccinenumber,vaccineprice);
            case 2->Vaccine.setVaccat(vaccinenumber,vaccineprice);
            case 3->Vaccine.setVacdog(vaccinenumber,vaccineprice);
        }
    }
    static Animal setAnimal(int number,int animalkind,String kindname,int age,double price,String state1,String state2){
        switch (animalkind){
            case 1->{
                return new Bird(number,age, kindname, price,state1,animalkind,state2);
            }
            case 2->{
                return new Cat(number,age, kindname, price,state1,animalkind,state2);
            }
            default -> {
                return new Dog(number,age, kindname, price,state1,animalkind,state2);
            }
        }//这个方法其实也可以写在animal类里
    }
    static double saleMeal(Map<String,setMeal> setMealMap,Map<String,Animal>animalArrayMap2,Map<String,Animal>animalArrayMap5,String salemealname,int salemealnumber){//salemealnumber是想要卖掉的数量
        int vaccinekind = setMealMap.get(salemealname).getVaccinekind();
        int vaccinenumber = setMealMap.get(salemealname).getVaccinenumber();
        Vaccine.reduceVaccine(vaccinekind,vaccinenumber,salemealnumber);
        String animalkindname = setMealMap.get(salemealname).getAnimalkindname();
        int animalnumber = setMealMap.get(salemealname).getAnimalnumber();//获得单个套餐中的动物数量
        double mealprice = setMealMap.get(salemealname).getMealprice();
        int number = animalArrayMap2.get(animalkindname).getNumber();//获得套餐中的宠物品种的总数
        petShop.setAnimal(salemealnumber*animalnumber,animalArrayMap2,animalArrayMap5,animalkindname,"在套餐中已售",mealprice);
        double earnmoney =  salemealnumber*setMealMap.get(salemealname).getMealprice();
        if (salemealnumber == setMealMap.get(salemealname).getMealnumber()){
            setMealMap.remove(salemealname);
        }else {
            setMealMap.get(salemealname).changeMealnumber(salemealnumber);
        }
        if(salemealnumber*animalnumber == number){
            animalArrayMap2.remove(animalkindname);
        }else {
            animalArrayMap2.get(animalkindname).reduceNumber(salemealnumber*animalnumber);
        }
        return earnmoney;
    }
    static double saleAnimal(Map<String,Animal>animalArrayMap1,Map<String,Animal>animalArrayMap5,String saleanimalkindname,int saleanimalnumber,double saleprice){
        int animalnumber = animalArrayMap1.get(saleanimalkindname).getNumber();
        animalArrayMap1.get(saleanimalkindname).reduceNumber(animalnumber);
        setAnimal(animalnumber,animalArrayMap1,animalArrayMap5,saleanimalkindname,"已售",saleprice);
        if (animalnumber == saleanimalnumber) animalArrayMap1.remove(saleanimalkindname);
        else animalArrayMap1.get(saleanimalkindname).reduceNumber(saleanimalnumber);
        return saleprice*saleanimalnumber;
    }
    static void setAnimal(int animalnumber, Map<String, Animal> animalArrayMap1,Map<String, Animal> animalArrayMap2, String animalkindname, String state1,double price){
        int num15 = animalArrayMap1.get(animalkindname).getKindnumber();
        int num16 = animalArrayMap1.get(animalkindname).getAge();
        String state2 = animalArrayMap1.get(animalkindname).getState2();
        animalArrayMap2.put(animalkindname,petShop.setAnimal(animalnumber,num15,animalkindname,num16,price,state1,state2));
    }
    static int getVaccined(Map<String,Animal>animalArrayMap,String getvaccinedanimalkindname){
        int getvaccinedanimalkindnumber = animalArrayMap.get(getvaccinedanimalkindname).getKindnumber();
        int getvaccinedanimalnumber = animalArrayMap.get(getvaccinedanimalkindname).getNumber();
        if (!animalArrayMap.containsKey(getvaccinedanimalkindname) || getvaccinedanimalnumber > Vaccine.getVaccinenumber(getvaccinedanimalkindnumber)) return 0;
        Vaccine.reduceVaccine(getvaccinedanimalkindnumber,getvaccinedanimalnumber);
        String state1 = animalArrayMap.get(getvaccinedanimalkindname).getState1();
        int num15 = animalArrayMap.get(getvaccinedanimalkindname).getKindnumber();
        int num16 = animalArrayMap.get(getvaccinedanimalkindname).getAge();
        double price = animalArrayMap.get(getvaccinedanimalkindname).getPrice();
        animalArrayMap.put(getvaccinedanimalkindname,petShop.setAnimal(getvaccinedanimalnumber,num15,getvaccinedanimalkindname,num16,price,state1,"是"));//直接覆盖未打疫苗的状态
        return 1;
    }
}
