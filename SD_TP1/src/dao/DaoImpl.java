package dao;

public class DaoImpl implements IDao{


    @Override
    public double getData() {
        /*
        db connection

         */
        System.out.println("version de base de donnees");
        double temp =Math.random()*40;
//        double result = temp;
        return temp;
    }
}
