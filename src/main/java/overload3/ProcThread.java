package overload3;

import hudson.Launcher.ProcStarter;
import hudson.model.BuildListener;

import java.io.IOException;
import java.util.Random;

public class ProcThread extends Thread {

    private ProcStarter proc;
    private BuildListener listener;
    private static Random rnd = new Random();

    public ProcThread(ProcStarter proc, BuildListener listener) {
        this.proc = proc;
        this.listener = listener;

    }

    @Override
    public void run() {
        try {
            proc.cmdAsSingleString("cmd /c \"dir c:\\windows & ping 10.158.238.1 -n " + (rnd.nextInt(10) + 1) + "\" ");
            proc.stdout(listener);
            int code = proc.join();
            listener.fatalError("returned  " + code);
        } catch (IOException e) {
            listener.fatalError("IOException " + e.getMessage());
            e.printStackTrace();
        } catch (InterruptedException e) {
            listener.fatalError("InterruptedException " + e.getMessage());
            e.printStackTrace();
        }

    }
}
