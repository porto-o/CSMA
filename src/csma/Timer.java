package csma;


public class Timer {

    public int randomHibernación(int contador) {
        int random;
        int tempora;
        tempora = Math.min(contador, 10);
        random = (int) (Math.random() * (Math.pow(2, tempora) - 1));
        return random;
    }
}
