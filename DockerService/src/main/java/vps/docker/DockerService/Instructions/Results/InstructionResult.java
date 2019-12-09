package vps.docker.DockerService.Instructions.Results;

public class InstructionResult {
    protected boolean hasError;

    protected String error;

    public InstructionResult(String error) {
        this.hasError = false;
        this.error = error;
    }

    public InstructionResult(boolean hasError, String error) {
        this.hasError = hasError;
        this.error = error;
    }

    public boolean hasError() {
        return hasError;
    }

    public void setHasError(boolean hasError) {
        this.hasError = hasError;
    }

    public String getError(){
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
