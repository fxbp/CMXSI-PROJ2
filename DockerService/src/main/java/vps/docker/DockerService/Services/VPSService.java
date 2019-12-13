package vps.docker.DockerService.Services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.SocketUtils;
import vps.docker.DockerService.Entities.DockerInfo;
import vps.docker.DockerService.Instructions.ChangePasswordInstrucction;
import vps.docker.DockerService.Instructions.DockerCreateInstruction;
import vps.docker.DockerService.Instructions.Results.DockerCreateResult;
import vps.docker.DockerService.Instructions.Results.InstructionResult;
import vps.docker.DockerService.Runnable.CmdRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.concurrent.TimeUnit;

@Service
public class VPSService {

    public final static int MAX_PORTS = 13;
    public final static int INTERNAL_INIT_PORT=3000;
    public ResponseEntity<?> create(DockerInfo dockerInfo){



        System.out.println("Dins del servei");

        SortedSet<Integer> availableTcpPorts = SocketUtils.findAvailableTcpPorts(MAX_PORTS);
        ArrayList<Integer> ports = new ArrayList<>(availableTcpPorts);
        dockerInfo.setSshPort(ports.get(0)+":"+22);
        dockerInfo.setHttpPort(ports.get(1)+":"+80);
        dockerInfo.setHttpsPort(ports.get(2)+":"+443);
        List<String> bindedPorts = new ArrayList<>();
        for(int i=3;i<MAX_PORTS;i++){
            bindedPorts.add(ports.get(i)+ ":" + (INTERNAL_INIT_PORT+ i - 3));
        }
        dockerInfo.setBindedPorts(bindedPorts);

        String generatedString = RandomStringUtils.randomAlphanumeric(10);
        dockerInfo.setPass(generatedString);

        //de moment nomes s'accepta el tipus ubuntu
        if(dockerInfo.isPrivileged()){
            dockerInfo.setSystem("ubuntu-systemd");
        }
        else{
            dockerInfo.setSystem("ubuntu-ssh");
        }

        DockerCreateInstruction createInstruction = new DockerCreateInstruction(dockerInfo);

        System.out.println("Comanda docker:");
        for(String s:createInstruction.getCommand()){
            System.out.print(s+ " ");
        }
        System.out.println();
        CmdRunnable runnable = new CmdRunnable(createInstruction);
        runnable.run();

        DockerCreateResult result = (DockerCreateResult) createInstruction.getResult();
        if(result == null || result.hasError()){
            System.out.println(result.getError());
            return new ResponseEntity<>("Error creating docker", HttpStatus.INTERNAL_SERVER_ERROR);

        }

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ChangePasswordInstrucction chInstruction = new ChangePasswordInstrucction(result.getDockerId(),generatedString);

        for(String s:chInstruction.getCommand()){
            System.out.print(s+ " ");
        }
        System.out.println();
        runnable = new CmdRunnable(chInstruction);
        runnable.run();

        InstructionResult chResult = chInstruction.getResult();
        /*if(result !=null && result.hasError()){
            return new ResponseEntity<>("Error creating docker", HttpStatus.INTERNAL_SERVER_ERROR);
        }*/

        return new ResponseEntity<>(dockerInfo,HttpStatus.OK);
    }

}
