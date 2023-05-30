public class MyThread implements Runnable {
    public double Tstart;
    public double Tend;
    public double Tstep;
    public double Tresult;  
    
    public MyThread(double T_start, double T_end, double T_step){
        Tstart = T_start;
        Tend = T_end;
        Tstep = T_step;
    };

    public void setTstart(double T_start, double T_end, double T_step) {
        Tstart = T_start;
        Tend = T_end;
        Tstep = T_step;
    }
    
    //Override
    
    public void run (){
        while(Tstart < Tend){
            if(Tstart + Tstep > Tend){
                Tstep = Tend - Tstart;
            }
            
            Tresult += ( (Math.sqrt(Tstart) + Math.sqrt(Tstart + Tstep)) /2)*Tstep; //sqrt(x)
                    Tstart += Tstep;
        }
        //System.out.println(Tresult);
    }
    public double Output() {
    System.out.println(Tresult);
    return 0;
    }
}
