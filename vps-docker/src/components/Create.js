import React, { Component } from 'react';
import axios from 'axios'

class Create extends Component{

    constructor(props){
        super(props)
        if(!this.props.location.state){
            this.props.history.push("/")
        }
        else{
            this.state={
                mem:this.props.location.state.mem,
                cpu:this.props.location.state.cpu,
                system:this.props.location.state.system,
                response:"Creating"
            }
        }
    }

    componentDidMount(){
        
        if(this.state){
            console.log(this.state)
            axios.post('http://192.168.1.7:8080/api/vps',
                {
            
                    'memory':this.state.mem,
                    'cpus':this.state.cpu,
                    'privileged':false,
                    'httpPort':'5003',
                    'httpsPort':'5004',
                    'sshPort':'5005',
                    'system':'ubuntu-systemd',
                    'bindedPorts':[]
                
                })
                .then(result => {
                    console.log("Creat correctament")
                    console.log(result.data)
                    this.setState({
                        response:result.data
                    })
            
                })
                .catch(error => {
                    console.log("Ha fallat algo")
                    console.log(error)
                    this.setState({
                        response:JSON.stringify(error)
                    })
                })
        }
        else{
            this.props.history.push("/")
        }
        
    }

   

    render(){
        console.log(this.props)

        return(
            
            <div>
                {this.state && this.state.response}
            </div>
            
        )
    }
}

export default Create