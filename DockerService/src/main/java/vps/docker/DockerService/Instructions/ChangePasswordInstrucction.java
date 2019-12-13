package vps.docker.DockerService.Instructions;

import vps.docker.DockerService.Instructions.Results.DockerCreateResult;
import vps.docker.DockerService.Instructions.Results.InstructionResult;

import java.util.List;

public class ChangePasswordInstrucction extends Instruction {

    public ChangePasswordInstrucction(String dockerId, String pass){
        super();
        _command.add("docker");
        _command.add("exec");
        _command.add("-it");
        _command.add(dockerId);
        _command.add("/bin/sh");
        _command.add("-c");
        _command.add("touch /root/pass && sed -i 'root:"+pass+"' /root/pass");

    }

    @Override
    protected void generateResult() {
        generateResult(_errorReader);
        if (_result == null){
            _result=new InstructionResult(false,"");
        }
    }

    private void generateResult(List<String> input){
        int i = 0;

        while (_result == null &&  i <input.size()) {
            String s = input.get(i);
            if (s != null && !s.isEmpty()) {
                if(_exitValue == 0)
                    _result = new InstructionResult(false,"");
                else
                    _result = new InstructionResult(true,"The instruction could not be executed\n"+s);
            }
        }
    }
}
