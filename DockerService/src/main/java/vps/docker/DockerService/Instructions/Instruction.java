package vps.docker.DockerService.Instructions;

import vps.docker.DockerService.Instructions.Results.InstructionResult;

import java.util.ArrayList;
import java.util.List;

public abstract class Instruction {


    protected ArrayList<String> _command;

    protected InstructionResult _result;

    protected List<String> _errorReader;

    protected List<String> _outputReader;

    protected int _exitValue;

    public Instruction(){
        _command = new ArrayList<>();
        _result = null;
    }

    public  String[] getCommand(){
        return _command.toArray(new String[0]);
    };

    public void processResult(List<String> outputReader, List<String> errorReader, int exitValue)  {
        _errorReader = errorReader;
        _outputReader = outputReader;
        _exitValue = exitValue;
        generateResult();

    }

    protected abstract void generateResult() ;

    public InstructionResult getResult(){
        return _result;
    }

}
