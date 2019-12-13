import React, { Component } from 'react';
import axios from 'axios'

import { makeStyles } from '@material-ui/core/styles';
import CircularProgresss from '@material-ui/core/CircularProgress'
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

const useStyles = makeStyles(theme => ({
  root: {
    width: '100%',
    maxWidth: 360,
    backgroundColor: theme.palette.background.paper,
  },
  nested: {
    paddingLeft: theme.spacing(4),
  },
}));



const generateElement = (key,value) => {
    return (
      <div key={key} className="row">
        <div className="col-xs-6 ins-label">{key}</div>
        <div className="col-xs-6">{value}</div>
      </div>
    );
  }
  
  function generateData(data) {
    const newData = Object.keys(data).reduce((result, currentKey) => {
      if (typeof data[currentKey] === 'string' || data[currentKey] instanceof String) {
        const elementToPush = generateElement(currentKey, data[currentKey]);
        result.push(elementToPush);
      } else {
        const nested = generateData(data[currentKey]);
        result.push(...nested);
      }
      return result;
    }, []);
    return newData;
  }


class Create extends Component{

    constructor(props){
        super(props)
        this.useStyles = makeStyles(theme => ({
            progress: {
                margin: theme.spacing(2),
            }
        }))
        if(!this.props.location.state){
            this.props.history.push("/")
        }
        else{
            this.state={
                mem:this.props.location.state.mem,
                cpu:this.props.location.state.cpu,
                system:this.props.location.state.system,
                privileged:this.props.location.state.privileged,
                isAvailable:false,
                recieved:false,
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
                    'privileged':this.state.privileged
                
                })
                .then(result => {
                    console.log("Creat correctament")
                    console.log(result.data)
                    this.setState({
                        response:result.data,
                        isAvailable:true
                    })
            
                })
                .catch(error => {
                    console.log("Ha fallat algo")
                    console.log(error)
                    this.setState({
                        response:JSON.stringify(error),
                        recieved:true
                    })
                })
        }
        else{
            this.props.history.push("/")
        }
        
    }


   
    


    render(){
        console.log(this.props)

        if(this.state && this.state.isAvailable){
            const result = this.state.response
            const bindPorts = result.bindedPorts;
            return(
                <div style={{margin:'5%'}}>
                    <h4> Les teves dades de connexió son:</h4>
                <Paper> 
                    <Table  size="small" aria-label="a dense table">
                        <TableHead>
                            <TableRow>                            
                                <TableCell >Dades</TableCell>
                                <TableCell >Valors</TableCell>                                
                            </TableRow>
                        </TableHead>
                        <TableBody>
                        <TableRow >
                                <TableCell component="th" scope="row">Sistema</TableCell>
                                <TableCell >{result.system}</TableCell>
                            
                            </TableRow>
                            <TableRow >
                                <TableCell component="th" scope="row">Memoria</TableCell>
                                <TableCell >{result.memory}</TableCell>
                            
                            </TableRow>
                            <TableRow >
                                <TableCell component="th" scope="row">Cpu</TableCell>
                                <TableCell>{result.cpus}</TableCell>
                            
                            </TableRow>
                            <TableRow >
                                <TableCell component="th" scope="row">Password</TableCell>
                                <TableCell>{result.pass} (Sembla que la comanda no funciona, el generic es: patates)</TableCell>
                            
                            </TableRow>
                            <TableRow >
                                <TableCell component="th" scope="row">Port ssh</TableCell>
                                <TableCell>{result.sshPort}</TableCell>
                            
                            </TableRow>

                            <TableRow >
                                <TableCell component="th" scope="row">Port http</TableCell>
                                <TableCell>{result.httpPort}</TableCell>
                            
                            </TableRow>

                            <TableRow >
                                <TableCell component="th" scope="row">Port https</TableCell>
                                <TableCell>{result.httpsPort}</TableCell>
                            
                            </TableRow>

                        </TableBody>
                    </Table>
                </Paper>

                <h4>Els teus ports de connexio extra son els segents:</h4>
                <p> El port de l'esquerra (extern) és el port de connexió des de fora. El port de la dreta (intern) es el port que té disponible el VPS</p>
                <Paper> 
                    <Table  size="small" aria-label="a dense table">
                        <TableHead>
                            <TableRow>                            
                                <TableCell >Ports Extern:Intern</TableCell>                              
                            </TableRow>
                        </TableHead>
                        <TableBody>
                            {bindPorts.map((row,index) => (
                            <TableRow key={index}>
                                <TableCell component="th" scope="row">
                                    {row}
                                </TableCell>
                            
                            </TableRow>
                            ))}
                        </TableBody>
                    </Table>
                </Paper>
            </div>
            )
            /* return (
                <div>
                    {generateData(result)}        
                </div>
            ) */
        }
        else if(this.state && this.state.recieved && !this.state.isAvailable){
            return(
                
                <div>
                    {this.state && this.state.response}
                </div> 
            )
        }
        else{
            return(
                <div className="container">
                    <CircularProgresss className={this.useStyles.progress} />
                </div>
            )
        }
    }
}

export default Create