import React, {useEffect}  from 'react';
import axios from 'axios';
import Box from '@mui/material/Box';


import Link from '@mui/material/Link';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Title from './Title';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';
import { Divider } from '@mui/material';

function preventDefault(event) {
  event.preventDefault();
}



export default function Orders() {
  const [rows, setRows] = React.useState();
  const [rs, setRs] = React.useState(false);
  const [rsarr, setRsarr] = React.useState();
  const [frarr, setFrarr] = React.useState();
  const [frdept, setFrdept] = React.useState();

  function searchFl(){
    setRs(true)
    var p = []
    rows.map((i)=>{
      if(i.dept === frdept && i.arrival === frarr){
        p.push(i)
      }
    })
    setRsarr(p)
    setFrarr("")
    setFrdept("")
  }

  var t = new Date()
  var n = t.toLocaleTimeString('en-GB')

  
  useEffect(()=>{
    const config = {
      headers:{
          'Content-type':'application/json'
      }
    }
    axios.get('/travel/deals', config)
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
          
      <Title>List of Deals available at {n} -</Title>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Expire at</TableCell>
            <TableCell>Flight</TableCell>
            <TableCell>Departure</TableCell>
            <TableCell>Arrival</TableCell>
            <TableCell align="right">Amount</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {rows && rows.map((row) => 
            {
              if(row.time>n){
              return(
            <TableRow key={row._id}>
              <TableCell>{row.time}</TableCell>
              <TableCell>{row.name}</TableCell>
              <TableCell>{row.dept}</TableCell>
              <TableCell>{row.arrival}</TableCell>
              <TableCell align="right">{`$${row.cost}`}</TableCell>
            </TableRow>)}
            })}
        </TableBody>
      </Table>
      <Divider/>
      <Box
          component="form"
          sx={{
            '& > :not(style)': { m: 8, width: '25ch' },

          }}
          noValidate
          autoComplete="off"
    >
            <TextField id="filled-basic" label="Departure" variant="filled"  onChange={(e)=>{setFrdept(e.target.value)}}/>
            <TextField id="filled-basic" label="Arrival" variant="filled"  onChange={(e)=>{setFrarr(e.target.value)}}/>
            <Button variant="contained" onClick={searchFl} sx={{my:1}}>Search</Button>  
          </Box>
      <Divider/>
      <Title>Search result</Title>
      <Table size="small">
        <TableHead>
          <TableRow>
            <TableCell>Date</TableCell>
            <TableCell>Flight</TableCell>
            <TableCell>Departure</TableCell>
            <TableCell>Arrival</TableCell>
            <TableCell align="right">Amount</TableCell>
          </TableRow>
        </TableHead>
        <TableBody>

          {rs && rsarr && rsarr.map((row) => (
            <TableRow key={row.id}>
              <TableCell>{row.time}</TableCell>
              <TableCell>{row.name}</TableCell>
              <TableCell>{row.dept}</TableCell>
              <TableCell>{row.arrival}</TableCell>
              <TableCell align="right">{`$${row.cost}`}</TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
      <Divider/>

    </React.Fragment>
  );
}