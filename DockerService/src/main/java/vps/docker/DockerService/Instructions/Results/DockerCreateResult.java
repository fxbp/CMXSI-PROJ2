package vps.docker.DockerService.Instructions.Results;

public class DockerCreateResult extends InstructionResult {

    private String dockerId;

    public DockerCreateResult(boolean hasError, String error) {
        this(null,hasError,error);
    }

    public DockerCreateResult(String dockerId){
        this(dockerId,false,null);
    }

    public DockerCreateResult(String dockerId, boolean hasError, String error){
        super(hasError,error);
        this.dockerId = dockerId;
    }

    public String getDockerId() {
        return dockerId;
    }

    public void setDockerId(String dockerId) {
        this.dockerId = dockerId;
    }
}
