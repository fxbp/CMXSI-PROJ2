package vps.docker.DockerService.Entities;

import java.util.List;

public class DockerInfo {


    private String memory;

    private String cpus;

    private boolean privileged;

    private String sshPort;

    private String httpPort;

    private String httpsPort;

    private List<String> bindedPorts; //external:internal ports

    private String system;

    private String pass;

    public DockerInfo(){

    }


    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCpus() {
        return cpus;
    }

    public void setCpus(String cpus) {
        this.cpus = cpus;
    }

    public boolean isPrivileged() {
        return privileged;
    }

    public void setPrivileged(boolean privileged) {
        this.privileged = privileged;
    }

    public String getSshPort() {
        return sshPort;
    }

    public void setSshPort(String sshPort) {
        this.sshPort = sshPort;
    }

    public String getHttpPort() {
        return httpPort;
    }

    public void setHttpPort(String httpPort) {
        this.httpPort = httpPort;
    }

    public String getHttpsPort() {
        return httpsPort;
    }

    public void setHttpsPort(String httpsPort) {
        this.httpsPort = httpsPort;
    }

    public List<String> getBindedPorts() {
        return bindedPorts;
    }

    public void setBindedPorts(List<String> bindedPorts) {
        this.bindedPorts = bindedPorts;
    }

    public String getSystem() {
        return system;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
