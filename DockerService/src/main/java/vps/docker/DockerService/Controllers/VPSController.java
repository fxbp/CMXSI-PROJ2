package vps.docker.DockerService.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vps.docker.DockerService.Entities.DockerInfo;
import vps.docker.DockerService.Services.VPSService;

@RestController
@RequestMapping("/api/vps")
public class VPSController {

    @Autowired
    private VPSService vpsService;

    @PostMapping
    public ResponseEntity<?> createDocker(@RequestBody DockerInfo dockerInfo){
        if(dockerInfo == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try{
            return vpsService.create(dockerInfo);
        }
        catch (Exception ex){
            return new ResponseEntity<>(ex,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
