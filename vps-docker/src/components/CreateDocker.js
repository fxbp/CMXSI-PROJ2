import React from 'react'
import Button from '@material-ui/core/Button'
import axios from 'axios'

class CreateDocker extends React.Component{

    constructor(props){
        super(props)

        this.onClick = this.onClick.bind(this)
    }

    onClick(){
        console.log("Ha fet click")
        axios.post('http://192.168.1.7:8080/api/vps',
        {
        
            'name':'testReact',
            'memory':'256m',
            'cpus':'1',
            'privileged':false,
            'httpPort':'5000',
            'httpsPort':'5001',
            'sshPort':'5002',
            'system':'ubuntu-systemd',
            'bindedPorts':[]
        
        })
        .then(result => {
            console.log("Creat correctament")
            console.log(result.data)

        })
        .catch(error => {
            console.log("Ha fallat algo")
            console.log(error)
        })
    }

    render(){
        return(
            <div>
                <Button variant="contained" color="primary" onClick={() => this.onClick() }>Crear</Button>
            </div>
        )
    }
}

export default CreateDocker