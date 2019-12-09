package vps.docker.DockerService.Runnable;

import org.springframework.beans.factory.annotation.Value;
import vps.docker.DockerService.Instructions.Instruction;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CmdRunnable implements Runnable  {

    private Instruction _cmd;
    @Value("${run.debug}")
    private boolean _debug;

    public CmdRunnable(Instruction command) {
        _cmd = command;
    }

    @Override
    public void run() {

        try {
            Process p = Runtime.getRuntime().exec(_cmd.getCommand());
            p.waitFor();
            BufferedReader stdInput = new BufferedReader(new
                    InputStreamReader(p.getInputStream()));
            BufferedReader stdError = new BufferedReader(new
                    InputStreamReader(p.getErrorStream()));
            // read the output from the command
            List<String> output = readBuffer(stdInput,"Here is the standard output of the command:\n");
            // read any errors from the attempted command
            List<String> error = readBuffer(stdError,"Here is the standard error of the command (if any):\n");

            _cmd.processResult(output,error,p.exitValue());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private List<String> readBuffer(BufferedReader input, String message) throws IOException {
        if(_debug)
            System.out.println(message);
        String s;
        List<String> result = new ArrayList<>();
        while ((s = input.readLine()) != null) {
            if(_debug)
                System.out.println(s);
            result.add(s);
        }
        return result;
    }
}
