import React, {Component} from 'react';
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';

import Button from '@material-ui/core/Button'


const rows = [
    createData("Ubuntu", "256m", 1, "3€"),
    createData("Ubuntu", "512m", 1, "5€"),
    createData("Ubuntu", "1g", 1, "7€"),
    createData("Ubuntu", "512g", 2, "9€"),
    createData("Ubuntu", "1g", 2, "11€")
];
  
function createData(system, mem, cpu, price) {
    return {system, mem, cpu, price };
  }




class TableVps extends Component{

    constructor(props){
        super(props)
        console.log(props)
        this.createDocker = this.createDocker.bind(this)
    }

    createDocker(specs){

        console.log("Ha fet click")
        console.log(specs)
        this.props.history.push("/create",specs:specs)
        
    }
    

  render(){
    return (
        <div >
            <Paper> 
            <Table  size="small" aria-label="a dense table">
            <TableHead>
                <TableRow>
                <TableCell>Sistema</TableCell>
                <TableCell align="right">Memoria</TableCell>
                <TableCell align="right">Cpu</TableCell>
                <TableCell align="right">Preu</TableCell>
                <TableCell align="right"></TableCell>
                </TableRow>
            </TableHead>
            <TableBody>
                {rows.map((row,index) => (
                <TableRow key={index}>
                    <TableCell component="th" scope="row">
                    {row.system}
                    </TableCell>
                    <TableCell align="right">{row.mem}</TableCell>
                    <TableCell align="right">{row.cpu}</TableCell>
                    <TableCell align="right">{row.price}</TableCell>
                    <TableCell align="right"><Button variant="contained" color="primary" onClick={() => this.createDocker(row) }>Crear</Button></TableCell>
                </TableRow>
                ))}
            </TableBody>
            </Table>
        </Paper>
        </div>
    );
    }

}
export default TableVps