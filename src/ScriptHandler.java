import javafx.concurrent.Task;
import java.io.*;
import java.util.Arrays;


public class ScriptHandler extends Task<Boolean> {

    private String script;
    private String python;
    private ProcessBuilder builder;
    private Process p;
    private OutputStream stdout = null;
    private DoorLockKiosk stageController;

    public ScriptHandler(DoorLockKiosk stgCon, String scriptPath, String pythonPath) {
        this.script = scriptPath;
        this.python = pythonPath;
        this.stageController = stgCon;
    }

    @Override
    protected Boolean call() {
        builder = new ProcessBuilder(Arrays.asList(python, script));

        try {
            p = builder.start();
            stdout = p.getOutputStream();

            //Read every Line of the stdout from the process and update message if stdout = "true"
            BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            System.out.println("Script starts: ");
            while ((line = bfr.readLine()) != null) {
                System.out.println("Script Output: " + line);
                updateMessage(line);
            }


        } catch (IOException e) {
            System.out.println("du Faule Sau! Logging noch implementieren" + e);
        }
        return true;
    }


}

