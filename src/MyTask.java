import java.util.Timer;
import java.util.TimerTask;

class MyTask extends TimerTask {
    Login login=new Login();
    public static int count=0;
    public void run() {
        System.out.println("Task is running");
        count+=1;
        if (count == 2) {
            login.timer.cancel();
            login.timer.purge();
        }
    }
}