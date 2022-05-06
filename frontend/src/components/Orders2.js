import React, {useEffect}  from 'react';
import axios from 'axios';

import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';


function preventDefault(event) {
  event.preventDefault();
}

export default function Orders2() {
  const [rows, setRows] = React.useState();
  var t = new Date()
  var n = t.toLocaleTimeString('en-GB')
  useEffect(()=>{
    var p= []
    rows && rows.map((i)=>{
      if(i.time<n){
        p.push(i)
      }
    })
    setRows(p)
  },[])
  
  useEffect(()=>{
    const config = {
      headers:{
          'Content-type':'application/json'
      }
    }
    axios.get('/travel/current', config)
        .then((res)=>{
            console.log(res.data)
            const data = res.data;
            setRows(data)
            console.log(rows);
            
          }
        )
        .catch(err=>console.log(err))
  },[])

  return (
    <React.Fragment>
      <Title>List of Flights available at {n}</Title>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Expires At</TableCell>
            <TableCell>Flight</TableCell>
            <TableCell>Departure</TableCell>
            <TableCell>Arrival</TableCell>
            <TableCell align="right">Amount</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows && rows.map((row) => (
            <TableRow key={row._id}>
              <TableCell>{row.time}</TableCell>
              <TableCell>{row.name}</TableCell>
              <TableCell>{row.dept}</TableCell>
              <TableCell>{row.arrival}</TableCell>
              <TableCell align="right">{`$${row.cost}`}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      
    </React.Fragment>
  );
}