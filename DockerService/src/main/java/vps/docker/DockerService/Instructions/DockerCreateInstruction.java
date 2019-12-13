package vps.docker.DockerService.Instructions;

import vps.docker.DockerService.Entities.DockerInfo;
import vps.docker.DockerService.Instructions.Results.DockerCreateResult;

import java.util.List;

public class DockerCreateInstruction extends Instruction {


    public DockerCreateInstruction(DockerInfo info){
        super();
        _command.add("docker");
        _command.add("run");
        _command.add("-d");

        _command.add("-p");
        _command.add(info.getSshPort());

        _command.add("-p");
        _command.add(info.getHttpPort());

        _command.add("-p");
        _command.add(info.getHttpsPort());

        for(String ports : info.getBindedPorts()){
            _command.add("-p");
            _command.add(ports);

        }

        _command.add("--memory="+info.getMemory());
        _command.add("--cpus="+info.getCpus());

        if(info.isPrivileged()){
            _command.add("--privileged");
            _command.add("-v");
            _command.add("/sys/fs/cgroup:/sys/fs/cgroup:ro");
        }

        _command.add(info.getSystem());

    }


    @Override
    protected void generateResult()  {
        generateResult(_outputReader);
        generateResult(_errorReader);
    }

    private void generateResult(List<String> input){
        int i = 0;

        while (_result == null &&  i <input.size()) {
            String s = input.get(i);
            if (s != null && !s.isEmpty()) {
                if(_exitValue == 0)
                    _result = new DockerCreateResult(s);
                else
                    _result = new DockerCreateResult(true,"The instruction could not be executed\n"+s);
            }
        }
    }
}


