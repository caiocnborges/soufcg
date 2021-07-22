import java.util.ArrayList;
import java.util.List;

class Garfo {

    private int idGarfo;
    private boolean estadoGarfo;
    private int dono;

    public Garfo(int id){
        idGarfo = id;
        estadoGarfo = false;
        dono = -1; }

    public int getIdGarfo(){
        return idGarfo; }

    public void setIdGarfo(int g){
        idGarfo = g; }

    public int getDonoGarfo(){
        return dono; }

    public void setDonoGarfo(int d){
        dono = d; }

    public boolean getEstadoGarfo(){
        return estadoGarfo; }

    public void setEstadoGarfo(boolean ocupado){
        estadoGarfo = ocupado; }
}



class Filosofo implements Runnable {

    final int N = 5;
    List garfos;
    int filosofo;
    Filosofo (List gs, int fil){
        garfos = gs;
        filosofo = fil; }

    public void run(){
        for (int i=0; i<5; i++){
            pensaMuito(filosofo);
            pegaGarfo(filosofo, filosofo);
            pegaGarfo((filosofo+1)%N, filosofo);
            comeEspaguete(filosofo);
            largaGarfo(filosofo, filosofo);
            largaGarfo((filosofo+1)%N, filosofo); }
    }

    private void pensaMuito(int fil){
        switch (fil) {
            case 0:
                try{
                    System.out.println("!!>"+Thread.currentThread().getName()+" PENSA");
                    Thread.sleep(500);}
                catch (InterruptedException e){}
            case 1:
                try{
                    System.out.println("!!>"+Thread.currentThread().getName()+" PENSA");
                    Thread.sleep(1000);}
                catch (InterruptedException e){}
            case 2:
                try{
                    System.out.println("!!>"+Thread.currentThread().getName()+" PENSA");
                    Thread.sleep(1500);}
                catch (InterruptedException e){}
            case 3:
                try{
                    System.out.println("!!>"+Thread.currentThread().getName()+" PENSA");
                    Thread.sleep(2000);}
                catch (InterruptedException e){}
            case 4:
                try{
                    System.out.println("!!>"+Thread.currentThread().getName()+" PENSA");
                    Thread.sleep(2500);}
                catch (InterruptedException e){} }
    }

    private void pegaGarfo(int pos, int dono){
        if (((Garfo)garfos.get(pos)).getEstadoGarfo()==false){
            System.out.println("++>"+Thread.currentThread().getName()+" PEGA GARFO "+ pos);
            ((Garfo)garfos.get(pos)).setEstadoGarfo(true);
            ((Garfo)garfos.get(pos)).setDonoGarfo(dono); }
    }

    private void largaGarfo(int pos, int dono){
        if (((Garfo)garfos.get(pos)).getEstadoGarfo()==true &&
                ((Garfo)garfos.get(pos)).getDonoGarfo() == dono){
            System.out.println("-->"+Thread.currentThread().getName()+" LARGA GARFO "+ pos);
            ((Garfo)garfos.get(pos)).setEstadoGarfo(false);
            ((Garfo)garfos.get(pos)).setDonoGarfo(-1); }
    }

    private void comeEspaguete(int fil){
        if (((Garfo)garfos.get(fil)).getEstadoGarfo() &&
                ((Garfo)garfos.get((fil+1)%N)).getEstadoGarfo() &&
                ((Garfo)garfos.get(fil)).getDonoGarfo()==fil &&
                ((Garfo)garfos.get((fil+1)%N)).getDonoGarfo()==fil){
            System.out.println("@@>"+Thread.currentThread().getName()+" COME ESPAGUETE");
            try{ Thread.sleep(5000);}
            catch (InterruptedException e){} }
    }
}


// CÓDIGO MAIN //


class Main {

    public static void main(String[] args) {

        List<Garfo>garfos = new ArrayList<Garfo>();
        for (int i = 0; i<=4; i++){
            Garfo garfo = new Garfo(i);
            garfos.add(i,garfo); }

        Filosofo r0 = new Filosofo(garfos, 0);
        Thread f0 = new Thread(r0);
        Filosofo r1 = new Filosofo(garfos, 1);
        Thread f1 = new Thread(r1);
        Filosofo r2 = new Filosofo(garfos, 2);
        Thread f2 = new Thread(r2);
        Filosofo r3 = new Filosofo(garfos, 3);
        Thread f3 = new Thread(r3);
        Filosofo r4 = new Filosofo(garfos, 4);
        Thread f4 = new Thread(r4);

        f0.setName("F0");
        f1.setName("F1");
        f2.setName("F2");
        f3.setName("F3");
        f4.setName("F4");

        f0.start();
        f1.start();
        f2.start();
        f3.start();
        f4.start();
    }
}