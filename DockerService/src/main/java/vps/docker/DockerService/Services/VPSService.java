package vps.docker.DockerService.Services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import vps.docker.DockerService.Entities.DockerInfo;
import vps.docker.DockerService.Instructions.DockerCreateInstruction;
import vps.docker.DockerService.Instructions.Results.DockerCreateResult;
import vps.docker.DockerService.Runnable.CmdRunnable;

@Service
public class VPSService {

    public ResponseEntity<?> create(DockerInfo dockerInfo){

        System.out.println("Dins del servei");
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
            return new ResponseEntity<>("Error creating docker", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(result.getDockerId(),HttpStatus.BAD_REQUEST.OK);
    }

}
