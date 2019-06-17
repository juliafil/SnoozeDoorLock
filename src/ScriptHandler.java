import javafx.concurrent.Task;

import java.io.*;
import java.util.Arrays;

/**
 * @author Ertel
 * ScriptHandler executes the python scripts in a seperate Task.
 * The Output of the Script is redirected to the message Property of Task to invoke the EventListener in the Application Class
 * If the state of the door changes the Listener in the Application Class is invoked to handle the Cahnge
 **/

public class ScriptHandler extends Task<Boolean> {

    private String script;
    private String python;
    private ProcessBuilder builder;
    private Process p;
    private boolean failed = false;

    public ScriptHandler(String scriptPath, String pythonPath) {
        this.script = scriptPath;
        this.python = pythonPath;

    }

    public void killProcess() {
        if (p.isAlive()) {
            p.destroy();
            System.out.println("killed");
        }
    }

    @Override
    protected Boolean call() {
        builder = new ProcessBuilder(Arrays.asList(python, script));


        try {
            p = builder.start(); // start process (script)

        } catch (IOException e) {
            System.out.println("Error when trying to execute Script");
            //Error Screen einblenden?
        }

        BufferedReader bferr = new BufferedReader(new InputStreamReader(p.getErrorStream()));
        String err;
        try {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (bferr.ready()) {
                err = bferr.readLine();
                if (err.contains("Errno")) {
                    System.out.println("Script '" + script + "' could not be executed!");
                    failed = true;

                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading the output of the Script");
            //Error Screen einblenden?
        }

        //Read every Line of the output from the process and call updateMessage() to invoke eventListener
        BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String line;

        if (!failed) {
            System.out.println("Script starts:");
            try {
                while ((line = bfr.readLine()) != null) {
                    System.out.println("Script Output: " + line);
                    updateMessage(line);

                }
            } catch (IOException e) {
                System.out.println("Error while reading the output of the Script");
                //Error Screen einblenden?

            }
            return false;
        }
        return true;
    }

}


