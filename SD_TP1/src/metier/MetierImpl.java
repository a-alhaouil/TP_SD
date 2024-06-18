package metier;

import dao.IDao;

public class MetierImpl implements IMetier {
    //couplege fible
    private IDao dao;
    @Override
    public double calcul() {
        double temp = dao.getData();
        double result = temp*540/Math.cos(temp*Math.PI);

        return result;
    }
    /*
    Injecter dans la variable dao   un objet d'une classe qui implement IDao
     */
    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
